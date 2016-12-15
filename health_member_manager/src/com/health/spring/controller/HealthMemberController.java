package com.health.spring.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceView;

import com.health.member.service.MemberService;
import com.health.member.service.impl.MemberServiceImpl;
import com.health.spring.vo.Attendance;
import com.health.spring.vo.HealthMember;
import com.health.spring.vo.ManagerInfo;
import com.health.spring.vo.ShoesCage;
import com.health.util.PagingBean;
import com.health.validator.HealthMemberForm;
import com.health.validator.ManagerInfoForm;
import com.health.validator.ShoesCageForm;

@Controller
@RequestMapping("/health")
public class HealthMemberController {

	@Autowired
	private MemberService service;


	@RequestMapping("/searchIdPw")
	public String searchIdPw(@ModelAttribute @Valid ManagerInfoForm form, BindingResult errors, ModelMap map) {

		ManagerInfo info = new ManagerInfo();
		BeanUtils.copyProperties(form, info);
		
		System.out.println(info.getManagerEmail() + "과" + info.getSerialNum());
		List<ManagerInfo> list = service.managerSearchIdPw();
		ManagerInfo manager = list.get(0);

		String failMessage = " ";
		if (!(info.getManagerEmail().equals(manager.getManagerEmail()))) {
			failMessage = failMessage + "이메일주소 가 틀렸습니다.";
		}
		if (!(info.getSerialNum().equals(manager.getSerialNum()))) {
			failMessage = failMessage + "주민등록번호 네자리가 틀렸습니다.";
		}
		if (!(info.getManagerEmail().equals(manager.getManagerEmail()))
				|| !(info.getSerialNum().equals(manager.getSerialNum()))) {
			map.addAttribute("failMessage", failMessage);
			return "/login/idPwSearchPage.jsp";
		}
		System.out.println(manager);

		if (info.getManagerEmail().equals(manager.getManagerEmail())) {
			if (info.getSerialNum().equals(manager.getSerialNum())) {
				String successMessage = "아이디 : " + manager.getManagerId() + "패스워드 :" + manager.getManagerPw();
				map.addAttribute("successMessage", successMessage);
				return "/login/idPwSearchPage.jsp";
			}
		}
		return "/login/idPwSearchPage.jsp";
	}

	@RequestMapping("/manager")
	public String manager(@ModelAttribute @Valid ManagerInfo mif, BindingResult errors,
			@RequestParam(defaultValue = "1") int page, HttpServletRequest request, HttpSession session, ModelMap map) {
		List<ManagerInfo> list = service.managerSearchIdPw();
		ManagerInfo manager = list.get(0);
		System.out.println(mif.getManagerId() + "," + mif.getManagerPw());

		String failMessage = " ";
		if (!(manager.getManagerId().equals(mif.getManagerId()))) {
			failMessage = "아이디가 틀렸습니다.";
		}
		if (!(manager.getManagerPw().equals(mif.getManagerPw()))) {
			failMessage = failMessage + "비밀번호가 틀렸습니다.";
		}

		if (!(manager.getManagerId().equals(mif.getManagerId()))
				|| !(manager.getManagerPw().equals(mif.getManagerPw()))) {
			map.addAttribute("failMessage", failMessage);
			return "/login/request.jsp";
		}

		if (manager.getManagerId().equals(mif.getManagerId())) {
			if (manager.getManagerPw().equals(mif.getManagerPw())) {

				System.out.println("메인" + page + "페이지입니다.");
				List lists = service.getMemberPageList(page);
				PagingBean bean = new PagingBean(service.selectCountMember(), page);
				session.setAttribute("codeValue", service.selectCodePhoneNumber());
				session.setAttribute("healthMemberList", lists);
				session.setAttribute("pageBean", bean);
				return "/main/managerpage.jsp";
			}
		}
		return "/login/request.jsp";
	}
	
	@RequestMapping("/joinMember")
	public String joinmember(@ModelAttribute @Valid HealthMemberForm form, BindingResult errors, HttpSession session) {


		List<ObjectError> list = errors.getAllErrors();
		for (ObjectError e : list) {
			System.out.println(e.getObjectName() + " " + e.getDefaultMessage());
		}

		HealthMember member = new HealthMember();
		BeanUtils.copyProperties(form, member);
		Random random = new Random();
		int number = random.nextInt(9999999-1000000+1)+1000000;
		for(int idx=0; idx<service.memberNoCheck().size(); idx++){
			if(service.memberNoCheck().get(idx)==number){
				number = random.nextInt(9999999-1000000+1)+1000000;
				idx=0;
				continue;
			}
		}
		member.setMemberNo(number);
		if (errors.hasErrors()) {
			// return new InternalResourceView("/health/healthMemberPaging.do");
			System.out.println("실패했습니다.");
			System.out.println("에러는" + errors.getErrorCount() + "개입니다.");
			return "/join/memberJoin.jsp";
		}
		int cnt = service.insertMember(member);
		System.out.println(cnt + "명이 등록되었습니다.");
		int page = 1;
		PagingBean bean = new PagingBean(service.selectCountMember(), page);
		List<HealthMember> memberlist = service.getMemberPageList(page);
		session.setAttribute("healthMemberList", memberlist);
		session.setAttribute("pageBean", bean);
		return "/main/managerpage.jsp";
	}
	
	
	
	
	/* 메인페이지에 대한 처리*/
	
