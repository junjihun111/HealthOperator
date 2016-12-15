package com.health.validator;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.health.spring.vo.ManagerInfo;

public class ManagerInfoForm {

	
	@NotEmpty(message="ID부분이 비어있습니다.")
	private String managerId;
	@NotEmpty(message="PassWord 부분이 비어있습니다.")
	private String managerPw;
	
	@Size(min=4, max=4, message="주민번호 뒷자리 숫자 네개를 입력하세요")
	private String serialNum;
	@Email(message="이메일 형식에 맞게 지정하세요")
	private String managerEmail;
	
	public String getManagerId() {
		return managerId;
	}


	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}


	public String getManagerPw() {
		return managerPw;
	}


	public void setManagerPw(String managerPw) {
		this.managerPw = managerPw;
	}


	public String getSerialNum() {
		return serialNum;
	}


	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}


	public String getManagerEmail() {
		return managerEmail;
	}


	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}



	@Override
	public String toString() {
		return "ManagerInfo [managerId=" + managerId + ", managerPw=" + managerPw + ", serialNum=" + serialNum
				+ ", managerEmail=" + managerEmail + "]";
	}
}
