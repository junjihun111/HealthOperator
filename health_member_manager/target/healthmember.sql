drop table attendance;
drop table health_shoes;
drop table health_member;

drop table codetable;
delete from HEALTH_MEMBER
delete from ATTENDANCE

create table health_member
(
   member_no number(7) primary key,
   shooes_no number(4) default null,
   member_name varchar2(20) not null,
   member_phonemiddle varchar2(4),
   member_phoneend varchar2(4) not null,
   member_startDay DATE not null,
   member_endDay DATE not null,
   member_birthDay DATE,
   member_address varchar2(100),
   member_email varchar2(50),
   member_codePhoneFront varchar2(50)
   
);	
create table attendance(
   member_no number(7) constraint health_fk2 references health_member(member_no) on delete cascade,
   member_name varchar2(30),
   attendance_date DATE
);
create table health_shoes
(
   shooes_no number(4) primary key,
   member_no number(7) constraint health_fk8 references health_member(member_no) on delete cascade,
   shooes_startDay DATE not null,
   shooes_endDay DATE not null
);

select shooes_no from health_shoes where shooes_no is not null;
create table codetable(
   CODE varchar2(50) primary key,
   CODEOPTION varchar2(20),
   CODEVALUE varchar2(20),
   EXPLAGIN varchar2(20)
);

create table manager(
   manager_id varchar2(20) primary key,
   manager_pw varchar2(20),
   serial_num varchar2(20),
   manager_email varchar2(20)
);


		  select member_no memberNo, shooes_no shoesNo, member_name memberName, member_phoneend phoneEnd, 
	  member_startDay startDay,member_endDay endDay
      from  (select ceil(rownum/#{memberPerpageAmout}) page, member_no, shooes_no, member_name, member_phoneend, member_startDay,member_endDay
               from(select member_no, shooes_no, member_name, member_phoneend, member_startDay,member_endDay
                       from health_member where member_name=#{memberName}
                       order by member_startDay desc
                      )
               )
      where page = #{anyPage}
      
      
      
  
      select board_no , board_header, board_name, board_content, board_date, board_count, board_password , board_writer
      from  (select ceil(rownum/#{itemPerPage}) page, board_no , board_header, board_name, board_content, board_date, board_count, board_password , board_writer
               from(select board_no , board_header, board_name, board_content, board_date, board_count, board_password , board_writer
                       from publicBoard
                       where board_name=#{option}
                       order by board_no desc
                      )
               )
      where page = #{page}

      






delete from health_member
select * from HEALTH_MEMBER
--회원 더미데이터
insert into health_member(member_no,member_name,member_phoneend,member_startDay,member_endDay)
values(5424105,'전지훈',8960,TO_DATE('2015-12-04 20:37:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2016-12-04 20:37:50','YYYY-MM-DD HH24:MI:SS'));
insert into health_member(member_no,member_name,member_phoneend,member_startDay,member_endDay)
values(2131813,'이한용',8123,TO_DATE('2016-09-04 20:12:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2016-11-09 20:37:50','YYYY-MM-DD HH24:MI:SS'));
insert into health_member(member_no,member_name,member_phoneend,member_startDay,member_endDay)
values(2131814,'박지우',9802,TO_DATE('2016-09-04 20:12:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2016-11-09 20:37:50','YYYY-MM-DD HH24:MI:SS'));

--신발장
insert into health_shoes values(7,9828965	,TO_DATE('2016-09-04 20:12:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2016-11-09 20:37:50','YYYY-MM-DD HH24:MI:SS'));

--코드테이블
insert into codetable values('a','phoneNum','010','자주쓰는번호');
insert into codetable values('b','phoneNum','017','간혹있는번호');
insert into codetable values('c','phoneNum','016','안보이는번호');
insert into codetable values('d','phoneNum','019','거의없는번호');

--attendence
insert into ATTENDANCE values(2131814,'박지우',TO_DATE('2016-10-04 20:12:50','YYYY-MM-DD HH24:MI:SS'));
insert into ATTENDANCE values(2131814,'박지우',TO_DATE('2016-10-05 20:12:50','YYYY-MM-DD HH24:MI:SS'));

-- 관리자 로그인
insert into manager values('manager','pw','1592','fnjdk@naver.com');

select  member_no memberNo,  member_name memberName,  member_phoneend phoneEnd, 
member_startDay startDay, member_endDay endDay, shooes_no shoesNo from Health_Member
where sysdate > member_endDay;
select  member_no memberNo,  member_name memberName,  member_phoneend phoneEnd, 
member_startDay startDay, member_endDay endDay, shooes_no shoesNo from Health_Member
where sysdate > member_endDay;


		  select member_no memberNo, shooes_no shoesNo, member_name memberName, member_phoneend phoneEnd, 
	  member_startDay startDay,member_endDay endDay
      from  (select ceil(rownum/10) page, member_no, shooes_no, member_name, member_phoneend, member_startDay,member_endDay
               from(select member_no, shooes_no, member_name, member_phoneend, member_startDay,member_endDay
                       from health_member order by member_startDay desc
                      )
               )
      where page = 1
order by member_startDay desc

select sysdate from dual; 
select * from HEALTH_MEMBER
select * from HEALTH_SHOES
select * from CODETABLE
select * from ATTENDANCE
select codevalue from CODETABLE where codeoption=1;
select codevalue from CODETABLE where codeoption='phoneNum'
select member_no, member_name, attendance_date from ATTENDANCE
where member_no='2131814'

select  count(*) from ATTENDANCE
where member_no='2131814'
select CONSTRAINT_NAME, TABLE_NAME, R_CONSTRAINT_NAME
from user_constraints
where CONSTRAINT_NAME = 'SYS_C0036932'

select * from codetable
select * from HEALTH_MEMBER order by member_startDay desc
select shooes_no,member_no,shooes_startDay,shooes_endDay from health_shoes
where member_no=2131814
select * from HEALTH_MEMBER where member_no=9733896
	select  member_no memberNo,  member_name memberName,  member_phoneend phoneEnd, 
	member_startDay startDay, member_endDay endDay, shooes_no shoesNo,member_phonemiddle phoneMiddle,
	member_codePhoneFront phoneFront, member_birthDay birthday, member_address address, member_email email,
	member_returnprice returnPrice
	from Health_Member
select shooes_no  from health_shoes where shooes_no is not null
select * from HEALTH_SHOES


	 select manager_id managerId, manager_pw managerPw, serial_num serialNum, manager_email managerEmail from Manager
   
	두번째페이지에서 한페이지당 표시될 3개의 행을 보여줘라.
	  select member_no memberNo, shooes_no shoesNo, member_name memberName, member_phoneend phoneEnd, 
	  member_startDay startDay,member_endDay endDay
      from  (select ceil(rownum/3) page, member_no, shooes_no, member_name, member_phoneend, member_startDay,member_endDay
               from(select member_no, shooes_no, member_name, member_phoneend, member_startDay,member_endDay
                       from health_member order by member_no desc
                      )
               )
      where page = 1
      select * from HEALTH_MEMBER where member_name='이상해씨';
      select * from health_shoes
      select count(member_id) from member
   	  select * from ATTENDANCE
		select member_no memberNo, member_name memberName, attendance_date
		attendanceDate from ATTENDANCE
		where member_no=4630320 order by attendance_date desc
		select * from manager

		select * from HEALTH_MEMBER where shooes_no=17
		insert into (shooes_no) values(3) 
		select * from health_member where member_no=8314666
		update health_member set shooes_no =17 where member_no=8659680
		update health_member set shooes_no =#{shoesNo} where member_no=#{memberNo}
		
		