	@RequestMapping("/healthMemberPaging")
	public String healthMemberPaging(HttpServletRequest request, HttpSession session,
			@RequestParam(defaultValue = "1") int page) {
		
		session.removeAttribute("healthEndedMemberList");
		session.removeAttribute("searchNo");
		session.removeAttribute("searchName");
		
		System.out.println(page + "");
		PagingBean bean = new PagingBean(service.selectCountMember(), page);
		List<HealthMember> list = service.getMemberPageList(page);
		session.setAttribute("healthMemberList", list);
		session.setAttribute("pageBean", bean);
		return "/main/managerpage.jsp";
	}
	
	@RequestMapping("/shoescage")
	public String shoescage(String name, int memberNo, @RequestParam(defaultValue = "1") int page, ModelMap map,
			String memberName, HttpServletRequest request, HttpSession session) {
		System.out.println(memberNo + "번입니다.");
		System.out.println(memberName + "노래잘한다.");
		// 등록된 한 회원의 신발장 정보를 가져오는 메소드

		PagingBean bean = new PagingBean(service.selectCountMember(), page);
		List<HealthMember> lists = service.getMemberPageList(page);
		
		session.setAttribute("healthMemberList", lists);
		session.setAttribute("pageBean", bean);

		ShoesCage sc = service.ShoesCagebyNo(memberNo);
		map.addAttribute("memberNo", memberNo);
		map.addAttribute("shoescage", sc);// memberNo, shoesno, startday, endDay  정보가 있겠지
		return "/main/managerpage.jsp";
	}

	@RequestMapping("/shoesRegister")
	public String shoesRegister(@ModelAttribute @Valid ShoesCageForm form, BindingResult errors, String name,
			@RequestParam(defaultValue = "1") int page, HttpSession session, ModelMap map) {
		System.out.println("page는" + page + "입니다");
		System.out.println("name은" + name);
		// 칼럼찾은 후 수정 cage.getMember(), cage.getNo값 적어서
		ShoesCage cage = new ShoesCage();
		BeanUtils.copyProperties(form, cage);
		System.out.println(cage.getMemberNo() + "번이다.");

		// 중복한 번호를 등록했을경우 클라이언트로 보내줄 오류 메세지
		List usingList = service.usingShooseNo();
		for (int idx = 0; idx < usingList.size(); idx++) {
			if (usingList.get(idx).equals(cage.getShoesNo())) {
				String DulplicateMessage = "신발장번호가 중복되었습니다.";
				map.addAttribute("DulplicateMessage", DulplicateMessage);
				map.addAttribute("memberNo", cage.getMemberNo());

				PagingBean bean = new PagingBean(service.selectCountMember(), page);
				List<HealthMember> lists = service.getMemberPageList(page);
				
				session.setAttribute("healthMemberList", lists);
				session.setAttribute("pageBean", bean);
				return "/main/managerpage.jsp";
			}
		}
		// 에러가 존재한다면 에러메시지를 가지고 다시 managerpage.jsp로 이동
		if (errors.hasErrors()) {
			System.out.println(cage);
			System.out.println("에러는" + errors.getErrorCount() + "개입니다.");
			map.addAttribute("errors", errors);
			map.addAttribute("memberNumber", cage.getMemberNo());

			PagingBean bean = new PagingBean(service.selectCountMember(), page);
			List<HealthMember> lists = service.getMemberPageList(page);
			
			session.setAttribute("healthMemberList", lists);
			session.setAttribute("pageBean", bean);
			return "/main/managerpage.jsp";
		}

		System.out.println(service.updateShooesNoForList(cage.getShoesNo(), cage.getMemberNo()) + "개의 행이 변경되었습니다.");
		System.out.println(service.shooseCageInsert(cage) + "개의 행이 입력되었습니다.");

		// 페이지를 유지하기 위해서 써주는것.
		PagingBean bean = new PagingBean(service.selectCountMember(), page);
		List<HealthMember> lists = service.getMemberPageList(page);
		session.setAttribute("healthMemberList", lists);
		session.setAttribute("pageBean", bean);
		return "/main/managerpage.jsp";
	}
	
	@RequestMapping("/ShoesCageStop")
	public String ShoesCageStop(String name, int memberNo, int page, HttpSession session, ModelMap map) {
		System.out.println(page + "페지입니다.");
		// System.out.println(memberNo+"번이에요");
		System.out.println(service.ShoesCageStop(memberNo) + "개 행이 삭제되었습니다.");
		System.out.println(service.shoesNoValueZero(memberNo) + "개 shoesNumber 삭제");

		PagingBean bean = new PagingBean(service.selectCountMember(), page);
		List<HealthMember> list = service.getMemberPageList(page);
		
		session.setAttribute("healthMemberList", list);
		session.setAttribute("pageBean", bean);
		return "/main/managerpage.jsp";
	}
	
