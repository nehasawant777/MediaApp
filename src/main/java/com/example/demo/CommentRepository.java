package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer > {
	
	List<Comment> findByPostId(int postId);
	@Transactional
	public void deleteByPostId(int postId);

}
