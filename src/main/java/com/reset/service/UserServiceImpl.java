package com.reset.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reset.entity.User;
import com.reset.exception.IdNotPresentInDataBase;
import com.reset.exception.NameNotPresentInDB;
import com.reset.exception.PasswordNotMatchException;
import com.reset.exception.ResetPasswordException;
import com.reset.repository.UserRepository;
import com.reset.request.ResetPassword;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public String addUser(User user) {
		repository.save(User.builder().stuName(user.getStuName()).password(user.getPassword()).email(user.getEmail())
				.age(user.getAge()).department(user.getDepartment()).build());
		return "Data Added into DB";
	}

	@Override
	public String deleteUser(Integer num) {

		try {
			Optional<User> findById = repository.findByIdAndIsDelete(num, false);
			if (!findById.isPresent()) {
				throw new IdNotPresentInDataBase("User Not Found");
			} else {
				findById.get().setDelete(true);
				repository.save(findById.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deleted data";
	}

	// Soft Delete
	@Override
	public List<User> getAllUserData() {
		return repository.findAll().stream().filter(x -> x.isDelete() != true).collect(Collectors.toList());
	}

	@Override
	public List<User> fetchUserByIdRange(int min, int max) {
		return repository.fetchUserByIdRange(min, max);
	}

	@Override
	public List<User> paramUserByIdRange(int min, int max) {
		return repository.searchUserByIdRange(min, max);
	}

	public List<User> searchByDepartment(String dept) {
		return repository.searchByDepartment(dept);
	}

	public List<User> basedOnNames(String name1, String name2, String name3, String name4) {
		return repository.searchByNames(name1, name2, name3, name4);
	}

	public List<User> basedOnAgeandPassword(Integer age, String pass) {
		return repository.basedOnAgeAndPasswordData(age, pass);
	}

	@Override
	public String resetPassword(ResetPassword password) {
		try {
			User user = repository.findByStuName(password.getName())
					.orElseThrow(() -> new NameNotPresentInDB(password.getName() + " name is not present in DB"));
			System.err.println("user : " + user);
			if (user.getPassword().equals(password.getOldPassword())) {
				user.setPassword(password.getNewPassword());
				User save = repository.save(user);
				System.err.println("save : " + save);
			} else {
				throw new PasswordNotMatchException("Old password doesn't match with DB password");
			}
			return "Password reset successfully!";
		} catch (NameNotPresentInDB | PasswordNotMatchException e) {
			throw e;
		} catch (Exception e) {
			throw new ResetPasswordException("Failed to reset password");
		}
	}
}

// Direct Delete

//	@Override
//	public String deleteUser(Integer num) {
//		User user = repository.findById(num)
//				.orElseThrow(() -> new IdNotPresentInDataBase(num + " id Not present int DB"));
//		repository.delete(user);
//		return num + " Id data deleted from DB Succesfully..!";
//	}
