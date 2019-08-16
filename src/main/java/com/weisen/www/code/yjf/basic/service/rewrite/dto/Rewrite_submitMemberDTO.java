package com.weisen.www.code.yjf.basic.service.rewrite.dto;

public class Rewrite_submitMemberDTO {
    private Long userId;
    private Integer RealName;
    private String userName;
    private Integer pageNum;
    private Integer pageSize;

    public String toString() {
        return "Rewrite_submitMemberDTO{" +
            "userId=" + userId +
            ", RealName=" + RealName +
            ", userName='" + userName + '\'' +
            ", pageNum=" + pageNum +
            ", pageSize=" + pageSize +
            '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRealName() {
        return RealName;
    }

    public void setRealName(Integer realName) {
        RealName = realName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
