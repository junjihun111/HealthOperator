package com.health.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.health.member.service.impl.MemberServiceImpl;
import com.health.spring.vo.HealthMember;
import com.health.spring.vo.ShoesCage;

public class main {

	public static void main(String[] args) throws ParseException {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/health/config/applicationContext.xml");
		MemberServiceImpl service = (MemberServiceImpl) context.getBean("memberServiceImpl");
		// System.out.println(service.getMemberList());
		// System.out.println(service.getEndMemberList());

		// Date date = new Date();
		// Attendance att = new Attendance(2131814,"박지우",date);
		// System.out.println(service.insertAttendance(att));
		// System.out.println(service.oneMemberAttendList(2131814));
		// System.out.println(service.oneMemberAttendCount(2131814));
		// int random = (int)(Math.random()*10000000);
		// System.out.println(service.insertMember(new HealthMember(random,
		// "박지박", 9802, new Date(), new Date(), 2)));
		/*
		 * List<Integer> attendanceDates = Arrays.asList(1, 2, 3);
		 * System.out.println(service.deleteByMemberNo(attendanceDates));
		 */
		// new HealthMember(m.getMemberNo(), m.getMemberName(),
		// m.getPhoneFront(), phoneMiddle, phoneEnd, startDay, endDay, shoesNo,
		// birthday, address, email, returnPrice)
		// HealthMember member = new
		// HealthMember(m.getMemberNo(),m.getMemberName(),m.getPhoneFront(),m.getPhoneMiddle(),m.getPhoneEnd(),m.getStartDay(),m.getEndDay(),m.getBirthday(),m.getAddress(),m.getEmail(),m.getReturnPrice());
		// System.out.println(service.IdSearchMemberList(5214201));
		// System.out.println(service.selectCountMember());
		// System.out.println(service.getMemberPageList(1));

		/*
		 * autoInsert 기능
		 * 
		 */

		/*
		 * int random; String [] arr =
		 * {"이상해씨","이상해풀","이상해꽃","파이리","리자드","리자몽","꼬부기","어니부기","거북왕","케터피",
		 * "단데기","버터풀","뿔충이","딱충이","독침붕","구구","피죤","피죤투","꼬렛","레트라","깨비참",
		 * "깨비드릴조","아보","아보크","피카츄","라이츄","모래두지","고지","니드런","니드리나","니드퀸","니드리노",
		 * "니드킹","삐삐","픽시",
		 * "식스테일","나인테일","푸린","푸크린","주벳","골뱃","뚜벅쵸","냄새꼬","라플레시아","파라스","파라섹트",
		 * "콘팡","도나리","디그다","닥트리오","나옹","페르시온","고라파덕","골덕","밍키","성원숭","가디","윈디",
		 * "발챙이","수륙쳉이","강챙이","캐이시","윤겔라","후딘","알통몬","근육몬","괴력몬","모다피","우츠동",
		 * "우츠보트","왕눈해","독파리","꼬마돌","데구리",
		 * "딱구리","포니타","날썡마","야돈","야도란","코일","레어코일","파오리","두두","두트리오","쥬쥬","쥬레곤"
		 * ,"질퍽이","질뻐기","셀러","파르셀","고오스","고오스트","팬텀","롱스톤","슬리프","슬리퍼","크랩",
		 * "킹크랩","찌리리공","붐볼","아라리","나시","탕구리","텅구리","시라소몬","홍수몬","내루미","또가스",
		 * "또도가스","뿔카노","코뿌리","럭키","덩쿠리","캥카",
		 * "쏘드라","시드라","콘치","왕콘치","별가사리","아쿠스타","마임맨","스라크","루주라","에레브","마그마",
		 * "쁘사이저","켄타로스","잉어킹","갸라도스","라프라스","메타몽","이브이","샤미드","쥬피썬더","부스터",
		 * "폴리곤","암나이트","암스타","투구","투구푸스","프테라","잠만보","프리져","썬더","파이어","미뇽","신뇽"
		 * ,"망나뇽","뮤츠","뮤"}; int phoneMiddle1; int phoneEnd1;
		 * 
		 * for(int idx=0;idx<arr.length; idx++){ random =
		 * (int)(Math.random()*10000000); phoneMiddle1=
		 * (int)(Math.random()*10000); phoneEnd1=(int)(Math.random()*10000);
		 * String phoneMiddle = Integer.toString(phoneMiddle1); String phoneEnd
		 * = Integer.toString(phoneEnd1); service.insertMember(new
		 * HealthMember(random, arr[idx], "010", phoneMiddle, phoneEnd, new
		 * Date(), new Date(), 0));
		 * 
		 * }
		 */

		// System.out.println(service.managerSearchIdPw());
		/*
		 * System.out.println("dfsfd"); ShoesCage cage = new ShoesCage(9955590,
		 * 2,new Date(),new Date());
		 * System.out.println(service.shooseCageInsert(cage));
		 */

		/*
		 * HealthMember member = new HealthMember();
		 * System.out.println(service.updateShooesNoForList(29, 8314666));
		 */

		// System.out.println(service.usingShooseNo());
		// System.out.println(service.endedShoeNo());

		// 더미데이터

		/*
		 * Random r = new Random();
		 * 
		 * 
		 * Date date = new Date(); System.out.println(date);
		 * System.out.println(System.currentTimeMillis());
		 */

		/*
		 * public static void testRandom(){ java.util.Random r = new Random();
		 * // 값을 실행시 마다 임의로 생성해주는 클래스 System.out.println(r.nextDouble());
		 * System.out.println(r.nextBoolean()); System.out.println(r.nextInt());
		 * //int 가 표현할 수 있는 범위내의 숫자를 임의로 리턴.
		 * System.out.println(r.nextInt(45)+1); //범위 지정 : 0<= 값 < 45 값 }
		 */
		Date d1 = new Date(2018 - 1900, 12 - 1, 25, 10, 20);

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
