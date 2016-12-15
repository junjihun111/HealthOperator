package com.health.spring.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.health.util.DateJsonSerializer;

public class Attendance {

	private int memberNo;
	private String memberName;
	@JsonSerialize(using=DateJsonSerializer.class)
	private Date attendanceDate;
	
	public Attendance(){}

	public Attendance(int memberNo, String memberName, Date attendanceDate) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.attendanceDate = attendanceDate;
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

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendanceDate == null) ? 0 : attendanceDate.hashCode());
		result = prime * result + ((memberName == null) ? 0 : memberName.hashCode());
		result = prime * result + memberNo;
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
		Attendance other = (Attendance) obj;
		if (attendanceDate == null) {
			if (other.attendanceDate != null)
				return false;
		} else if (!attendanceDate.equals(other.attendanceDate))
			return false;
		if (memberName == null) {
			if (other.memberName != null)
				return false;
		} else if (!memberName.equals(other.memberName))
			return false;
		if (memberNo != other.memberNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attendance [memberNo=" + memberNo + ", memberName=" + memberName + ", attendanceDate=" + attendanceDate
				+ "]";
	}
	
	
}
