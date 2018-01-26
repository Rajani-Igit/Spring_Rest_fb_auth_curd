package com.curdoperation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.curdoperation.bo.UserBo;
/*
 * queryForObject will for returning single row as object or primitives
 * query method is for fetching multiple objects are list It will take 
 * sql query and named or positional params
 * 
 * execute methods are ment for List of Objects
 * 
 * 
 * */
@Component
public class AdminDao {
   @Autowired
	 private NamedParameterJdbcTemplate npJdbcTemplate;
	 private final String SQL_INSERT_SAVEUSER="INSERT INTO USER_TBL(USER_FNAME, USER_LNAME, GENDER,STATE,ZIP, MOBILE, EMAIL, PASSWORD) VALUES(:fName,:lName,:gender,:state,:zip,:mobile,:email,:password)";
     private final String SQL_FIND_USER_BY_EMAIL="SELECT USER_FNAME,USER_LNAME,GENDER,STATE,ZIP,MOBILE,EMAIL,PASSWORD FROM USER_TBL WHERE EMAIL= :email";
     private final String SQL_GET_USERS="SELECT USER_FNAME,USER_LNAME,GENDER,STATE,ZIP,MOBILE,EMAIL,PASSWORD FROM USER_TBL";
     private final String SQL_DELETE_USERBY_EMAIL="DELETE FROM USER_TBL  WHERE EMAIL=:email";
     private final String SQL_COUNT_USER_BY_ID_PASSWORD="SELECT COUNT(1) FROM USER_TBL WHERE EMAIL=:email AND PASSWORD=:password";
     private GeneratedKeyHolder kh;
     
     public long saveUser(UserBo bo){
    	 kh=new GeneratedKeyHolder();
    	 BeanPropertySqlParameterSource  params=new BeanPropertySqlParameterSource(bo);
    	 npJdbcTemplate.update(SQL_INSERT_SAVEUSER, params, kh);
    	return (long) kh.getKey();
    	 
     }
     public int validateUser(String userid,String password){
    	 MapSqlParameterSource params=new MapSqlParameterSource();
    	params.addValue("email", userid);
    	params.addValue("password", password);
    	 
    	return npJdbcTemplate.queryForObject(SQL_COUNT_USER_BY_ID_PASSWORD,params ,Integer.class);
     }
     public UserBo findUser(String email){
    	
    	return npJdbcTemplate.queryForObject(SQL_FIND_USER_BY_EMAIL, new MapSqlParameterSource().addValue("email", email),new RowMapper<UserBo>(){

			@Override
			public UserBo mapRow(ResultSet rs, int count) throws SQLException {
				System.out.println("Resultset");
				UserBo ubo=new UserBo();
				ubo.setfName(rs.getString(1));
				ubo.setlName(rs.getString(2));
				ubo.setGender(rs.getString(3));
				ubo.setState(rs.getString(4));
				ubo.setZip(rs.getString(5));
				ubo.setMobile(rs.getString(6));
				ubo.setEmail(rs.getString(7));
				ubo.setPassword(rs.getString(8));
				return ubo;
			}});
     }
     
     public List<UserBo> getUsers(){
    	 
    	 return npJdbcTemplate.query(SQL_GET_USERS,(rs,count)->{
    			UserBo ubo=new UserBo();
    			ubo.setfName(rs.getString(1));
    			ubo.setlName(rs.getString(2));
    			ubo.setGender(rs.getString(3));
    			ubo.setState(rs.getString(4));
    			ubo.setZip(rs.getString(5));
    			ubo.setMobile(rs.getString(6));
    			ubo.setEmail(rs.getString(7));
    			ubo.setPassword(rs.getString(8));
    			return ubo;
    			
    		});
     }
	/*RowMapper<UserBo> rowmap=(rs,count)->{
		UserBo ubo=new UserBo();
		ubo.setfName(rs.getString(1));
		ubo.setlName(rs.getString(2));
		ubo.setGender(rs.getString(3));
		ubo.setState(rs.getString(4));
		ubo.setZip(rs.getString(5));
		ubo.setMobile(rs.getString(6));
		ubo.setEmail(rs.getString(7));
		ubo.setPassword(rs.getString(8));
		return ubo;
		
	};*/
     public int deleteUserByMail(String email){
    	 
    	 return npJdbcTemplate.update(SQL_DELETE_USERBY_EMAIL,new MapSqlParameterSource().addValue("email", email));
     }
     
     public List<UserBo> getusersbypagination(int pageno,int pagesize){
    	 int pageNo=pageno;
    	 int pageSize=pagesize;
    	 return npJdbcTemplate.query(SQL_GET_USERS,new ResultSetExtractor<List<UserBo>>() {

			@Override
			public List<UserBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
			    List<UserBo> userbolist=new ArrayList<UserBo>();  
				int startindex=0;
			       int endindex=0;
			       int count=1;
			       startindex=(pageNo*pageSize)-pageSize+1;
			       endindex=pageNo*pageSize;
			       
			       while(rs.next()&& count<=endindex){
			    	   if(startindex<=count){
			    	   UserBo ubo=new UserBo();
			   		ubo.setfName(rs.getString(1));
			   		ubo.setlName(rs.getString(2));
			   		ubo.setGender(rs.getString(3));
			   		ubo.setState(rs.getString(4));
			   		ubo.setZip(rs.getString(5));
			   		ubo.setMobile(rs.getString(6));
			   		ubo.setEmail(rs.getString(7));
			   		ubo.setPassword(rs.getString(8));
			   		userbolist.add(ubo);
			       }
			    	   count=count+1;
				}
			       return userbolist;
			        
			}
		});
     }
     
}
