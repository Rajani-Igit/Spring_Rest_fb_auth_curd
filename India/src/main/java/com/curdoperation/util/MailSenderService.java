package com.curdoperation.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
@Component
public class MailSenderService {
	@Autowired(required = true)
	private JavaMailSenderImpl mailSender;
	public MailResponse sendMail(List<String> to, String subject, String content) {
		MailResponse response = new MailResponse();
		SimpleMailMessage message = new SimpleMailMessage();
		try {
			for (String emails : to) {
				message.setFrom("razneknt.pradhan5@gmail.com");
				message.setTo(emails);
				message.setSubject(subject);
				message.setText(content);
			//	 message.setContent("text/html");
			}
			mailSender.send(message);
			response.setStatus("Success");
			response.setErrorCode("0000");
		} catch (Exception e) {
			response.setStatus(e.getLocalizedMessage());
			response.setErrorCode("9999");
		}
		return response;
	}
}