	@RequestMapping("/updateTransfer")
	public String updateMemberPage(@ModelAttribute @Valid HealthMemberForm form, BindingResult errors,
			HttpSession session, @RequestParam(defaultValue = "1") int page, ModelMap map) {

		HealthMember member = new HealthMember();
		BeanUtils.copyProperties(form, member);

		if (errors.hasErrors()) {
			System.out.println(errors.getErrorCount() + "개 에러");
			map.addAttribute("updateErrors", errors);
			map.addAttribute("updateMember", member);
			return "/main/managerpage.jsp";
		}

		System.out.println("현재페이지는 " + page);
		System.out.println(service.updateMemberByNo(member) + "행이 수정되었습니다.");
		PagingBean bean = new PagingBean(service.selectCountMember(), page);
		List<HealthMember> list = service.getMemberPageList(page);
		session.setAttribute("healthMemberList", list);
		session.setAttribute("pageBean", bean);
		return "/main/managerpage.jsp";
	}

	// 만료된 신발장 조회
	@RequestMapping("/endedShoeNo")
	public String endedShoeNo(int page, ModelMap map) {
		System.out.println(page);
		List<ShoesCage> list = service.endedShoeNo();
		map.addAttribute("endedShoeCageList", list);
		return	"/main/managerpage.jsp";
	}
		
	@RequestMapping("/deleteEndedShoesCage")
	public String deleteEndedShoedCage(@RequestParam(defaultValue="1") String [] memberNo, ModelMap map){
		
		
		String endedShoesCageFailMessage = "";
		//검증을 위해서 일부러 1이라는 값이 들어오면 그전페이지로 돌아가 오류메세지를 뿌릴거임
		if(memberNo[0].equals("1")){
			List<ShoesCage> list  = service.endedShoeNo();
			map.addAttribute("endedShoeCageList", list);
			endedShoesCageFailMessage="체크된 값이 존재하지 않습니다.";
			map.addAttribute("endedShoesCageErrorMessage",endedShoesCageFailMessage);
			return "/main/managerpage.jsp";
		}
		
		List<Integer> list = new ArrayList();
		for(int idx=0;idx<memberNo.length;idx++){
			list.add(Integer.parseInt(memberNo[idx]));
		}
		
		// 삭제  받아온 list를 for문을 돌려서 삭제하는 방법.
		int cnt=0;
		for(int idx=0; idx<list.size(); idx++){
		int count =service.ShoesCageStop(list.get(idx));
		System.out.println(service.shoesNoValueZero(list.get(idx)) + "개 shoesNumber 삭제");
		cnt = cnt+count;
		System.out.println(cnt+"개의 행을 신발장을 삭제 하였습니다.");
		}
		return "/main/managerpage.jsp";
	}
	
	@RequestMapping("/attendance")
	public String attandance(int memberNo, String memberName, ModelMap map) {
		System.out.println(memberNo + "와" + memberName);
		Date date = new Date();
		Attendance att = new Attendance(memberNo, memberName, date);
		System.out.println(service.insertAttendance(att));
		map.addAttribute("attMessage", "출석 되었습니다.");
		return "/main/managerpage.jsp";
	}
	
	@RequestMapping("/attendanceList")
	public String attendanceList(int memberNo, String memberName, int page, ModelMap map) {
		System.out.println(page);

		// 한명의 출석리스트를 no를 가지고 뽑는것
		map.addAttribute("attendList", service.oneMemberAttendList(memberNo));

		// 한명의 출석 수를 가지고 오는것.
		map.addAttribute("attendCount", service.oneMemberAttendCount(memberNo));
		return "/attendance/attendanceList.jsp";
	}
	

	
	/*--------------------------------------------------------- 만료된 회원에 대한 조회------------------------------------------- */	
	
	@RequestMapping("/endedMember")
	public String endedMember(HttpSession session, @RequestParam(defaultValue="1") int page) {
		session.removeAttribute("healthMemberList");
		session.removeAttribute("searchNo");
		session.removeAttribute("searchName");
		
		PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
		List<HealthMember> list = service.endDateMember(page);
		session.setAttribute("healthEndedMemberList", list);
		session.setAttribute("pageBean", bean);
		return "/search/endedMemberSearch.jsp";
	}
	
	
	@RequestMapping("/endedMemshoescage")
	public String endedMemshoescage(String name, int memberNo, @RequestParam(defaultValue = "1") int page, ModelMap map,
		 HttpServletRequest request, HttpSession session) {
		System.out.println(memberNo + "번입니다.");
		// 등록된 한 회원의 신발장 정보를 가져오는 메소드

		PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
		List<HealthMember> lists = service.endDateMember(page);
		session.setAttribute("healthEndedMemberList", lists);
		session.setAttribute("pageBean", bean);

		ShoesCage sc = service.ShoesCagebyNo(memberNo);
		map.addAttribute("memberNo", memberNo);
		map.addAttribute("shoescage", sc);// memberNo, shoesno, startday, endDay
											// 정보가 있겠지
		return "/search/endedMemberSearch.jsp";
	}
	
	
	@RequestMapping("/endedMemshoesRegister")
	public String endedMemshoesRegister(@ModelAttribute @Valid ShoesCageForm form, BindingResult errors, 
			@RequestParam(defaultValue = "1") int page, HttpSession session, ModelMap map) {
		System.out.println("page는" + page + "입니다");
		// 칼럼찾은 후 수정 cage.getMember(), cage.getNo값 적어서
		ShoesCage cage = new ShoesCage();
		BeanUtils.copyProperties(form, cage);
		System.out.println(cage.getMemberNo() + "번이다.");

		// 중복한 번호를 등록했을경우 클라이언트로 보내줄 오류 메세지
		List usingList = service.usingShooseNo();
		for (int idx = 0; idx < usingList.size(); idx++) {
			if (usingList.get(idx).equals(cage.getShoesNo())) {
				String DulplicateMessage = "신발장번호가 중복되었습니다.";
				map.addAttribute("DulplicateMessage", DulplicateMessage);
				map.addAttribute("memberNo", cage.getMemberNo());

				PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
				List<HealthMember> lists = service.endDateMember(page);
				session.setAttribute("healthEndedMemberList", lists);
				session.setAttribute("pageBean", bean);
				return "/search/endedMemberSearch.jsp";
			}
		}

		// 에러가 존재한다면 에러메시지를 가지고 다시 managerpage.jsp로 이동
		if (errors.hasErrors()) {
			System.out.println(cage);
			System.out.println("에러는" + errors.getErrorCount() + "개입니다.");
			map.addAttribute("errors", errors);
			map.addAttribute("memberNumber", cage.getMemberNo());

			PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
			List<HealthMember> lists = service.endDateMember(page);
			session.setAttribute("healthEndedMemberList", lists);
			session.setAttribute("pageBean", bean);
			return "/search/endedMemberSearch.jsp";
		}

		System.out.println(service.updateShooesNoForList(cage.getShoesNo(), cage.getMemberNo()) + "개의 행이 변경되었습니다.");
		System.out.println(service.shooseCageInsert(cage) + "개의 행이 입력되었습니다.");

		// 페이지를 유지하기 위해서 써주는것.
		PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
		List<HealthMember> lists = service.endDateMember(page);
		session.setAttribute("healthEndedMemberList", lists);
		session.setAttribute("pageBean", bean);
		return "/search/endedMemberSearch.jsp";
	}
	
