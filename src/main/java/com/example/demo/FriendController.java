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
public class FriendController {
	

	@Autowired
	private UserProfileRespository profileRepo;
	@Autowired
	private PostRepository postrepo;

	
	@GetMapping(value="/FriendProfile")
	public ModelAndView renderFriendsProfile(
			HttpServletRequest request, 
			@RequestParam("friendId") String friendId) throws Exception {
		
		ModelAndView mv= new ModelAndView();

		
		UserProfile up=profileRepo.findById(friendId);
		mv.addObject("profile_name", up.getProfile_name());
		mv.addObject("profile_desc", up.getProfile_desc());
		mv.addObject("imgSrc", up.getProfile_picture());
		
		Post pst= new Post();
		
		List<Post> posts = postrepo.findByUserId(friendId);
		//mv.addObject("loggedin", loggedin);
		mv.addObject("ownProfile", "No");
		mv.addObject("postList", posts);
		
		mv.addObject("profileID", friendId);	
		mv.setViewName("ProfilePage");
		return mv;
	}
	
//	@GetMapping(value="/FriendsList")
//	public ModelAndView renderFriendList(HttpServletRequest request) {
//		ModelAndView mv= new ModelAndView();
//		Friend f= new Friend();
//		String userId=(request.getSession().getAttribute("uid")).toString();
//		
//		List<Friend> friends = frndrepo.findByUserId(userId);
//		
//		mv.addObject("friends", friends);
//		mv.setViewName("FriendsList");
//		return mv;
//	}

}
