package com.cos.validation_test.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "select * from user where username = :username and password = :password", nativeQuery = true)
	User mLogin(String username, String password);
	
	@Query(value = "Update user Set chk = 2 where username = :username ", nativeQuery = true)
	User mChangeAdmin(String username);
	
	@Query(value = "Update user Set chk = 1 where username = :username ", nativeQuery = true)
	User mChangeGuest(String username);
}
