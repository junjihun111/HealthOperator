package com.health.validator;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ShoesCageForm {
	
	private int memberNo;
	
	@NotNull(message="신발장 번호를 입력하세요")
	private int shoesNo;
	@NotNull(message="신발장시작일을 선택하세요")
	@DateTimeFormat(pattern="yyyy년 MM월 dd일")
	private Date startDay;
	@NotNull(message="신발장입력일을 선택하세요")
	@DateTimeFormat(pattern="yyyy년 MM월 dd일")
	private Date endDay;
	
	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getShoesNo() {
		return shoesNo;
	}

	public void setShoesNo(int shoesNo) {
		this.shoesNo = shoesNo;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}


	@Override
	public String toString() {
		return "ShoesCage [memberNo=" + memberNo + ", shoesNo=" + shoesNo + ", startDay=" + startDay + ", endDay="
				+ endDay + "]";
	}
}
