package com.health.member.service;

import java.util.List;
import java.util.Map;

import com.health.common.exception.DuplicatedIdException;
import com.health.spring.vo.Attendance;
import com.health.spring.vo.CodeTable;
import com.health.spring.vo.HealthMember;
import com.health.spring.vo.ManagerInfo;
import com.health.spring.vo.ShoesCage;


public interface MemberService {

	/**
	 * 한명의 회원 추가하는 메소드
	 *  - 기존 저장된 회원과 같은 ID의 회원은 추가하지 않는다. (ID중복검사) 
	 * @param member : 추가할 한명의 회원정보. 회원의 type이 Member이므로 매개변수로 Member 선언.
	 * @throws DuplicatedIdException 등록하려는 회원 아이디가 이미 등록되 있는 경우 발생
	 * 
	 * 
	 */
	int insertMember(HealthMember member);
	List<HealthMember> getMemberList();

	List<HealthMember> nameSearchMemberList(String memberName);
	int nameSearchMemberCount(String memberName);
	HealthMember IdSearchMemberList(int num);
	List<CodeTable> selectCodePhoneNumber();
	ShoesCage ShoesCagebyNo(int memberNo);
	int insertAttendance(Attendance att);
	List<Attendance> oneMemberAttendList(int memberNo);
	int oneMemberAttendCount (int memberNo);
	int deleteByAttend(List attendanceDates, int id);
	int deleteByMemberNo(List memberNos);
	int updateMemberByNo(HealthMember member);
	List<HealthMember> getMemberPageList(int page);
	int selectCountMember();
	List<ManagerInfo> managerSearchIdPw();
	int shooseCageInsert(ShoesCage cage);
	int updateShooesNoForList(int shooseNo, int memberNo);
	int ShoesCageStop(int memberNo);
	int shoesNoValueZero(int memberNo);
	List<Integer> usingShooseNo();
	List<ShoesCage> endedShoeNo();
	List<HealthMember> endDateMember(int anyPage);
	int endDateMemberCount();
	
	List<Integer> memberNoCheck();
}