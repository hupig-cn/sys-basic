package com.weisen.www.code.yjf.basic.web.rest.rewrite_003_消息;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class Rewrite_StompConroller {

    public Rewrite_StompConroller(){

    }
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;


    @MessageMapping("/marco")
    public void handleShout() {
    }









}