	@RequestMapping("/endedMemShoesCageStop")
	public String endedMemShoesCageStop(int memberNo, int page, HttpSession session) {
		System.out.println(page + "페지입니다.");
		System.out.println(service.ShoesCageStop(memberNo) + "개 행이 삭제되었습니다.");
		System.out.println(service.shoesNoValueZero(memberNo) + "개 shoesNumber 삭제");

		PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
		List<HealthMember> list = service.endDateMember(page);
		session.setAttribute("healthEndedMemberList", list);
		
		session.setAttribute("pageBean", bean);
		return "/search/endedMemberSearch.jsp";
	}
	
	@RequestMapping("/updateTransferEndedMem")
	public String updateTransferEndedMem(@ModelAttribute @Valid HealthMemberForm form, BindingResult errors,
			HttpSession session, @RequestParam(defaultValue = "1") int page, ModelMap map) {

		HealthMember member = new HealthMember();
		BeanUtils.copyProperties(form, member);

		if (errors.hasErrors()) {
			System.out.println(errors.getErrorCount() + "개 에러");
			map.addAttribute("updateErrors", errors);
			map.addAttribute("updateMember", member);
			PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
			List<HealthMember> list = service.endDateMember(page);
			session.setAttribute("healthEndedMemberList", list);
			session.setAttribute("pageBean", bean);
			return "/search/endedMemberSearch.jsp";
		}

		System.out.println("현재페이지는 " + page);
		System.out.println(service.updateMemberByNo(member) + "행이 수정되었습니다.");
		
		
		PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
		List<HealthMember> list = service.endDateMember(page);
		session.setAttribute("healthEndedMemberList", list);
		session.setAttribute("pageBean", bean);
		return "/search/endedMemberSearch.jsp";
	}
	
	@RequestMapping("/endedShoeNoEndedMem")
	public String endedShoeNoEndedMem(int page, ModelMap map) {
		System.out.println(page);
		List<ShoesCage> list = service.endedShoeNo();
		map.addAttribute("endedShoeCageList", list);
		return	"/search/endedMemberSearch.jsp";
	}
	
	@RequestMapping("/deleteEndedShoesCageEndedMem")
	public String deleteEndedShoesCageEndedMem(@RequestParam(defaultValue="1") String [] memberNo, ModelMap map){
		
		
		String endedShoesCageFailMessage = "";
		//검증을 위해서 일부러 1이라는 값이 들어오면 그전페이지로 돌아가 오류메세지를 뿌릴거임
		if(memberNo[0].equals("1")){
			List<ShoesCage> list  = service.endedShoeNo();
			map.addAttribute("endedShoeCageList", list);
			endedShoesCageFailMessage="체크된 값이 존재하지 않습니다.";
			map.addAttribute("endedShoesCageErrorMessage",endedShoesCageFailMessage);
			return "/search/endedMemberSearch.jsp";
		}
		
		List<Integer> list = new ArrayList();
		for(int idx=0;idx<memberNo.length;idx++){
			list.add(Integer.parseInt(memberNo[idx]));
		}
		// 삭제  받아온 list를 for문을 돌려서 삭제하는 방법.
		int cnt=0;
		for(int idx=0; idx<list.size(); idx++){
		int count =service.ShoesCageStop(list.get(idx));
		System.out.println(service.shoesNoValueZero(list.get(idx)) + "개 shoesNumber 삭제");
		cnt = cnt+count;
		System.out.println(cnt+"개의 행을 신발장을 삭제 하였습니다.");
		}
		return "/search/endedMemberSearch.jsp";
	}
	
