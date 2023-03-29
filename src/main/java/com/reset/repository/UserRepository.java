package com.reset.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reset.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByIdAndIsDelete(Integer num, boolean b);
	
	// Named Custom Query
	@Query("From User WHERE id>=:min and id<=:max")
	List<User> fetchUserByIdRange(int min, int max);

	// Positional param
	@Query("From User WHERE id>=?1 and id<=?2")
	List<User> searchUserByIdRange(int min, int max);

	// serach by department
	@Query("From User WHERE department=:dept")
	List<User> searchByDepartment(String dept);
	
	@Query("From User WHERE stuName in(:name1, :name2, :name3, :name4) order by stuName desc")
	List<User> searchByNames(String name1, String name2, String name3, String name4);
	
	@Query("From User WHERE age>=:age and password=:pass")
	List<User> basedOnAgeAndPasswordData(Integer age, String pass);

	Optional<User> findByStuName(String name);
}
