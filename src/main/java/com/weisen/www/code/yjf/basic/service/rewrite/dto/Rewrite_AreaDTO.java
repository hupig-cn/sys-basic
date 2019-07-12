package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class Rewrite_AreaDTO implements Serializable {

    private Long id;

    @Size(max = 50)
    private String name;

    @Max(value = 11)
    private Integer pid;

    @Max(value = 1)
    private Integer status;

    @Size(max = 50)
    private String pname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
