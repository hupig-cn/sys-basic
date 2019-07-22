package com.weisen.www.code.yjf.basic.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkaccountRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_AlipayService;
import com.weisen.www.code.yjf.basic.util.AlipayUtil;
import com.weisen.www.code.yjf.basic.util.DateUtils;

@Service
@Transactional
public class Rewrite_AlipayServiceImpl implements Rewrite_AlipayService {

    private final Rewrite_LinkaccountRepository rewrite_LinkaccountRepository;
    
	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;
	
	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;
	
	private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;
	
    public Rewrite_AlipayServiceImpl(Rewrite_LinkaccountRepository rewrite_LinkaccountRepository,Rewrite_LinkuserRepository rewrite_LinkuserRepository,
    		Rewrite_UserassetsRepository rewrite_UserassetsRepository,Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository) {
        this.rewrite_LinkaccountRepository = rewrite_LinkaccountRepository;
        this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
        this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
    }

    @Override
    public String scaning(String userid, String authCode) {
        AlipaySystemOauthTokenResponse userInfo = AlipayUtil.getUserInfo(authCode);
        if (userInfo == null) {
            return "获取支付宝会员信息失败";
        }
        String alipayUserId = userInfo.getUserId();
        //判断我是否已经绑定支付宝
        Linkaccount mylinkaccount = rewrite_LinkaccountRepository.findFirstByUseridAndAccounttype(userid, "支付宝");
        if (mylinkaccount!=null) {
        	return "你已经绑定过支付宝了";
        }
        Linkaccount linkaccount = rewrite_LinkaccountRepository.findFirstByAccounttypeAndToken("支付宝",alipayUserId);//判断系统是否有这个支付宝
        String recommendr = "";
        String recommenddate = DateUtils.getDateForNow();
        String id = "0";
        if (linkaccount != null) {
        	Optional<Linkuser> linkuser = rewrite_LinkuserRepository.findByUserid(linkaccount.getUserid());
        	if (linkuser.get() != null&&linkuser.get().getPhone()!=null) {
        		if (linkaccount.getUserid().equals(userid)) {
        			return "已绑定，请勿重复操作";
        		}else {
        			return "此支付宝已被他人绑定";
        		}
        	}else {
        		Userassets UserassetsI = rewrite_UserassetsRepository.findByUserid(userid);
        		Userassets UserassetsII = rewrite_UserassetsRepository.findByUserid(linkaccount.getUserid());
        		UserassetsI.setIntegral(String.valueOf((Integer.parseInt(UserassetsI.getIntegral()) + Integer.parseInt(UserassetsII.getIntegral()))));
        		id = UserassetsII.getUserid();
        		rewrite_UserassetsRepository.deleteById(UserassetsII.getId());//用来合并资产
        		Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(linkaccount.getUserid());
        		if (userlinkuser!=null) {
        			recommendr = userlinkuser.getRecommendid();
        			recommenddate = userlinkuser.getModifierdate();
        			rewrite_UserlinkuserRepository.delete(userlinkuser);//拿出推荐人
        		}
        	}
        }
        if (recommendr!=null&&!recommendr.equals("")) {
	        Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userid);
	        if (userlinkuser!=null&&userlinkuser.getRecommendid()!=null) {//app有推荐人
	        	if(userlinkuser.getOther()!=null) {
	        		if (Long.parseLong(userlinkuser.getModifierdate())-Long.parseLong(recommenddate) > 0) {
	        			userlinkuser.setRecommendid(recommendr);
	        			userlinkuser.setModifierdate(recommenddate);
	        			userlinkuser.setOther("支付宝");
	        		}
	        	}
	        }else if(userlinkuser!=null && userlinkuser.getRecommendid()==null) {//app没有推荐人，但是有数据
	        	userlinkuser.setRecommendid(recommendr);
    			userlinkuser.setModifierdate(recommenddate);
    			userlinkuser.setOther("支付宝");
	        }else {//没有推荐人，并且没有数据
	        	userlinkuser = new Userlinkuser();
	        	userlinkuser.setUserid(userid);
	        	userlinkuser.setRecommendid(recommendr);
	    		userlinkuser.setPartner(false);
	    		userlinkuser.setProvince(false);
	    		userlinkuser.setCity(false);
	    		userlinkuser.setCounty(false);
	    		userlinkuser.setCreator(userid);
	    		userlinkuser.setCreatedate(recommenddate);
	    		userlinkuser.setModifier(userid);
	        	userlinkuser.setModifierdate(recommenddate);
    			userlinkuser.setOther("支付宝");
	        }
	        rewrite_UserlinkuserRepository.save(userlinkuser);
        }
        mylinkaccount = new Linkaccount();
        mylinkaccount.setAccounttype("支付宝");
        mylinkaccount.setUserid(userid);
        mylinkaccount.setToken(alipayUserId);
        mylinkaccount.setCreator(userid);
        mylinkaccount.setCreatedate(DateUtils.getDateForNow());
        mylinkaccount.setModifier(userid);
        mylinkaccount.setModifierdate(DateUtils.getDateForNow());
        rewrite_LinkaccountRepository.save(mylinkaccount);
        return id;
    }
    @Override
    public String queryAlipay (String userid) {
        Linkaccount mylinkaccount = rewrite_LinkaccountRepository.findFirstByUseridAndAccounttype(userid, "支付宝");
        if (mylinkaccount!=null) {
        	return "已绑定";
        }
    	return "未绑定";
    }
}
