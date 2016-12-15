package com.health.validator;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.health.spring.vo.HealthMember;
import com.health.util.DateJsonSerializerable;

public class HealthMemberForm {

	
	private int memberNo;
	
	@NotEmpty(message="회원이름은 필수입력사항 입니다.")
	private String memberName;
	
	
	private String phoneFront;
	
	@Size(min=3, max=4, message="중간자리 핸드폰번호는 3-4글자사이 입니다.")
	private String phoneMiddle;
	
	@Size(min=4, max=4, message="핸드폰 뒷자리는 반드시 4자여야 합니다.")
	private String phoneEnd;
	
	@NotNull(message="운동시작일을 지정해주세요")
	@JsonSerialize(using=DateJsonSerializerable.class)
	@DateTimeFormat(pattern="yyyy년 MM월 dd일")
	private Date startDay;
	
	
	@NotNull(message="운동만료일을 지정해주세요")
	@DateTimeFormat(pattern="yyyy년 MM월 dd일")
	private Date endDay;
	
	//선택항목
	private int shoesNo;
	
	@DateTimeFormat(pattern="yyyy년 MM월 dd일")
	@Past(message="현재날짜보다 과거 날짜만 가능합니다.")
	private Date birthday;
	private String address;
	private String email;


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
	public String toString() {
		return "HealthMember [memberNo=" + memberNo + ", memberName=" + memberName + ", phoneFront=" + phoneFront
				+ ", phoneMiddle=" + phoneMiddle + ", phoneEnd=" + phoneEnd + ", startDay=" + startDay + ", endDay="
				+ endDay + ", shoesNo=" + shoesNo + ", birthday=" + birthday + ", address=" + address + ", email="
				+ email + "]";
	}

}
