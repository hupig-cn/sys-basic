package com.weisen.www.code.yjf.basic.service.dto.show_dto;

public class Rewrite_ProfitDTO {

	private String todayrecommend;
	
    private String todayprofit;
    
    private String todaylastprofit;
    
    private String monthprofit;
    
    private String monthlastprofit;
    
    private String totalprofit;
    
    private String totalrecommend;
    
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
		return "Rewrite_ProfitDTO [todayrecommend=" + todayrecommend + ", todayprofit=" + todayprofit
				+ ", todaylastprofit=" + todaylastprofit + ", monthprofit=" + monthprofit + ", monthlastprofit="
				+ monthlastprofit + ", totalprofit=" + totalprofit + ", totalrecommend=" + totalrecommend + "]";
	}
    
}
