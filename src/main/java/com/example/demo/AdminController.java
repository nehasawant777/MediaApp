package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@Autowired
	private UserProfileRespository profileRepo;
	@Autowired
	private PostRepository postrepo;
	@Autowired
	private AppUserRepository appuserrepo;
	@Autowired
	private CommentRepository cmrepo;

	
	@GetMapping(value="/AdminDelete")
	public ModelAndView renderFriendsProfile(
			@RequestParam("postID") String postID, 
			HttpServletRequest request) throws Exception {
		
		ModelAndView mv= new ModelAndView();
		int post_ID= Integer.parseInt(postID);
		Post pst=new Post();
		String userID=pst.getUserId();
		System.out.println("before deleteion"+userID);
		
		postrepo.deleteByPostId(post_ID);
		cmrepo.deleteByPostId(post_ID);
		
		System.out.println("after deleteion");
//		UserProfile up=profileRepo.findById(userID);
//		mv.addObject("profile_name", up.getProfile_name());
//		mv.addObject("profile_desc", up.getProfile_desc());
//		mv.addObject("imgSrc", up.getProfile_picture());
//		
//		List<Post> posts = postrepo.findByUserId(userID);
//		//mv.addObject("loggedin", loggedin);
//		mv.addObject("ownProfile", "No");
//		mv.addObject("postList", posts);
//		
//		mv.addObject("profileID", userID);	
		mv.setViewName("/FriendProfile?friendId="+userID);
		return mv;
	
	}
	
	@GetMapping(value="/AllUsers")
	public ModelAndView renderFriendList(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView();
		Iterable<AppUser> users= appuserrepo.findAll();
		
		mv.addObject("users", users);
		mv.setViewName("AdminPage");
		return mv;
	}

}
