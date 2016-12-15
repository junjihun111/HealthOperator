package com.health.spring.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ManagerInfo {


	private String managerId;

	private String managerPw;
	

	private String serialNum;

	private String managerEmail;
	
	
	public ManagerInfo(){}


	public ManagerInfo(String managerId, String managerPw, String serialNum, String managerEmail) {
		super();
		this.managerId = managerId;
		this.managerPw = managerPw;
		this.serialNum = serialNum;
		this.managerEmail = managerEmail;
	}


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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((managerEmail == null) ? 0 : managerEmail.hashCode());
		result = prime * result + ((managerId == null) ? 0 : managerId.hashCode());
		result = prime * result + ((managerPw == null) ? 0 : managerPw.hashCode());
		result = prime * result + ((serialNum == null) ? 0 : serialNum.hashCode());
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
		ManagerInfo other = (ManagerInfo) obj;
		if (managerEmail == null) {
			if (other.managerEmail != null)
				return false;
		} else if (!managerEmail.equals(other.managerEmail))
			return false;
		if (managerId == null) {
			if (other.managerId != null)
				return false;
		} else if (!managerId.equals(other.managerId))
			return false;
		if (managerPw == null) {
			if (other.managerPw != null)
				return false;
		} else if (!managerPw.equals(other.managerPw))
			return false;
		if (serialNum == null) {
			if (other.serialNum != null)
				return false;
		} else if (!serialNum.equals(other.serialNum))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ManagerInfo [managerId=" + managerId + ", managerPw=" + managerPw + ", serialNum=" + serialNum
				+ ", managerEmail=" + managerEmail + "]";
	}
	
	
}