	@RequestMapping("/endedMemattendanceList")
	public String endedMemattendanceList(int memberNo, int page, ModelMap map) {
		System.out.println(page);

		// 한명의 출석리스트를 no를 가지고 뽑는것
		map.addAttribute("attendList", service.oneMemberAttendList(memberNo));

		// 한명의 출석 수를 가지고 오는것.
		map.addAttribute("attendCount", service.oneMemberAttendCount(memberNo));
		return "/attendance/attendanceListEndedMem.jsp";
	}
	
	
	
	
	
	/*************************************** No 조회에 대한 Handler ************************************/
	
	@RequestMapping("/noSearch")
	public String noSearch(@RequestParam int searchNo, ModelMap map, HttpSession session) {
		session.removeAttribute("healthMemberList");
		session.removeAttribute("healthEndedMemberList");
		session.removeAttribute("pageBean");
		
		System.out.println("번호는" + searchNo);
		HealthMember member = service.IdSearchMemberList(searchNo);
		System.out.println(member);
		map.addAttribute("healthMemberNoList", member);
		session.setAttribute("searchNo",searchNo);
		return "/search/noSearch.jsp";
	}
	
	@RequestMapping("/noSearchUpdate")
	@ResponseBody
	public HealthMember noSearchUpdate(int no, /*HttpSession session,*/ @RequestParam(defaultValue="1") int page) {
		System.out.println("번호는" + no);
		HealthMember member = service.IdSearchMemberList(no);
		System.out.println(member);
		return member;
	}
	
	@RequestMapping("/updateTransferByNo")
	public String updateTransferByNo(@ModelAttribute @Valid HealthMemberForm form, BindingResult errors,
			HttpSession session, @RequestParam(defaultValue = "1") int page, ModelMap map, int memberNo) {

		HealthMember member = new HealthMember();
		BeanUtils.copyProperties(form, member);

		if (errors.hasErrors()) {
			System.out.println(errors.getErrorCount() + "개 에러");
			map.addAttribute("updateErrors", errors);
			map.addAttribute("updateMember", member);
			
			HealthMember mem =service.IdSearchMemberList(memberNo);
			map.addAttribute("healthMemberNoList", mem);
			return "/search/noSearch.jsp";
		}
		System.out.println(service.updateMemberByNo(member) + "행이 수정되었습니다.");
		HealthMember mem =service.IdSearchMemberList(memberNo);
		map.addAttribute("healthMemberNoList", mem);
		return "/search/noSearch.jsp";
	}
	
	
	
	@RequestMapping("/shoescageBymemberNo")
	public String shoescageByNoList(String name, int memberNo, @RequestParam(defaultValue = "1") int page, ModelMap map,
			String memberName, HttpServletRequest request, HttpSession session) {
		System.out.println(memberNo + "번입니다.");
		System.out.println(memberName + "노래잘한다.");
		// 등록된 한 회원의 신발장 정보를 가져오는 메소드

		HealthMember member = service.IdSearchMemberList(memberNo);
		map.addAttribute("healthMemberNoList", member);
		
		ShoesCage sc = service.ShoesCagebyNo(memberNo);
		map.addAttribute("memberNo", memberNo);
		map.addAttribute("shoescage", sc);// memberNo, shoesno, startday, endDay
											// 정보가 있겠지
		return "/search/noSearch.jsp";
	}
	
	@RequestMapping("/shoesRegisterByNo")
	public String shoesRegisterByNo(@ModelAttribute @Valid ShoesCageForm form, BindingResult errors, String name,
			@RequestParam(defaultValue = "1") int page, HttpSession session, ModelMap map, int memberNo) {
		System.out.println("page는" + page + "입니다");
		System.out.println("name은" + name);
		// 칼럼찾은 후 수정 cage.getMember(), cage.getNo값 적어서
		ShoesCage cage = new ShoesCage();
		BeanUtils.copyProperties(form, cage);
		System.out.println(cage.getMemberNo() + "번이다.");

		// 중복한 번호를 등록했을경우 클라이언트로 보내줄 오류 메세지
		List usingList = service.usingShooseNo();
		for (int idx = 0; idx < usingList.size(); idx++) {
			if (usingList.get(idx).equals(cage.getShoesNo())) {
				String DulplicateMessage = "신발장번호가 중복되었습니다.";
				map.addAttribute("DulplicateMessage", DulplicateMessage);
				map.addAttribute("memberNo", cage.getMemberNo());

				HealthMember member = service.IdSearchMemberList(memberNo);
				System.out.println(member);
				map.addAttribute("healthMemberNoList", member);
				return "/search/noSearch.jsp";
			}
		}

		// 에러가 존재한다면 에러메시지를 가지고 다시 managerpage.jsp로 이동
		if (errors.hasErrors()) {
			System.out.println(cage);
			System.out.println("에러는" + errors.getErrorCount() + "개입니다.");
			map.addAttribute("errors", errors);
			map.addAttribute("memberNo", cage.getMemberNo());

			HealthMember member = service.IdSearchMemberList(memberNo);
			System.out.println(member);
			map.addAttribute("healthMemberNoList", member);
			return "/search/noSearch.jsp";
		}

		System.out.println(service.updateShooesNoForList(cage.getShoesNo(), cage.getMemberNo()) + "개의 행이 변경되었습니다.");
		System.out.println(service.shooseCageInsert(cage) + "개의 행이 입력되었습니다.");

		// 페이지를 유지하기 위해서 써주는것.
		HealthMember member = service.IdSearchMemberList(memberNo);
		System.out.println(member);
		map.addAttribute("healthMemberNoList", member);
		return "/search/noSearch.jsp";
	}
	
