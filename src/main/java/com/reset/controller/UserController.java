package com.reset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reset.entity.User;
import com.reset.request.ResetPassword;
import com.reset.response.UserDataResponse;
import com.reset.response.UserResponse;
import com.reset.service.UserService;
import com.reset.service.UserServiceImpl;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private UserServiceImpl impl;

	@PostMapping("/addUser")
	public ResponseEntity<UserResponse> addUserData(@RequestBody User user) {
		return ResponseEntity.ok(UserResponse.builder().message(service.addUser(user)).build());
	}

	@DeleteMapping("/deleteUser/{num}")
	public ResponseEntity<UserResponse> deleteUserData(@PathVariable Integer num) {
		String deleteUser = service.deleteUser(num);
		return ResponseEntity.ok(UserResponse.builder().message(deleteUser).build());
	}

	@GetMapping("getAllData")
	public ResponseEntity<UserDataResponse> getAllUserData() {
		List<User> allUserData = service.getAllUserData();
		return ResponseEntity.ok(UserDataResponse.builder().data(allUserData).build());
	}

	@GetMapping("getRangeData/{min}/{max}")
	public ResponseEntity<UserDataResponse> basedOnIdRangeData(@PathVariable int min, @PathVariable int max) {
		return ResponseEntity.ok(UserDataResponse.builder().data(service.fetchUserByIdRange(min, max)).build());
	}

	@GetMapping("paramRangeData/{min}/{max}")
	public ResponseEntity<UserDataResponse> basedOnParamIdRangeData(@PathVariable int min, @PathVariable int max) {
		return ResponseEntity.ok(UserDataResponse.builder().data(service.paramUserByIdRange(min, max)).build());
	}

	@GetMapping("getDept/{department}")
	public ResponseEntity<UserDataResponse> basedOnDepartment(@PathVariable String department) {
		return ResponseEntity.ok(UserDataResponse.builder().data(impl.searchByDepartment(department)).build());
	}

	// suppose the name is not present into DB , it won't give any exceptions /
	// errors

	@GetMapping("basedOnNames/{name1}/{name2}/{name3}/{name4}")
	public ResponseEntity<UserDataResponse> basedOnNames(@PathVariable String name1, @PathVariable String name2,
			@PathVariable String name3, @PathVariable String name4) {
		return ResponseEntity
				.ok(UserDataResponse.builder().data(impl.basedOnNames(name1, name2, name3, name4)).build());
	}

	@GetMapping("basedOnAgeandPassword/{age}/{pass}")
	public ResponseEntity<UserDataResponse> basedOnAgePassword(@PathVariable Integer age, @PathVariable String pass) {
		return ResponseEntity.ok(UserDataResponse.builder().data(impl.basedOnAgeandPassword(age, pass)).build());
	}

	@PostMapping("/restPassword")
	public ResponseEntity<UserResponse> getResetPassword(@RequestBody ResetPassword password)
		 {
		return ResponseEntity.ok(UserResponse.builder().message(service.resetPassword(password)).build());
	}
}