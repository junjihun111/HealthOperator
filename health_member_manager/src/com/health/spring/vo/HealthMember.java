package com.health.spring.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.health.util.DateJsonSerializerable;

public class HealthMember implements Serializable{
	//필수항목
	private int memberNo;
	
	private String memberName;
	
	private String phoneFront;
	
	private String phoneMiddle;
	
	private String phoneEnd;
	
	@JsonSerialize(using=DateJsonSerializerable.class)
	private Date startDay;
	
	
	@JsonSerialize(using=DateJsonSerializerable.class)
	private Date endDay;
	
	//선택항목
	private int shoesNo;
	
	@JsonSerialize(using=DateJsonSerializerable.class)
	private Date birthday;
	private String address;
	private String email;

	
	public HealthMember(){}

	public HealthMember(int memberNo, String memberName, String phoneFront, String phoneMiddle, String phoneEnd,
			Date startDay, Date endDay, int shoesNo) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.phoneFront = phoneFront;
		this.phoneMiddle = phoneMiddle;
		this.phoneEnd = phoneEnd;
		this.startDay = startDay;
		this.endDay = endDay;
		this.shoesNo = shoesNo;
	}







	public HealthMember(int memberNo, String memberName, String phoneFront, String phoneMiddle, String phoneEnd,
			Date startDay, Date endDay, int shoesNo, Date birthday, String address, String email, int returnPrice) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.phoneFront = phoneFront;
		this.phoneMiddle = phoneMiddle;
		this.phoneEnd = phoneEnd;
		this.startDay = startDay;
		this.endDay = endDay;
		this.shoesNo = shoesNo;
		this.birthday = birthday;
		this.address = address;
		this.email = email;

	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhoneFront() {
		return phoneFront;
	}

	public void setPhoneFront(String phoneFront) {
		this.phoneFront = phoneFront;
	}

	public String getPhoneMiddle() {
		return phoneMiddle;
	}

	public void setPhoneMiddle(String phoneMiddle) {
		this.phoneMiddle = phoneMiddle;
	}

	public String getPhoneEnd() {
		return phoneEnd;
	}

	public void setPhoneEnd(String phoneEnd) {
		this.phoneEnd = phoneEnd;
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

	public int getShoesNo() {
		return shoesNo;
	}

	public void setShoesNo(int shoesNo) {
		this.shoesNo = shoesNo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endDay == null) ? 0 : endDay.hashCode());
		result = prime * result + ((memberName == null) ? 0 : memberName.hashCode());
		result = prime * result + memberNo;
		result = prime * result + ((phoneEnd == null) ? 0 : phoneEnd.hashCode());
		result = prime * result + ((phoneFront == null) ? 0 : phoneFront.hashCode());
		result = prime * result + ((phoneMiddle == null) ? 0 : phoneMiddle.hashCode());

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
		HealthMember other = (HealthMember) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endDay == null) {
			if (other.endDay != null)
				return false;
		} else if (!endDay.equals(other.endDay))
			return false;
		if (memberName == null) {
			if (other.memberName != null)
				return false;
		} else if (!memberName.equals(other.memberName))
			return false;
		if (memberNo != other.memberNo)
			return false;
		if (phoneEnd == null) {
			if (other.phoneEnd != null)
				return false;
		} else if (!phoneEnd.equals(other.phoneEnd))
			return false;
		if (phoneFront == null) {
			if (other.phoneFront != null)
				return false;
		} else if (!phoneFront.equals(other.phoneFront))
			return false;
		if (phoneMiddle == null) {
			if (other.phoneMiddle != null)
				return false;
		} else if (!phoneMiddle.equals(other.phoneMiddle))
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
		return "HealthMember [memberNo=" + memberNo + ", memberName=" + memberName + ", phoneFront=" + phoneFront
				+ ", phoneMiddle=" + phoneMiddle + ", phoneEnd=" + phoneEnd + ", startDay=" + startDay + ", endDay="
				+ endDay + ", shoesNo=" + shoesNo + ", birthday=" + birthday + ", address=" + address + ", email="
				+ email + "]";
	}


}