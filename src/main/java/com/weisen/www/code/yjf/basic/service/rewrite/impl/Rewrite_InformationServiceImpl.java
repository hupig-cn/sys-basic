package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.alibaba.fastjson.JSONObject;
import com.weisen.www.code.yjf.basic.domain.Information;
import com.weisen.www.code.yjf.basic.domain.Messagetemplate;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_InformationRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MessageTemplateRepository;
import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_InformationService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitInformationDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_InformationMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_MessageTemplateMapper;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_InformationServiceImpl implements Rewrite_InformationService {

    private final Rewrite_InformationRepository rewrite_informationRepository;

    private final Rewrite_InformationMapper rewrite_informationMapper;

    private final Rewrite_MessageTemplateRepository rewrite_messageTemplateRepository;

    private final Rewrite_MessageTemplateMapper rewrite_messageTemplateMapper;

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    public Rewrite_InformationServiceImpl(Rewrite_InformationRepository rewrite_informationRepository, Rewrite_InformationMapper rewrite_informationMapper, Rewrite_MessageTemplateRepository rewrite_messageTemplateRepository, Rewrite_MessageTemplateMapper rewrite_messageTemplateMapper) {
        this.rewrite_informationRepository = rewrite_informationRepository;
        this.rewrite_informationMapper = rewrite_informationMapper;
        this.rewrite_messageTemplateRepository = rewrite_messageTemplateRepository;
        this.rewrite_messageTemplateMapper = rewrite_messageTemplateMapper;
    }

    /**
     * 保存消息,当消息模板type为空的时候,默认将所有内容设置进去
     *
     * @param rewrite_submitInformationDTO
     * @return
     */
    public Result insertInformation(Rewrite_submitInformationDTO rewrite_submitInformationDTO) {
        if (!CheckUtils.checkObj(rewrite_submitInformationDTO))
            return Result.fail();
        else if (!CheckUtils.checkString(rewrite_submitInformationDTO.getContent()))
            return Result.fail("消息内容不能为空");
        else if (!CheckUtils.checkString(rewrite_submitInformationDTO.getReaduserid()))
            return Result.fail("接收人不能为空");
        else if (!CheckUtils.checkString(rewrite_submitInformationDTO.getSenduserid()))
            return Result.fail("发送人不能为空");
        else {
            if (!CheckUtils.checkString(rewrite_submitInformationDTO.getType())) {
                Information information = new Information();
                //设置内容
                information.setContent(rewrite_submitInformationDTO.getContent());
                //设置创建时间
                information.setCreatedate(DateUtils.getDateForNow());
                //设置创建者
                information.setCreator(rewrite_submitInformationDTO.getCreator());
                //设置发送人id
                information.setSenduserid(rewrite_submitInformationDTO.getSenduserid());
                //设置接收人id
                information.setReaduserid(rewrite_submitInformationDTO.getReaduserid());
                //设置备注
                information.setOther(rewrite_submitInformationDTO.getOther());
                //设置标题
                information.setTitle(rewrite_submitInformationDTO.getTitle());
                //逻辑删除
                information.setLogicdelete(false);
                //设置消息类型
                information.setType("1");
                information.setState("1");
                if ("1".equals(rewrite_submitInformationDTO.getWeight()) && "所有人".equals(rewrite_submitInformationDTO.getReaduserid())) {
                    simpMessageSendingOperations.convertAndSend("/topic", information.getContent());
                }
                else if ("1".equals(rewrite_submitInformationDTO.getWeight()) && !"所有人".equals(rewrite_submitInformationDTO.getReaduserid())) {
                    simpMessageSendingOperations.convertAndSendToUser(information.getReaduserid(), "/message", information.getContent());
                }
                Information save = rewrite_informationRepository.save(information);
                if (!CheckUtils.checkObj(save)) {
                    return Result.fail("数据保存异常,请稍后重试");
               }
            } else {
                Messagetemplate templateByType = rewrite_messageTemplateRepository.findTemplateByType(rewrite_submitInformationDTO.getType());
                if (null == templateByType)
                    return Result.fail("消息模板异常");
                JSONObject data = (JSONObject) JSONObject.parse(rewrite_submitInformationDTO.getContent());
                System.out.println(data.get("goods"));
                Information information = new Information();
                Iterator<String> iterator = data.keySet().iterator();
                String content = templateByType.getContent();
                System.out.println(content);
                while (iterator.hasNext()) {
                    String parm = iterator.next().trim();
                    System.out.println(parm);
                    String str = data.get(parm).toString().trim();
                    if (null != str && !"".equals(str))
                        content = content.trim().replace(parm, str);
                }
                System.out.println(content);
                //设置内容
                information.setContent(content);
                //设置创建时间
                information.setCreatedate(DateUtils.getDateForNow());
                //设置消息类型
                information.setType(templateByType.getType());
                //设置消息标题
                information.setTitle(templateByType.getTitle());
                //设置消息接收人
                information.setReaduserid(rewrite_submitInformationDTO.getReaduserid());
                //设置消息发送人
                information.setSenduserid(rewrite_submitInformationDTO.getSenduserid());
                //设置消息创建人
                information.setCreator(rewrite_submitInformationDTO.getCreator());
                //设置备注
                information.setOther(rewrite_submitInformationDTO.getOther());
                //设置消息状态,已读,未读
                information.setState("1");
                //设置权重
                information.setWeight(rewrite_submitInformationDTO.getWeight());
                // 逻辑删除
                information.setLogicdelete(false);
                if ("1".equals(information.getWeight()) && "所有人".equals(rewrite_submitInformationDTO.getReaduserid())) {
                    simpMessageSendingOperations.convertAndSend("/topic", information.getContent());
                }
                else if ("1".equals(information.getWeight()) && !"所有人".equals(rewrite_submitInformationDTO.getReaduserid())) {
                    simpMessageSendingOperations.convertAndSendToUser(information.getReaduserid(), "/message", information.getContent());
                }
                Information save = rewrite_informationRepository.save(information);
                if (!CheckUtils.checkObj(save)) {
                    return Result.fail("数据保存异常,请稍后重试");
                }
            }
            return Result.suc("保存成功");
        }
    }

    /**
     * 获取消息详情
     * @param id
     * @param userId
     * @return
     */
    public Result readInformation(Long id,String userId) {
        if(!CheckUtils.checkLongByZero(id))
            return Result.fail();
        else if(!CheckUtils.checkString(userId))
            return Result.fail();
        else {
            Optional<Information> informationByUserIdAndId = rewrite_informationRepository.findInformationByUserIdAndId(userId, id);
            Information information = informationByUserIdAndId.get();
            Integer integer = rewrite_informationRepository.readInformationById(id, userId);
            if(null == integer || 0 == integer)
                return Result.fail("数据库异常");
            InformationDTO informationDTO = rewrite_informationMapper.toDto(information);
            return Result.suc("获取成功",informationDTO);
        }
    }

    /**
     * 全部已读
     * @param ids
     * @param userId
     * @return
     */
    public Result readAllInformation(String userId) {
        if(!CheckUtils.checkString(userId))
            return Result.fail("用户信息异常");
        else {
            Integer integer = rewrite_informationRepository.readAllInformation(userId);
            if(!CheckUtils.checkIntegerByZero(integer))
                return Result.fail("服务器繁忙,请稍后重试");
        }
        return Result.suc();
    }

    /**
     * 批量删除消息
     * @param ids
     * @param userId
     * @return
     */
    public Result deleteInformations(Long[] ids, String userId) {
        if(!CheckUtils.checkArray(ids))
            return Result.fail("消息信息异常");
        else if(!CheckUtils.checkString(userId))
            return Result.fail("用户信息异常");
        else {
            rewrite_informationRepository.deleteInformations(ids,userId);
        }
        return Result.suc("删除成功");
    }

    /**
     * 获取账号消息列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Result getInformations(String userId, Integer pageNum, Integer pageSize) {
        if(!CheckUtils.checkString(userId))
            return Result.fail();
        else if(!CheckUtils.checkPageInfo(pageNum,pageSize))
            return Result.fail();
        else {
            List<Information> informations = rewrite_informationRepository.getInformations(userId, pageNum * pageSize, pageSize);
            if(!CheckUtils.checkList(informations))
                return Result.fail();
            Integer count = rewrite_informationRepository.getCount(userId);
            List<InformationDTO> data = rewrite_informationMapper.toDto(informations);
            return Result.suc("获取成功",data,count);
        }
    }
}
