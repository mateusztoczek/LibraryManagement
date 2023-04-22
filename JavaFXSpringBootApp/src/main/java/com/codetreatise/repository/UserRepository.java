package com.codetreatise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.bean.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	List<User> findByRole(String role);
	List<User> findByFirstNameAndLastName(String fname,String lname);
	List<User> findByLibrary_idLibrary(long idLibrary);
	boolean existsById(long id);
}
