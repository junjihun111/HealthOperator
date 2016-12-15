package com.health.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/*
 * 	MappingJackson2HttpMessageConverter(<mvc:annotation-driven/> 이등록해주는 bean
 * 		-> VO -> JO({	}), List/배열 - Javascript 배열형태([		])
 * 		
 * 		JsonSerializer 	-  특정 property(instance 변수) 값을 json변환시 원하는 형태의 문자열로 변환처리 하는 bean (Jackson API)에서 제공
 * 
 * 	 	instance 변수에  @jsonSerializer 어노테이션을 추가한다.
 */
public class DateJsonSerializerable extends JsonSerializer<Date>{

	/*
	 * 1. 변활할 Data
	 *  2. 변환된 Data 출력해주는 메소드 제공 객체
	 *  3. 기존 변환처리 객체가 있는 경우 제공받는 변수.
	 *  */
	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)throws IOException, JsonProcessingException 
	
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String dateStr = sdf.format(date);	//받은 data를 받아서 string으로 값의 형식을 이렇게 바꿔서 보내라
		gen.writeString(dateStr);
		
	}
	
	
}