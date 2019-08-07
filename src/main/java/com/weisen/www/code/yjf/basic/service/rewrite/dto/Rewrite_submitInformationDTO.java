package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

public class Rewrite_submitInformationDTO implements Serializable {
    private String type;
    private String senduserid;
    private String readuserid;
    private String title;
    private String content;
    private String other;
    private String Creator;
    private String weight;
    private Long ids;
    private String userId;

    public Rewrite_submitInformationDTO(){

    }

    public Rewrite_submitInformationDTO(String type,String senduserid,String readuserid,String content){
        this.type = type;
        this.senduserid = senduserid;
        this.readuserid = readuserid;
        this.content = content;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(String senduserid) {
        this.senduserid = senduserid;
    }

    public String getReaduserid() {
        return readuserid;
    }

    public void setReaduserid(String readuserid) {
        this.readuserid = readuserid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
