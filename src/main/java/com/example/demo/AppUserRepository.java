package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

	public boolean existsById(String myId);
	Iterable<AppUser> findAll();
	
}