	@RequestMapping("/ShoesCageStopByNo")
	public String ShoesCageStopByNo(int memberNo, HttpSession session, ModelMap map) {
		
		System.out.println(memberNo+"번이에요");
		System.out.println(service.ShoesCageStop(memberNo) + "개 행이 삭제되었습니다.");
		System.out.println(service.shoesNoValueZero(memberNo) + "개 shoesNumber 삭제");

		HealthMember member = service.IdSearchMemberList(memberNo);
		System.out.println(member);
		map.addAttribute("healthMemberNoList", member);
		return "/search/noSearch.jsp";
	}


	@RequestMapping("/attendanceByNo")
	public String attendanceByNo(int memberNo, String memberName, ModelMap map, HttpSession session) {
		System.out.println(memberNo + "와" + memberName);
		Date date = new Date();
		Attendance att = new Attendance(memberNo, memberName, date);
		System.out.println(service.insertAttendance(att));
		map.addAttribute("attMessage", "출석 되었습니다.");
		String name = (String) session.getAttribute("name");
		
		HealthMember member = service.IdSearchMemberList(memberNo);
		map.addAttribute("healthMemberNoList", member);
		return "/search/noSearch.jsp";
	}
	
	@RequestMapping("/endedShoeNoEndNoListPage")
	public String endedShoeNoEndNoListPage(int searchNo, ModelMap map, HttpSession session) {
		
		//페이지 처리때문에 설정해주는 것.
		HealthMember member = service.IdSearchMemberList(searchNo);
		map.addAttribute("healthMemberNoList", member);
		
		List<ShoesCage> list = service.endedShoeNo();
		map.addAttribute("endedShoeCageList", list);
		return	"/search/noSearch.jsp";
	}
	
	@RequestMapping("/deleteEndedShoesCageForNoPage")
	public String deleteEndedShoesCageForNoPage(@RequestParam(defaultValue="1") String [] memberNo, ModelMap map, int searchNo){
		
		String endedShoesCageFailMessage = "";
		//검증을 위해서 일부러 1이라는 값이 들어오면 그전페이지로 돌아가 오류메세지를 뿌릴거임
		if(memberNo[0].equals("1")){
			
			HealthMember member = service.IdSearchMemberList(searchNo);
			System.out.println(member);
			map.addAttribute("healthMemberNoList", member);
			
			List<ShoesCage> list  = service.endedShoeNo();
			map.addAttribute("endedShoeCageList", list);
			endedShoesCageFailMessage="체크된 값이 존재하지 않습니다.";
			map.addAttribute("endedShoesCageErrorMessage",endedShoesCageFailMessage);
			return "/search/noSearch.jsp";
		}
		
		List<Integer> list = new ArrayList();
		for(int idx=0;idx<memberNo.length;idx++){
			list.add(Integer.parseInt(memberNo[idx]));
		}
		// 삭제  받아온 list를 for문을 돌려서 삭제하는 방법.
		int cnt=0;
		for(int idx=0; idx<list.size(); idx++){
		int count =service.ShoesCageStop(list.get(idx));
		System.out.println(service.shoesNoValueZero(list.get(idx)) + "개 shoesNumber 삭제");
		cnt = cnt+count;
		System.out.println(cnt+"개의 행을 신발장을 삭제 하였습니다.");
		}
		HealthMember member= service.IdSearchMemberList(searchNo);
		map.addAttribute("healthMemberNoList", member);
		
		return "/search/noSearch.jsp";
	}
	
	
	@RequestMapping("/attendanceListByNoList")
	public String attendanceByNoList(int memberNo,ModelMap map) {

		// 한명의 출석리스트를 no를 가지고 뽑는것
		map.addAttribute("attendList", service.oneMemberAttendList(memberNo));
		map.addAttribute("memberNo",memberNo);
		// 한명의 출석 수를 가지고 오는것.
		map.addAttribute("attendCount", service.oneMemberAttendCount(memberNo));
		return "/attendance/attendanceByNo.jsp";
	}
	
	
	
	
	/*************************************** Name 조회에 대한 Handler ************************************/
	
	@RequestMapping("/nameSearch")
	public String nameSearch(@RequestParam String name,  HttpServletRequest request, ModelMap map, HttpSession session) {
		session.removeAttribute("healthMemberList");
		session.removeAttribute("healthEndedMemberList");
		session.removeAttribute("pageBean");
		
		System.out.println(name+"이름입니다.");
		// 이름으로 조회 해서 값을 계속 유지해야하는애
		List<HealthMember> list = service.nameSearchMemberList(name);
		map.addAttribute("healthMemberNameList", list);
		session.setAttribute("searchName", name);
		return "/search/nameSearch.jsp";
	}


	@RequestMapping("/shoescageForNameList")
	public String shoescageForNameList(String searchName, int memberNo, @RequestParam(defaultValue = "1") int page,
			ModelMap map, String memberName, HttpServletRequest request, HttpSession session) {
		System.out.println("찾고자 하는 이름 : "+searchName);
		System.out.println(memberNo + "번입니다.");
		System.out.println(memberName + "노래잘한다.");
		
		// 등록된 한 회원의 신발장 정보를 가져오는 메소드
		List<HealthMember> list = service.nameSearchMemberList(searchName);
		map.addAttribute("healthMemberNameList", list);

		ShoesCage sc = service.ShoesCagebyNo(memberNo);
		map.addAttribute("memberNo", memberNo);
		map.addAttribute("shoescage", sc);// memberNo, shoesno, startday, endDay
											// 정보가 있겠지
		return "/search/nameSearch.jsp";
	}
	

