package com.health.common.exception;
/**
 * 등록시 중복된 ID일 경우 발생시킬 Exception
 * @author kosta
 *
 */
public class DuplicatedIdException extends Exception {
	
	public DuplicatedIdException(){}
	public DuplicatedIdException(String message){
		super(message);
	}
}
