package com.weisen.www.code.yjf.basic.service.dto.show_dto;

public class Rewrite_ProfitDTO {

    private Long id; // 用户id
    
	private String todayrecommend; // 今日推荐人数量
	
    private String todayprofit; // 今日分销的收入数量
    
    private String todaylastprofit; // 昨日的分销收入
    
    private String monthprofit; // 本月的分销收入
    
    private String monthlastprofit; // 上月的分销收入
    
    private String totalprofit; // 总分销收入
    
    private String totalrecommend; // 总推荐人数
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTodayrecommend() {
		return todayrecommend;
	}

	public void setTodayrecommend(String todayrecommend) {
		this.todayrecommend = todayrecommend;
	}

	public String getTodayprofit() {
		return todayprofit;
	}

	public void setTodayprofit(String todayprofit) {
		this.todayprofit = todayprofit;
	}

	public String getTodaylastprofit() {
		return todaylastprofit;
	}

	public void setTodaylastprofit(String todaylastprofit) {
		this.todaylastprofit = todaylastprofit;
	}

	public String getMonthprofit() {
		return monthprofit;
	}

	public void setMonthprofit(String monthprofit) {
		this.monthprofit = monthprofit;
	}

	public String getMonthlastprofit() {
		return monthlastprofit;
	}

	public void setMonthlastprofit(String monthlastprofit) {
		this.monthlastprofit = monthlastprofit;
	}

	public String getTotalprofit() {
		return totalprofit;
	}

	public void setTotalprofit(String totalprofit) {
		this.totalprofit = totalprofit;
	}

	public String getTotalrecommend() {
		return totalrecommend;
	}

	public void setTotalrecommend(String totalrecommend) {
		this.totalrecommend = totalrecommend;
	}

	@Override
	public String toString() {
		return "Rewrite_ProfitDTO [id=" + id + ", todayrecommend=" + todayrecommend + ", todayprofit=" + todayprofit
				+ ", todaylastprofit=" + todaylastprofit + ", monthprofit=" + monthprofit + ", monthlastprofit="
				+ monthlastprofit + ", totalprofit=" + totalprofit + ", totalrecommend=" + totalrecommend + "]";
	}
}
