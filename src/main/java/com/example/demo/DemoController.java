package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {
	@Autowired
	private AppUserRepository appuserRepo;
	@Autowired
	private UserProfileRespository profileRepo;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String renderFirstPage(@RequestParam Map<String,Object> model, Model viewModel) {
		return "index";
		
	}
	@RequestMapping(value="/ProfilePage", method=RequestMethod.GET)	
	public String renderProfilePage(@RequestParam Map<String,Object> model, Model viewModel) {
			return "ProfilePage";
	}
	@RequestMapping(value="/FriendsList", method=RequestMethod.GET)	
	public String rendercreatedprofile(@RequestParam Map<String,Object> model, Model viewModel) {
			return "FriendsList";
	}
	
	@GetMapping(value="/facebook")
	public ModelAndView renderFB() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("facebook_login");
		return mv;
	}
	
	@PostMapping(value ="/facebookRedirect")
	public ModelAndView	handleRedirect(
			@RequestParam(name="myId") String myId,
			@RequestParam(name="myName") String myName,
			@RequestParam(name="myFriends") String myFriends,
			@RequestParam(name="myEmail") String myEmail,
			HttpServletRequest req
			) {
		System.out.println(myId + myName + myFriends + myEmail);
		String[] splitted= myFriends.split("/");
		for(int i=0; i< splitted.length;i++)
		{
			System.out.println(i+":"+ splitted[i]);
		}
		HttpSession session=req.getSession();
		Boolean exist=false;
		AppUser ap = new AppUser();
		ap.setEmail(myEmail);
		ap.setId(myId);
		ap.setName(myName);
		
		session.setAttribute("uid", ap.getId());
		List<Friend> friendlist = new ArrayList();
		
		Friend f = new Friend();
		friendlist.add(f);
		
		exist=appuserRepo.existsById(myId);
		if (exist==true)
		{
			Boolean profileexist=false;
			profileexist=profileRepo.existsById(myId);
			if (profileexist==true)
			{
				
				ModelAndView mv= new ModelAndView();
				UserProfile up=profileRepo.findById((String)session.getAttribute("uid"));
				mv.addObject("profile_name", up.getProfile_name());
				
				System.out.println((String)up.getProfile_name());
				System.out.println((String)up.getProfile_desc());
				System.out.println((String)up.getProfile_picture());
				
				mv.addObject("profile_desc", up.getProfile_desc());
				mv.addObject("imgSrc", up.getProfile_picture());
				mv.setViewName("ProfilePage");
				
				return mv;
			}
			else 
			{
				return new ModelAndView("index");
			}
				
		}
		else
		{
			appuserRepo.save(ap);
			return new ModelAndView("index");
		}
		
	}
	
	
	
	
}
