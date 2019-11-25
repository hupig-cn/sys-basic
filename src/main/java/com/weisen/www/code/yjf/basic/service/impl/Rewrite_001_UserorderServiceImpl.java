package com.weisen.www.code.yjf.basic.service.impl;
import com.google.common.collect.Lists;
import java.math.BigDecimal;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.FilesRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.*;
import com.weisen.www.code.yjf.basic.service.Rewrite_001_UserorderService;
import com.weisen.www.code.yjf.basic.service.dto.IntroductionOrderDTO;
import com.weisen.www.code.yjf.basic.service.dto.OrderDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_Commity2DTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_GoodsCommity2DTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_GoodsCommityDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserOrderDTO;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 16:04
 */
@Service
@Transactional
public class Rewrite_001_UserorderServiceImpl implements Rewrite_001_UserorderService {

    @Value("${images-path}")
    private String imagesPath;

    private final Rewrite_001_UserorderRepository rewrite_001_userorderRepository;

    private final Rewrite_SpecificationsRepository rewrite_specificationsRepository;

    private final Rewrite_OrderRepository rewrite_orderRepository;

    private final Rewrite_UserorderRepository rewrite_userOrderResource;

    private final Rewrite_LinkuserRepository rewrite_linkuserRepository;

    private final Rewrite_MerchantRepository rewrite_merchantRepository;

    private final PasswordEncoder passwordEncoder;

    private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

    private final FilesRepository filesRepository;

    private final Reweite_ProdcutimageRepository reweite_prodcutimageRepository;


    public Rewrite_001_UserorderServiceImpl(Rewrite_001_UserorderRepository rewrite_001_userorderRepository, Rewrite_SpecificationsRepository rewrite_specificationsRepository, Rewrite_OrderRepository rewrite_orderRepository, Rewrite_UserorderRepository rewrite_userOrderResource, Rewrite_LinkuserRepository rewrite_linkuserRepository, Rewrite_MerchantRepository rewrite_merchantRepository, PasswordEncoder passwordEncoder, Rewrite_LinkuserRepository rewrite_linkuserRepository1, FilesRepository filesRepository, Reweite_ProdcutimageRepository reweite_prodcutimageRepository) {

        this.rewrite_001_userorderRepository = rewrite_001_userorderRepository;
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
        this.rewrite_orderRepository = rewrite_orderRepository;
        this.rewrite_userOrderResource = rewrite_userOrderResource;
        this.rewrite_linkuserRepository = rewrite_linkuserRepository;
        this.rewrite_merchantRepository = rewrite_merchantRepository;
        this.passwordEncoder = passwordEncoder;
        rewrite_LinkuserRepository = rewrite_linkuserRepository1;
        this.filesRepository = filesRepository;
        this.reweite_prodcutimageRepository = reweite_prodcutimageRepository;
    }

    @Override
    public Result myUserOrder(String userid,String orderState,Integer pageNum,Integer pageSize) {
        List<IntroductionOrderDTO> aaa = new ArrayList<>();
        List<IntroductionOrderDTO> aa = new ArrayList<>();
        if (pageNum == null || pageSize == null ){
            pageNum = 0;
            pageSize = 10;
        }
        if (orderState.equals("0")){
            List<Userorder> list = rewrite_001_userorderRepository.findUserorderByUserid(userid);
            if (list == null){
                return Result.fail("输入参数有误");
            }else if (list.size() == 0){
                return Result.fail("暂无订单");
            }
            aa = useerorder(list);
        }else if (!orderState.equals("1") && !orderState.equals("2") && !orderState.equals("3")&& !orderState.equals("4")&& !orderState.equals("5")){
            return Result.fail("传入参数有误");
        }else {
        List<Userorder> userorder = rewrite_001_userorderRepository.findUserorderByUseridAndOrderstatus(userid, orderState);
        if (userorder == null) {
            return Result.fail("输入参数有误");
        }else if (userorder.size() == 0){
            return Result.fail("暂无这个状态的订单");
        }
            aa = useerorder(userorder);
        }

        for (int i = 1 + (pageSize * (pageNum)); i <= (pageNum+1) * (pageSize); i++) {
            if (i > aa.size()) {
                return Result.suc("查询成功，这是最后一页了", aaa, aaa.size());
            }
            IntroductionOrderDTO introductionOrderDTO = aa.get(i-1);
            aaa.add(introductionOrderDTO);
        }

        return Result.suc("查询成功",aaa,aaa.size());
    }


    @Override
    public Result OrdersConfirmation(String userid, String ordercode) {
        List<Userorder> userorderByOrdercodes = rewrite_001_userorderRepository.findUserorderByOrdercode(ordercode);
        for (int i = 0; i < userorderByOrdercodes.size(); i++) {
            Userorder userorderByOrdercode = userorderByOrdercodes.get(i);

            if (userorderByOrdercode == null){
                return Result.fail("传入参数有误");
            } else if (!userorderByOrdercode.getUserid().equals(userid)) {
                return Result.fail("你不是本人不能操作");
            }
            userorderByOrdercode.setOrderstatus("2");
            userorderByOrdercode.setId(userorderByOrdercode.getId());
            rewrite_001_userorderRepository.save(userorderByOrdercode);
        }

        return Result.suc("修改成功");
    }

