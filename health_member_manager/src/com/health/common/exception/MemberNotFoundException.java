package com.health.common.exception;
/**
 * 수정/삭제시 대상 회원이 없을 경우 발생시킬 Exception
 * @author kosta
 *
 */
public class MemberNotFoundException extends Exception{

	public MemberNotFoundException(){}
	public MemberNotFoundException(String message){
		super(message);
	}
}
