package com.curdoperation.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curdoperation.bo.Success;
import com.curdoperation.bo.UserBo;
import com.curdoperation.dao.AdminDao;
import com.curdoperation.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class AdminController {

	@Autowired
	AdminDao userdao;
	@Autowired
	private UserService userService;
	@RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(@RequestParam(value="userid")String userid,Model model){
		//userService.validateUserCridentials(userid, password);
		model.addAttribute("userid", userid);
    return "login";
    }
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(Model model){
		//userService.validateUserCridentials(userid, password);
		//model.addAttribute("userid", userid);
    return "logout";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Success> addUser(@RequestBody UserBo userbo) {
		/*
		 * UserBo bo=new UserBo(); bo.setfName("Rajanikanta");
		 * bo.setlName("Pradhan"); bo.setGender("Male"); bo.setState("Odisha");
		 * bo.setZip("761125"); bo.setMobile("8895247580");
		 * bo.setPassword("wellcome1"); bo.setEmail("rajani769@gmail.com");
		 */
		System.out.println(userbo);
		System.out.println("from controller");
		System.out.println(userService.AddNewUser(userbo));
		return new ResponseEntity<Success>(new Success("User Added Successfully",123), HttpStatus.OK);
	}

	@RequestMapping(value = "/finduser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserBo> findUserByMail(@RequestParam(value = "email", required = false) String email)
			throws JsonProcessingException {
		/*
		 * ObjectMapper mapper = new ObjectMapper();
		 * 
		 * //Convert object to JSON string String jsonInString =
		 * mapper.writeValueAsString(userdao.findUser("rajani769@gmail.com"));
		 * System.out.println(jsonInString);
		 * 
		 * //Convert object to JSON string and pretty print //jsonInString =
		 * mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		 */
		
		return new ResponseEntity<UserBo>(userService.findUserByMailId(email), HttpStatus.OK);

	}

	@RequestMapping(value="/getallusers",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserBo>> getAllUsers(@RequestParam(value = "pageno") int pageNo,
			@RequestParam(value = "pageSize") int pageSize) throws JsonProcessingException {
		/*
		 * System.out.println(userdao.getUsers()); ObjectMapper mapper=new
		 * ObjectMapper(); String jsonInString =
		 * mapper.writeValueAsString(userdao.getUsers());
		 */
		System.out.println("controller Page no"+pageNo);
		System.out.println("controller Page Size"+pageSize);
		return new ResponseEntity<List<UserBo>>(userService.displayAllUsers(pageNo, pageSize), HttpStatus.OK);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)

	public String saveUser(@RequestParam(value = "fname", required = false) String fname) {
		System.out.println("adduser controller");
		System.out.println(fname);
		// System.out.println(state);
		return "addUser";
	}

	@RequestMapping(value = "selectlist", method = RequestMethod.GET)
	@ResponseBody
	public List<String> fetchStates() {
		System.out.println("Select List");
		List<String> states = new ArrayList<>();
		states.add("Odisha");
		states.add("Telengana");
		states.add("Andrapradesh");
		states.add("Maharastra");
		states.add("Bihar");
		states.add("UttarPradesh");
		return states;
	}

	@RequestMapping(value = "deleterecordbyemail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String deleteUserByEmail(@RequestParam(value = "email", required = false) String email) {
		System.out.println("inside delete controller" + email);
		if (userService.removeUser(email) > 0) {
			return "User deleted Succesfully";
		}
		return "Something went wrong try again";
	}

	//Dummy Method for Testing purpose
	@RequestMapping(value = "dummy", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String dummyFunction(HttpServletRequest request) throws IOException {
		/*System.out.println();
		MultipartHttpServletRequest mRequest;
		String filename = "upload.xlsx";
		try {
		   mRequest = (MultipartHttpServletRequest) request;
		   mRequest.getParameterMap();
		   Iterator itr = mRequest.getFileNames();
		   while (itr.hasNext()) {
		        MultipartFile mFile = mRequest.getFile((String) itr.next());
		        String fileName = mFile.getOriginalFilename();
		        System.out.println(fileName);
		         * Files.deleteIfExists(path);
		       // File f=new File("D:/Data/DemoUpload/");
		        java.nio.file.Path path = Paths.get("D:/Data/DemoUpload/"+fileName);
		        InputStream in = mFile.getInputStream();
		        Files.copy(in, path);
		 }
		   } catch (Exception e) {
		        e.printStackTrace();
		   }
*/		
		byte[] array = Files.readAllBytes(new File("D:\\Photos\\clg1.jpg").toPath());
		String base64String = Base64.getEncoder().encodeToString(array);
		System.out.println(base64String);

		return base64String;
	}

}
