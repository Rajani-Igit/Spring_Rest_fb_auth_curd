package com.curdoperation.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curdoperation.bo.UserBo;
import com.curdoperation.util.MailSenderService;

@Component
@Aspect
public class EmailAspect {
	@Autowired
	private MailSenderService service;
	@After("execution(* com.curdoperation.service.UserService.AddNewUser(..))")
	//@After("within(com.curdoperation.service.UserService.findUserByMailId(..))")
	public void sendEmail(JoinPoint jp){
		UserBo userbo =(UserBo) jp.getArgs()[0];
		System.out.println("inside aspect aop"+userbo.getEmail());
		List<String> mailId = new ArrayList<>();
		mailId.add(userbo.getEmail().trim());
		System.out.println(service.sendMail(mailId, " Curd Operation Registration Status",
				"Hi"+userbo.getfName()+"  "+userbo.getlName()+" you have Sucessfully Registered"));
	}
  

}