    @Override
    public Result Orderdetail(String userid, String ordercode) {
        List<Userorder> userorderByOrdercodes = rewrite_001_userorderRepository.findUserorderByOrdercode(ordercode);
        List<Userorder> list = new ArrayList<>();
        for (int i = 0; i < userorderByOrdercodes.size(); i++) {
            Userorder userorderByOrdercode = userorderByOrdercodes.get(i);
            if (userorderByOrdercode == null){
                return Result.fail("请输入正确的订单编号");
            } else if (!userorderByOrdercode.getUserid().equals(userid)) {
                return Result.fail("你不是本人不能操作");
            }

            list.add(userorderByOrdercode);
        }

        List<IntroductionOrderDTO> useerorder = useerorder(list);
        IntroductionOrderDTO dto = useerorder.get(0);
        List<Order> orders = rewrite_orderRepository.findOrderByBigorder(dto.getOrderid());
        Order order = orders.get(0);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(useerorder);
        orderDTO.setConsignee(order.getConsignee());
        orderDTO.setMobile(order.getMobile());
        orderDTO.setAddress(order.getAddress());
        return Result.suc("查询成功",orderDTO);
    }

    @Override
    public List<IntroductionOrderDTO> useerorder(List<Userorder> a){
        List<IntroductionOrderDTO> aa = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            Userorder userorder = a.get(i);
            //去other出来查到他的图片，价钱，描述，   订单状态，下单时间  --> 点开就详情页面
            IntroductionOrderDTO introductionOrderDTO = new IntroductionOrderDTO();
            introductionOrderDTO.setOrderid(userorder.getId()+"");
            introductionOrderDTO.setOrdercode(userorder.getOrdercode());

            Specifications s = rewrite_specificationsRepository.findSpecificationsByCommodityid(userorder.getOther());
            Long fileid = s.getFileid();
            introductionOrderDTO.setUrl(imagesPath+fileid);
            introductionOrderDTO.setPrice(s.getPrice());
            introductionOrderDTO.setSpecificatinons(s.getSpecifications());
            introductionOrderDTO.setStaus(userorder.getOrderstatus());
            introductionOrderDTO.setPaytime(userorder.getPaytime());
            introductionOrderDTO.setOneprice(s.getPrice()+"");
            introductionOrderDTO.setCreatedate(userorder.getCreatedate());
            introductionOrderDTO.setOther(userorder.getOther());

            if (userorder.getExpressCompany() == null || userorder.getExpressCompany().equals("")){
                introductionOrderDTO.setExpress_company("未支付暂时还未发货");
            }else {
                introductionOrderDTO.setExpress_company(userorder.getExpressCompany());
            }

            if (userorder.getExpressNo() == null || userorder.getExpressNo().equals("")){
                introductionOrderDTO.setExpress_no("未支付暂无订单号");
            }else {
                introductionOrderDTO.setExpress_no(userorder.getExpressNo());
            }

            if (userorder.getPayway() == null || userorder.getPayway().equals("")){
                introductionOrderDTO.setPayway("未支付");
            }else if (userorder.getPayway().equals("1")){
                introductionOrderDTO.setPayway("支付宝支付");
            }else if(userorder.getPayway().equals("2")){
                introductionOrderDTO.setPayway("微信支付");
            }else if(userorder.getPayway().equals("3")){
                introductionOrderDTO.setPayway("余额支付");
            }else if(userorder.getPayway().equals("4")){
                introductionOrderDTO.setPayway("积分支付");
            }else if(userorder.getPayway().equals("5")){
                introductionOrderDTO.setPayway("优惠卷支付");
            }
            List<Order> orderByBigorders = rewrite_orderRepository.findOrderByBigorder(userorder.getId() + "");
            int ab = 0;
            if (orderByBigorders != null){
                for (int i1 = 0; i1 < orderByBigorders.size(); i1++) {
                    Order order = orderByBigorders.get(i1);
                    ab = ab + Integer.valueOf(order.getNum());
                }
                introductionOrderDTO.setNum(ab+"");
            }else {
                introductionOrderDTO.setNum("1");
            }
            aa.add(introductionOrderDTO);
        }
        return aa;
    }

    @Override
    public Result CreateOrder(Rewrite_UserOrderDTO dto) {
        String userId = dto.getUserId();
        Linkuser byUserid = rewrite_linkuserRepository.findByUserid(userId);
        if (byUserid == null){
            return Result.fail("没有用户信息");
        }
        List<Long> others = dto.getOthers();
        List<Long> nums = dto.getNums();
        List<Userorder> o = new ArrayList<>();
        String a  = "";
        String orderCode = OrderConstant.getOrderCode(userId);
        for (int i = 0; i < others.size(); i++) {
            Long other = others.get(i);//商品id
            Long num = nums.get(i);//数量
            Specifications specifications = rewrite_specificationsRepository.findSpecificationsByCommodityid(other+"");
            Userorder userorder = new Userorder();
            userorder.setOrdercode(orderCode);
            userorder.setOrderstatus(OrderConstant.UN_PAID);
            userorder.setModifier((Float.valueOf(specifications.getPrice())*num)+"");
            userorder.setModifiernum(num);
            userorder.setUserid(userId);
            userorder.setCreator(userId);
            userorder.setCreatedate(TimeUtil.getDate());

            userorder.setOther(other+"");
            o.add(userorder);
            if (a.equals("")){
                a = userorder.getModifier()+"";
            }else {
                a =  new BigDecimal(a).add(new BigDecimal(userorder.getModifier()))+"";
            }
        }
        nums.clear();
        for (int i = 0; i < o.size(); i++) {
            Userorder userorder = o.get(i);
            userorder.setSum(new BigDecimal(a));
            Userorder save = rewrite_userOrderResource.save(userorder);
            nums.add(save.getId());
        }
        return Result.suc("创建订单成功",nums);
    }

    @Override
    public Result cheakpaypassword(String userid, String pass) {

        Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userid);
        if(!passwordEncoder.matches(pass,linkuser.getPaypassword())){
            return  Result.fail("支付密码错误");
        }else {
            return Result.suc("支付密码成功");
        }


    }

    @Override
    public Result myfilesList(Integer pageSize, Integer pageNum,Integer type,Integer start) {
        if (type == 0){ //type = 0 全部商品  type = 1 积分精选，2 美食, 3 数码 ，4 居家

        }
        List<Rewrite_Commity2DTO> bbc = new ArrayList<>();
        int a = (pageNum-1) * pageSize;
        List<Specifications> specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerBys(a, pageSize);
        for (int i = 0; i < specificationsByOrdrerBys.size(); i++) {
            Rewrite_Commity2DTO rewrite_commity2DTO = new Rewrite_Commity2DTO();

            Specifications s = specificationsByOrdrerBys.get(i);
            String price = s.getPrice();
            Long fileid = s.getFileid();
            String commodityid = s.getCommodityid();
            String specifications = s.getSpecifications();
            Files files = filesRepository.findByIds(fileid);
            String url = files.getUrl();
            Integer height = files.getHeight();
            Integer width = files.getWidth();

            rewrite_commity2DTO.setCommityid(Long.valueOf(commodityid));
            rewrite_commity2DTO.setText(specifications);
            rewrite_commity2DTO.setPrice(price);
            rewrite_commity2DTO.setUrl(url);
            rewrite_commity2DTO.setWidth(width);
            rewrite_commity2DTO.setHeight(height);
            bbc.add(rewrite_commity2DTO);
        }

        return Result.suc("查询成功",bbc,specificationsByOrdrerBys.size());
    }

    @Override
    public Result myfilesLists(String commityid) {
        //商品详情
        Rewrite_GoodsCommity2DTO r = new Rewrite_GoodsCommity2DTO();
        Specifications a = rewrite_specificationsRepository.findSpecificationsByCommodityid(commityid);
        String specifications = a.getCommodityid();
        String title = a.getSpecifications();
        String price = a.getPrice();
        List<Long> Hp = new ArrayList<>();
        List<Rewrite_GoodsCommityDTO> hplist = new ArrayList<>();
        List<Rewrite_GoodsCommityDTO> splist = new ArrayList<>();
        List<Long> Sp = new ArrayList<>();
        List<Prodcutimage> slist = reweite_prodcutimageRepository.findAllBySpecificationsid(Long.valueOf(commityid));
        for (int i = 0; i < slist.size(); i++) {
            Prodcutimage prodcutimage = slist.get(i);
            String type = prodcutimage.getType();
            if (type.equals("1")){
                Hp.add(Long.valueOf(prodcutimage.getFileid()));
            }else {
                Sp.add(Long.valueOf(prodcutimage.getFileid()));
            }
        }
        hplist = aab(Hp);

        splist = aab(Sp);

        r.setSpecifications(specifications);
        r.setTitle(title);
        r.setPrice(price);
        r.setHplist(hplist);
        r.setSplist(splist);
        return Result.suc("查询成功",r);
    }

    public List<Rewrite_GoodsCommityDTO> aab (List<Long> Sp){
        List<Rewrite_GoodsCommityDTO> splist = new ArrayList<>();
        for (int i = 0; i < Sp.size(); i++) {
            Long aa = Sp.get(i);
            //图片id
            Rewrite_GoodsCommityDTO rewrite_goodsCommityDTO = new Rewrite_GoodsCommityDTO();

            Files filesById = filesRepository.findByIds(aa);
            Integer width = filesById.getWidth();
            Integer height = filesById.getHeight();
            String url = filesById.getUrl();
            String fileContentType = filesById.getFileContentType();
            Integer size = filesById.getSize();
            rewrite_goodsCommityDTO.setUrl(url);
            rewrite_goodsCommityDTO.setWidth(width);
            rewrite_goodsCommityDTO.setHeight(height);
            rewrite_goodsCommityDTO.setType(fileContentType);
            rewrite_goodsCommityDTO.setSize(size);
            splist.add(rewrite_goodsCommityDTO);
        }
        return splist;
    }
}
