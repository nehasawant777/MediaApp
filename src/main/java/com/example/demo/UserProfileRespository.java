package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRespository extends CrudRepository<UserProfile, Integer > {
		
	public boolean existsById(String myId);
	public UserProfile findById(String myId);
	
}
