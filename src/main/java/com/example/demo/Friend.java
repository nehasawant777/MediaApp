package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friend {
	
	@Id
	private String friend_no;
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private String user_id;
	
	private String friend_id;
	
	private String friend_name;

	public String getFriend_no() {
		return friend_no;
	}

	public void setFriend_no(String friend_no) {
		this.friend_no = friend_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	public String getFriend_name() {
		return friend_name;
	}

	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	
	

}
