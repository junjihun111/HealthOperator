package com.health.util;

import java.util.Date;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.health.member.service.impl.MemberServiceImpl;
import com.health.spring.vo.HealthMember;

public class DummmyData {

	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/health/config/applicationContext.xml");
		MemberServiceImpl service = (MemberServiceImpl) context.getBean("memberServiceImpl");
		
		int random;

		int phoneMiddle1;
		int phoneEnd1;
		String phoneMiddle;
		String phoneEnd;

		// 시작일
		Random rand = new Random();
		int min = 2016, max = 2016;
		int year;

		int min2 = 6, max2 = 12;
		int month;

		int min3 = 1, max3 = 28;
		int day;

		// 만료날짜
		int min4 = 2016, max4 = 2017;
		int year2;

		int min5 = 7, max5 = 12;
		int month2;

		int min6 = 1, max6 = 28;
		int day2;

		String[] arr = { "박지우", "이한용", "전지훈", "김희태", "김태희", "김민영", "신신애", "심의원", "구마적", "오징어", "오정훈", "신성호", "이용학",
				"박근혜", "갑돌이", "갑순이", "장보리", "장그레", "신마적", "장조림", "장도림", "크리링", "정직한", "천진반", "도라에몽", "진구", "친구", "신구",
				"천실장", "김무성", "이용학", "이환용", "이완용", "박정희", "김연아", "요한", "호날두", "메시", "오채환", "이재용", "이건희", "전두환", "노태우",
				"진다랠", "진나영", "신나영", "황영시", "김철수", "최산득", "허삼수", "허화평", "이인녕", "삼인용", "사인용", "오인용", "김태규", "최대창", "김대중",
				"김종필", "손석희", "박건영", "정진영", "장진한", "김두한", "이화룡", "제갈공", "박준규", "김준규", "이순자", "나문희", "정준하", "안철수",
				"유재석", "김기리", "하동훈", "정준하", "박명수", "이근호", "모모짱", "전효성", "김명자", "곤드리", "한드래", "전지전", "박지박", "문재연", "이재명",
				"신동엽", "장동건", "원빈", "김갑수", "우상욱", "김철수", "이덕화", "서인숙", "서인석", "김동현", "김구라", "김석조", "이수근", "박영태", "김희철",
				"최홍만", "김희선", "권상우", "김범수", "전지현", "박보람", "정보람", "주보람", "차보람", "차승원", "차인표", "김루라", "김르라", "김그리", "이혜언",
				"김철수", "신짱구", "장기하", "장비호", "나얼", "조석", "정준하", "이말년", "김영철", "김두한", "장태환", "우승옥", "박보람", "김두한",
				"박철수", "김영희", "손오공", "신짱아", "봉미선", "신영만", "이유리", "이훈이", "전재훈", "우병욱", "고지용", "시라소니", "김공유", "하지원", "선우재덕",
				"김깡패", "박건달", "박대편", "김현빈" };

		String startYear;
		String endYear;
		
		String startMonth;
		String endMonth;
		
		String startDay;
		String endDay;
		
		
		for (int idx = 0; idx < arr.length; idx++) {
			random= rand.nextInt(9999999-1000000+1)+1000000;
			year = rand.nextInt(max - min + 1) + min;
			year2 = rand.nextInt(max4 - min4 + 1) + min;
			month = rand.nextInt(max2 - min2 + 1) + min2;
			month2 = rand.nextInt(max5 - min5 + 1) + min2;
			day = rand.nextInt(max3 - min3 + 1) + min2;
			day2 = rand.nextInt(max6 - min6 + 1) + min2;
			phoneMiddle1 = rand.nextInt(9999-1000+1)+1000;
			phoneEnd1 = rand.nextInt(9999-1000+1)+1000;
			phoneMiddle = Integer.toString(phoneMiddle1);
			phoneEnd = Integer.toString(phoneEnd1);
			
			
			
			Date date1 = new Date(year - 1900, month - 1, day, 10, 20);
			Date date2 = new Date(year2-1900, month2-1, day2,11,21);
			
			
			if (year > year2) {
				idx--;
				continue;
			} else if (year == year2) {
				if (month > month2) {
					idx--;
					continue;
				} else if (month == month2) {
					if (day > day2) {
						idx--;
						continue;
					} else {
						service.insertMember(new HealthMember(random, arr[idx], "010", phoneMiddle, phoneEnd,
								date1, date2, 0));
					}
				} else {
					service.insertMember(new HealthMember(random, arr[idx], "010", phoneMiddle, phoneEnd, date1, date2, 0));
				}

			} else {
				service.insertMember(
						new HealthMember(random, arr[idx], "010", phoneMiddle, phoneEnd, date1, date2, 0));
			}

		}
	}
	
	
}