	@RequestMapping("/shoesRegisterForNameList")
	public String shoesRegisterForNameList(@ModelAttribute @Valid ShoesCageForm form, BindingResult errors, String searchName,ModelMap map) {
		System.out.println("name은" + searchName);
		// 칼럼찾은 후 수정 cage.getMember(), cage.getNo값 적어서
		ShoesCage cage = new ShoesCage();
		BeanUtils.copyProperties(form, cage);
		System.out.println(cage.getMemberNo() + "번이다.");

		// 중복한 번호를 등록했을경우 클라이언트로 보내줄 오류 메세지
		List usingList = service.usingShooseNo();
		for (int idx = 0; idx < usingList.size(); idx++) {
			if (usingList.get(idx).equals(cage.getShoesNo())) {
				String DulplicateMessage = "신발장번호가 중복되었습니다.";
				map.addAttribute("DulplicateMessage", DulplicateMessage);
				map.addAttribute("memberNo", cage.getMemberNo());
				List<HealthMember> list = service.nameSearchMemberList(searchName);
				map.addAttribute("healthMemberNameList", list);
				return "/search/nameSearch.jsp";
			}
		}

	
		if (errors.hasErrors()) {
			System.out.println(cage);
			System.out.println("에러는" + errors.getErrorCount() + "개입니다.");
			map.addAttribute("errors", errors);
			map.addAttribute("memberNumber", cage.getMemberNo());
			List<HealthMember> list = service.nameSearchMemberList(searchName);
			map.addAttribute("healthMemberNameList", list);
			return "/search/nameSearch.jsp";
		}

		System.out.println(service.updateShooesNoForList(cage.getShoesNo(), cage.getMemberNo()) + "개의 행이 변경되었습니다.");
		System.out.println(service.shooseCageInsert(cage) + "개의 행이 입력되었습니다.");
		System.out.println("이름은"+searchName+"입니다.");
		List<HealthMember> list = service.nameSearchMemberList(searchName);
		map.addAttribute("healthMemberNameList", list);
		return "/search/nameSearch.jsp";
	}
	

	
	@RequestMapping("/ShoesCageStopNameList")
	public String ShoesCageStopNameList(String searchName, int memberNo, HttpSession session, ModelMap map) {
		System.out.println(searchName+"이름입니다.");
		System.out.println(service.ShoesCageStop(memberNo) + "개 행이 삭제되었습니다.");
		System.out.println(service.shoesNoValueZero(memberNo) + "개 shoesNumber 삭제");

		List<HealthMember> list = service.nameSearchMemberList(searchName);
		map.addAttribute("healthMemberNameList", list);
		return "/search/nameSearch.jsp";
	}
	
	
	@RequestMapping("/attendanceForNamList")
	public String attandanceForNamList(String searchName, int memberNo, String memberName, ModelMap map) {
		System.out.println(memberNo + "와" + memberName);
		Date date = new Date();
		Attendance att = new Attendance(memberNo, memberName, date);
		System.out.println(service.insertAttendance(att));
		map.addAttribute("attMessage", "출석 되었습니다.");
		List<HealthMember> list = service.nameSearchMemberList(searchName);
		map.addAttribute("healthMemberNameList", list);
		return "/search/nameSearch.jsp";
	}
	
	
	@RequestMapping("/updateTransferForName")
	public String updateTransferForName(@ModelAttribute @Valid HealthMemberForm form, BindingResult errors,
		ModelMap map ,String searchName) {

		HealthMember member = new HealthMember();
		BeanUtils.copyProperties(form, member);
		
		if (errors.hasErrors()) {
			System.out.println(errors.getErrorCount() + "개 에러");
			map.addAttribute("updateErrors", errors);
			map.addAttribute("updateMember", member);

			List<HealthMember> list = service.nameSearchMemberList(searchName);
			map.addAttribute("healthMemberNameList", list);
			return "/search/nameSearch.jsp";
		}
		System.out.println(service.updateMemberByNo(member) + "행이 수정되었습니다.");
		List<HealthMember> list = service.nameSearchMemberList(searchName);
		map.addAttribute("healthMemberNameList", list);
		return "/search/nameSearch.jsp";
	}
	

	
	@RequestMapping("/attendanceListForNameList")
	public String attendanceListForNamList(int memberNo, ModelMap map) {
		// 한명의 출석리스트를 no를 가지고 뽑는것
		map.addAttribute("attendList", service.oneMemberAttendList(memberNo));

		// 한명의 출석 수를 가지고 오는것.
		map.addAttribute("attendCount", service.oneMemberAttendCount(memberNo));
		return "/attendance/attendanceListForNameList.jsp";
	}
	
