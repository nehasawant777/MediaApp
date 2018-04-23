package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserProfile {
	
	@Id
	private String id;

	private String profile_name;
    
	private String profile_picture;
    
    private String profile_desc;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getProfile_picture() {
		return profile_picture;
	}

	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}

	public String getProfile_desc() {
		return profile_desc;
	}

	public void setProfile_desc(String profile_desc) {
		this.profile_desc = profile_desc;
	}


    
    
	

}
