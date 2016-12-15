package com.health.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.member.dao.MemberDao;
import com.health.member.service.MemberService;
import com.health.spring.vo.Attendance;
import com.health.spring.vo.CodeTable;
import com.health.spring.vo.HealthMember;
import com.health.spring.vo.ManagerInfo;
import com.health.spring.vo.ShoesCage;
import com.health.util.Constants;
import com.health.util.PagingBean;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao dao;

	@Override
	public List<HealthMember> getMemberList() {
		
		return dao.selectMemberList();
	}
	
	
	
	@Override
	public HealthMember IdSearchMemberList(int  id){
		// TODO Auto-generated method stub
		return dao.idSearchMemberList(id);
	}
	
	public List<HealthMember> nameSearchMemberList(String memberName){
		// TODO Auto-generated method stub
		return dao.nameSearchMemberList(memberName);
	}
	
	
	@Override
	public int nameSearchMemberCount(String memberName) {
		// TODO Auto-generated method stub
		return dao.nameSearchMemberCount(memberName);
	}


	@Override
	public List<CodeTable> selectCodePhoneNumber() {
		// TODO Auto-generated method stub
		return dao.selectCodePhoneNumber();
	}


	@Override
	public int insertMember(HealthMember member) {
		// TODO Auto-generated method stub
		return dao.insertMember(member);
	}


	@Override
	public ShoesCage ShoesCagebyNo(int memberNo) {
		// TODO Auto-generated method stub
		return dao.ShoesCagebyNo(memberNo);
	}


	@Override
	public int insertAttendance(Attendance att) {
		// TODO Auto-generated method stub
		return dao.insertAttendance(att);
	}


	@Override
	public List<Attendance> oneMemberAttendList(int memberNo) {
		// TODO Auto-generated method stub
		return dao.oneMemberAttendList(memberNo);
	}


	@Override
	public int oneMemberAttendCount(int memberNo) {
		// TODO Auto-generated method stub
		return dao.oneMemberAttendCount(memberNo);
	}


	@Override
	public int deleteByAttend(List attendanceDates, int id) {
		// TODO Auto-generated method stub
		return dao.deleteByAttend(attendanceDates, id);
	}


	@Override
	public int deleteByMemberNo(List memberNos) {
		// TODO Auto-generated method stub
		return dao.deleteByMemberNo(memberNos);
	}


	@Override
	public int updateMemberByNo(HealthMember member) {
		// TODO Auto-generated method stub
		return dao.updateMemberByNo(member);
	}


	@Override
	public List<HealthMember> getMemberPageList(int page) {
		List<HealthMember> list = dao.getMemberPageList(Constants.ITEMS_PER_PAGE, page);
		
		return list;
	}
	
	public int selectCountMember(){
		int MemberAllCount= dao.selectMemberCount();
		return MemberAllCount;
	}
	
	
	@Override
	public List<HealthMember> endDateMember(int anyPage) {
		// TODO Auto-generated method stub
		List<HealthMember> list = dao.endDateMember(Constants.ITEMS_PER_PAGE, anyPage);
		return list;
	}


	@Override
	public int endDateMemberCount() {
		// TODO Auto-generated method stub
		int cnt = dao.endDateMemberCount();
		return cnt;
	}
	
	
	
	
	
	
	
	


	@Override
	public List<ManagerInfo> managerSearchIdPw() {
		// TODO Auto-generated method stub
		return dao.managerSearchIdPw();
	}


	@Override
	public int shooseCageInsert(ShoesCage cage) {
		// TODO Auto-generated method stub
		return dao.shooseCageInsert(cage);
	}


	@Override
	public int updateShooesNoForList(int shooesNo, int memberNo) {
		// TODO Auto-generated method stub
		return dao.updateShooesNoForList(shooesNo, memberNo);
	}


	@Override
	public int ShoesCageStop(int memberNo) {
		// TODO Auto-generated method stub
		return dao.ShoesCageStop(memberNo);
	}


	@Override
	public int shoesNoValueZero(int memberNo) {
		// TODO Auto-generated method stub
		return dao.shoesNoValueZero(memberNo);
	}


	@Override
	public List<Integer> usingShooseNo() {
		// TODO Auto-generated method stub
		return dao.usingShooseNo();
	}


	@Override
	public List<ShoesCage> endedShoeNo() {
		// TODO Auto-generated method stub
		return dao.endedShoeNo();
	}



	@Override
	public List<Integer> memberNoCheck() {
		// TODO Auto-generated method stub
		return dao.memberNoCheck();
	}









}
