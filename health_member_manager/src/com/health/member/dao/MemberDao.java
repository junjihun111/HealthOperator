package com.health.member.dao;

import java.util.Date;
import java.util.List;

import com.health.spring.vo.Attendance;
import com.health.spring.vo.CodeTable;
import com.health.spring.vo.HealthMember;
import com.health.spring.vo.ManagerInfo;
import com.health.spring.vo.ShoesCage;

public interface MemberDao {

	int insertMember(HealthMember member);

	List<HealthMember> selectMemberList();

	List<HealthMember> nameSearchMemberList(String memberName);

	HealthMember idSearchMemberList(int id);

	List<CodeTable> selectCodePhoneNumber();

	ShoesCage ShoesCagebyNo(int memberNo);

	int insertAttendance(Attendance att);

	List<Attendance> oneMemberAttendList(int memberNo);

	int oneMemberAttendCount(int memberNo);

	int deleteByAttend(List attendanceDates, int id);

	public int deleteByMemberNo(List memberNos);

	int updateMemberByNo(HealthMember member);

	List<HealthMember> getMemberPageList(int memberPerpageAmout, int anyPage);

	int selectMemberCount();

	List<ManagerInfo> managerSearchIdPw();

	int shooseCageInsert(ShoesCage cage);

	int updateShooesNoForList(int shooesNo, int memberNo);

	int ShoesCageStop(int memberNo);

	int shoesNoValueZero(int memberNo);

	List<Integer> usingShooseNo();

	List<ShoesCage> endedShoeNo();

	int nameSearchMemberCount(String memberName);
	List<HealthMember> endDateMember(int memberPerpageAmout, int anyPage);
	int endDateMemberCount();
	
	List<Integer> memberNoCheck();
	
	
}
