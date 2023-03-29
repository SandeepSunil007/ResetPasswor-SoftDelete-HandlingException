package com.reset.service;

import java.util.List;

import com.reset.entity.User;
import com.reset.request.ResetPassword;

public interface UserService {

	public String addUser(User user);

	public String deleteUser(Integer num);

	public java.util.List<User> getAllUserData();

	// Custom Queries

	// 1. named parameters
	List<User> fetchUserByIdRange(int min, int max);

	// 2. positional param
	List<User> paramUserByIdRange(int min, int max);

	String resetPassword(ResetPassword password) ;

}
