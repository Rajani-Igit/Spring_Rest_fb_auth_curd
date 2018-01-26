package com.curdoperation.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curdoperation.bo.Success;
import com.curdoperation.bo.UserBo;

@Controller
public class UserController {
@RequestMapping(value="/saveUser",method=RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
@ResponseBody
public ResponseEntity<Success> SaveUser(@RequestBody UserBo userbo){
	return null;
	
}
}
