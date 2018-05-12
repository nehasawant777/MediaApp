package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer > {

	public Post findByPostId(int Id);
	List<Post> findByUserId(String userId);	
	long countByUserId(String userId);
	@Transactional
	public void deleteByPostId(int Id);

}