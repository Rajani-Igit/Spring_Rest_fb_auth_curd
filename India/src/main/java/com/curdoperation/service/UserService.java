package com.curdoperation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curdoperation.bo.UserBo;
import com.curdoperation.dao.AdminDao;
import com.curdoperation.exceptions.UserExceptions;
import com.curdoperation.util.MailSenderService;

@Service
public class UserService {
	@Autowired
	private AdminDao userdao;
	@Autowired
	MailSenderService service;
	public int validateUserCridentials(String userid,String password){
		return userdao.validateUser(userid, password);
	}
	public long AddNewUser(UserBo userbo){
		try{
			return userdao.saveUser(userbo);
			}
			catch(Exception ex){
				throw new UserExceptions("Some error Encountered,Enter a corect MailId");
			}
	
		
	}
	public UserBo findUserByMailId(String emailid){
		try{
			
			/*List<String> mailId = new ArrayList<>();
			mailId.add("basant1993.dev@gmail.com");
			System.out.println(service.sendMail(mailId, "Sample Text",
					"Hi Basant How are you"));*/
		return userdao.findUser(emailid);
		}
		catch(Exception ex){
			throw new UserExceptions("Error Occquered,Enter a corect MailId");
		}
	}
	public List<UserBo> displayAllUsers(int pageno, int pagesize){
		List<UserBo> userlist=userdao.getusersbypagination(pageno,pagesize);
		if(userlist.size()>0){
			return userlist;
		}
		else
			throw new UserExceptions("This is the last page,No more Records are there");
	}
	public int removeUser(String emailid){
		return userdao.deleteUserByMail(emailid);
	}
	

}
