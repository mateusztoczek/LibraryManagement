package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.User;
import com.codetreatise.generic.GenericService;

public interface UserService extends GenericService<User> {


	boolean authenticate(String email, String password);
	
	String getRoleFromData(String email);
	
	User findByEmail(String email);
	List<User> findByLibrary_idLibrary(long idLibrary);
	List<User> findByFirstNameAndLastName(String fname,String lname);
	List<User> findByRole(String role);
	boolean existsById(long id);
	
}
