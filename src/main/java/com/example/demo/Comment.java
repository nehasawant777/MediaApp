package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentId;
	
	private int postId;
	
	private String commentOwner;
	
	private String commentOwnerName;
	
	private String postOwner;
	
	private String commentText;
	
	
	public String getCommentOwnerName() {
		return commentOwnerName;
	}

	public void setCommentOwnerName(String commentOwnerName) {
		this.commentOwnerName = commentOwnerName;
	}

	public String getPostOwner() {
		return postOwner;
	}

	public void setPostOwner(String postOwner) {
		this.postOwner = postOwner;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getCommentOwner() {
		return commentOwner;
	}

	public void setCommentOwner(String commentOwner) {
		this.commentOwner = commentOwner;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	

	
	
	
	
	
	

}