	@RequestMapping("/endedShoeNoEndNameListPage")
	public String endedShoeNoEndNameListPage(String searchName , ModelMap map) {
		
		//페이지 처리때문에 설정해주는 것.
		List<HealthMember> lists = service.nameSearchMemberList(searchName);
		map.addAttribute("healthMemberNameList", lists);
		
		
		List<ShoesCage> list = service.endedShoeNo();
		map.addAttribute("endedShoeCageList", list);
		return	"/search/nameSearch.jsp";
	}
	
	
	@RequestMapping("/deleteEndedShoesCageNameList")
	public String deleteEndedShoesCageNameList(@RequestParam(defaultValue="1") String [] memberNo, ModelMap map, String searchName){
		
		
		String endedShoesCageFailMessage = "";
		//검증을 위해서 일부러 1이라는 값이 들어오면 그전페이지로 돌아가 오류메세지를 뿌릴거임
		if(memberNo[0].equals("1")){
			
			List<HealthMember> lists = service.nameSearchMemberList(searchName);
			map.addAttribute("healthMemberNameList", lists);
			
			List<ShoesCage> list  = service.endedShoeNo();
			map.addAttribute("endedShoeCageList", list);
			endedShoesCageFailMessage="체크된 값이 존재하지 않습니다.";
			map.addAttribute("endedShoesCageErrorMessage",endedShoesCageFailMessage);
			return "/search/nameSearch.jsp";
		}
		
		List<Integer> list = new ArrayList();
		for(int idx=0;idx<memberNo.length;idx++){
			list.add(Integer.parseInt(memberNo[idx]));
		}
		// 삭제  받아온 list를 for문을 돌려서 삭제하는 방법.
		int cnt=0;
		for(int idx=0; idx<list.size(); idx++){
		int count =service.ShoesCageStop(list.get(idx));
		System.out.println(service.shoesNoValueZero(list.get(idx)) + "개 shoesNumber 삭제");
		cnt = cnt+count;
		System.out.println(cnt+"개의 행을 신발장을 삭제 하였습니다.");
		}
		List<HealthMember> lists = service.nameSearchMemberList(searchName);
		map.addAttribute("healthMemberNameList", lists);
		
		return "/search/nameSearch.jsp";
	}
	
	
	
	/*--------------------------------------------------Ajax 처리 ---------------------------------------*/
	
	@RequestMapping("/deleteByAttend")
	@ResponseBody
	public List<Attendance> deleteByAttend(@RequestParam List<String> attendanceDates, @RequestParam String id,
			ModelMap map, HttpSession session) throws ParseException {
		
		System.out.println(attendanceDates + "," + id);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Date> list = new ArrayList<>();
		for (int idx = 0; idx < attendanceDates.size(); idx++) {
			list.add(transFormat.parse(attendanceDates.get(idx)));
		}
		int ids = Integer.parseInt(id);
		int cnt = service.deleteByAttend(list, ids);
		System.out.println(cnt + "개의 행이 삭제되었습니다.");
		int chkCnt = service.oneMemberAttendCount(ids);
		
		List<Attendance> lists = service.oneMemberAttendList(ids);
		return lists;
	}

	@RequestMapping("/deleteByMemberNo")
	@ResponseBody
	public List<HealthMember> deleteByMemberNo(@RequestParam List<String> no, HttpServletRequest request,
			HttpSession session, int page) {
		System.out.println(no);
		System.out.println(page);
		List<Integer> delNoList = new ArrayList<>();
		for (int idx = 0; idx < no.size(); idx++) {
			delNoList.add(Integer.parseInt(no.get(idx)));
		}
		int cnt = service.deleteByMemberNo(delNoList);
		System.out.println(cnt + "행이 삭제되었습니다.");
		
		
		PagingBean bean = new PagingBean(service.selectCountMember(), page);
		List<HealthMember> memberlist = service.getMemberPageList(page);
		session.setAttribute("pageBean", bean);
		return memberlist;
	}
	

	
	@RequestMapping("/deleteByMemberNoForNameList")
	@ResponseBody
	public List<HealthMember> deleteByMemberNoForNameList(@RequestParam List<String> no, HttpServletRequest request,
			HttpSession session, String searchName) {
		System.out.println(no);
		List<Integer> delNoList = new ArrayList<>();
		for (int idx = 0; idx < no.size(); idx++) {
			delNoList.add(Integer.parseInt(no.get(idx)));
		}
		int cnt = service.deleteByMemberNo(delNoList);
		System.out.println(cnt + "행이 삭제되었습니다.");
		
		
		List<HealthMember> list = service.nameSearchMemberList(searchName);
		return list;
	}
	
	@RequestMapping("/deleteForEndedMember")
	@ResponseBody
	public List<HealthMember> deleteForEndedMember(@RequestParam List<String> no, HttpServletRequest request,
			HttpSession session, int page) {
		System.out.println(no);
		System.out.println(page);
		List<Integer> delNoList = new ArrayList<>();
		for (int idx = 0; idx < no.size(); idx++) {
			delNoList.add(Integer.parseInt(no.get(idx)));
		}
		int cnt = service.deleteByMemberNo(delNoList);
		System.out.println(cnt + "행이 삭제되었습니다.");
		
		
		PagingBean bean = new PagingBean(service.endDateMemberCount(), page);
		List<HealthMember> list = service.endDateMember(page);
		session.setAttribute("pageBean", bean);
		return list;
	}
	

	
	// 사용중인 신발장 조회
	@RequestMapping("/usingShooseNo")
	@ResponseBody
	public List<Integer> usingShooseNo() {
		List<Integer> list = service.usingShooseNo();
		return list;
	}


}
