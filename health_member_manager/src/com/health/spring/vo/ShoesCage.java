package com.health.spring.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.health.util.DateJsonSerializerable;

public class ShoesCage implements Serializable{

	private int memberNo;
	private int shoesNo;

	@JsonSerialize(using=DateJsonSerializerable.class)
	private Date startDay;
	@JsonSerialize(using=DateJsonSerializerable.class)
	
	private Date endDay;
	
	public ShoesCage(){}

	public ShoesCage(int memberNo, int shoesNo, Date startDay, Date endDay) {
		super();
		this.memberNo = memberNo;
		this.shoesNo = shoesNo;
		this.startDay = startDay;
		this.endDay = endDay;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDay == null) ? 0 : endDay.hashCode());
		result = prime * result + memberNo;
		result = prime * result + shoesNo;
		result = prime * result + ((startDay == null) ? 0 : startDay.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoesCage other = (ShoesCage) obj;
		if (endDay == null) {
			if (other.endDay != null)
				return false;
		} else if (!endDay.equals(other.endDay))
			return false;
		if (memberNo != other.memberNo)
			return false;
		if (shoesNo != other.shoesNo)
			return false;
		if (startDay == null) {
			if (other.startDay != null)
				return false;
		} else if (!startDay.equals(other.startDay))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShoesCage [memberNo=" + memberNo + ", shoesNo=" + shoesNo + ", startDay=" + startDay + ", endDay="
				+ endDay + "]";
	}
	
}
