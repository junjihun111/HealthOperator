package com.health.member.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.health.member.dao.MemberDao;
import com.health.spring.vo.Attendance;
import com.health.spring.vo.CodeTable;
import com.health.spring.vo.HealthMember;
import com.health.spring.vo.ManagerInfo;
import com.health.spring.vo.ShoesCage;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private SqlSessionTemplate session;
	
	
	@Override
	public List<HealthMember> selectMemberList() {
		// TODO Auto-generated method stub
		return session.selectList("selectMemberList");
	}
	


	@Override
	public List<HealthMember> nameSearchMemberList(String memberName) {
		// TODO Auto-generated method stub
		return session.selectList("selectMemberByName",memberName);
	}

	@Override
	public int nameSearchMemberCount(String memberName) {
		// TODO Auto-generated method stub
		return session.selectOne("SearchByNameCount",memberName);
	}
	

	@Override
	public HealthMember idSearchMemberList(int id) {
		// TODO Auto-generated method stub
		return session.selectOne("selectMemberById",id);
	}

	@Override
	public List<CodeTable> selectCodePhoneNumber() {
		// TODO Auto-generated method stub
		return session.selectList("selectCodePhoneNumber");
	}

	@Override
	public int insertMember(HealthMember member) {
		// TODO Auto-generated method stub
		return session.insert("insertMember", member);
	}

	@Override
	public ShoesCage ShoesCagebyNo(int memberNo) {
		// TODO Auto-generated method stub
		return session.selectOne("ShoesCagebyNo", memberNo);
	}

	@Override
	public int insertAttendance(Attendance att) {
		
		return session.insert("attendInsertbyNo",att);
	}

	@Override
	public List<Attendance> oneMemberAttendList(int memberNo) {
		// TODO Auto-generated method stub
		return session.selectList("oneMemberAttendList", memberNo);
	}

	@Override
	public int oneMemberAttendCount(int memberNo) {
		// TODO Auto-generated method stub
		return session.selectOne("oneMemberAttendCount",memberNo);
	}

	@Override
	public int deleteByAttend(List attendanceDates, int id) {
		// TODO Auto-generated method stub
		HashMap param = new HashMap();
		param.put("attendanceDates",attendanceDates);
		param.put("id", id);
		return session.delete("deleteByAttend", param);
	}

	@Override
	public int deleteByMemberNo(List memberNos) {
		// TODO Auto-generated method stub
		return session.delete("deleteByMemberNo", memberNos);
	}

	@Override
	public int updateMemberByNo(HealthMember member) {
		// TODO Auto-generated method stub
		return session.update("updateMemberById",member);
	}

	@Override
	public List<HealthMember> getMemberPageList(int memberPerpageAmout, int anyPage) {
		// TODO Auto-generated method stub
		Map<String,Integer> param = new HashMap<>();
		param.put("memberPerpageAmout", memberPerpageAmout);
		param.put("anyPage", anyPage);
		return session.selectList("selectMemberListPaging",param);
	}

	@Override
	public int selectMemberCount() {
		// TODO Auto-generated method stub
		return session.selectOne("selectCountMember");
	}
	
	@Override
	public List<HealthMember> endDateMember(int memberPerpageAmout, int anyPage) {
		Map<String,Integer> param = new HashMap<>();
		param.put("memberPerpageAmout", memberPerpageAmout);
		param.put("anyPage", anyPage);
		return session.selectList("endDateMember",param);
	}

	@Override
	public int endDateMemberCount() {
		// TODO Auto-generated method stub
		return session.selectOne("endDateMemberCount");
	}
	
	
	

	@Override
	public List<ManagerInfo> managerSearchIdPw() {
		// TODO Auto-generated method stub
		return session.selectList("managerSearchIdPw");
	}

	@Override
	public int shooseCageInsert(ShoesCage cage) {
		return session.insert("shoesCageMember",cage);
	}

	@Override
	public int updateShooesNoForList( int shoesNo, int memberNo) {
		// TODO Auto-generated method stub
		HashMap param = new HashMap<>();
		param.put("memberNo", memberNo);
		param.put("shoesNo", shoesNo);
		System.out.println(param.get("memberNo")+"임");
		System.out.println(param.get("shoesNo")+"임");
		return session.update("updateShooesNoForList",param);
	}

	@Override
	public int ShoesCageStop(int memberNo) {
		// TODO Auto-generated method stub
		return session.delete("deleteByMemberNoForView",memberNo);
	}

	@Override
	public int shoesNoValueZero(int memberNo) {
		// TODO Auto-generated method stub
		return session.delete("shoesNoValueZero",memberNo);
	}

	@Override
	public List<Integer> usingShooseNo() {
		// TODO Auto-generated method stub
		return session.selectList("usingShooseNo");
	}

	@Override
	public List<ShoesCage> endedShoeNo() {
		// TODO Auto-generated method stub
		return session.selectList("endedShoeNo");
	}



	@Override
	public List<Integer> memberNoCheck() {
		// TODO Auto-generated method stub
		return session.selectList("memberNoCheck");
	}






	

	

	
	
}
