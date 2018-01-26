package com.curdoperation.exceptions;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curdoperation.bo.UserBo;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionResolver {
	@ExceptionHandler(UserExceptions.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> exceptionreolver(UserExceptions ex){
		System.out.println("Global Exception resolver "+ex.getMessage());
		return new ResponseEntity(new ErrorMessage(ex.getMessage(),"333"),HttpStatus.BAD_REQUEST);
		
		
	} 
    

}
