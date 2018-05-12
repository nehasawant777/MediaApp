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
	@Autowired
	private PostRepository postrepo;
	@Autowired
	private AppUserRepository appuserrepo;
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String renderFirstPage(@RequestParam Map<String,Object> model, Model viewModel) {
		return "index";
		
	}
//	@RequestMapping(value="/ProfilePage", method=RequestMethod.GET)	
//	public String renderProfilePage(@RequestParam Map<String,Object> model, Model viewModel) {
//			return "ProfilePage";
//	}
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
			HttpServletRequest request
			) {
		HttpSession session=request.getSession();
		System.out.println(myId + myName + myFriends + myEmail);
		session.setAttribute("myFriends", myFriends);

		String[] splitted= myFriends.split("/");
		
		Boolean exist=false;
		AppUser ap = new AppUser();
		ap.setEmail(myEmail);
		ap.setId(myId);
		ap.setName(myName);
		
		session.setAttribute("uid", myId);
		System.out.println("admin: "+(ap.getEmail()).toString());
		if ("open_acnhzic_user@tfbnw.net".equals(ap.getEmail()))
		{
			ModelAndView mv= new ModelAndView();
			System.out.println("admin matched ");
			session.setAttribute("adminloggedin", "yes");
				
			Iterable<AppUser> users= appuserrepo.findAll();
			
			mv.addObject("users", users);
			mv.setViewName("AdminPage");
			return mv;
		}
		else
		{
			session.setAttribute("adminloggedin", "no");
		}
		
		
		exist=appuserRepo.existsById(myId);
		System.out.println("user exists or no: "+exist);
		if (exist==true)
		{
			Boolean profileexist=false;
			profileexist=profileRepo.existsById(myId);
			System.out.println("user profile exists : "+profileexist);
			if (profileexist==true)
			{
				
				ModelAndView mv= new ModelAndView();
				UserProfile up=profileRepo.findById((String)session.getAttribute("uid"));
				mv.addObject("profile_name", up.getProfile_name());
				
				Post pst= new Post();
				
				List<Post> posts = postrepo.findByUserId(myId);
				mv.addObject("ownProfile", "Yes");
				mv.addObject("postList", posts);
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
