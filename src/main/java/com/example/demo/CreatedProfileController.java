//package com.example.demo;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class CreatedProfileController {
//	
//	@Autowired
//	private UserProfileRespository profileRepo;
//	
//	@RequestMapping(value="/createdprofile", method=RequestMethod.GET)	
//	public String rendercreatedprofile(@RequestParam Map<String,Object> model, Model viewModel) {
//			return "createdprofile";
//	}
//	
//	@GetMapping(value="/createdprofile")
//	public ModelAndView renderProfile(HttpServletRequest req) {
//		HttpSession session=req.getSession();
//		ModelAndView mv= new ModelAndView();
//		UserProfile up=profileRepo.findById((String)session.getAttribute("uid"));
//		mv.addObject("profile_name", up.getProfile_name());
//		mv.addObject("profile_desc", up.getProfile_desc());
//		mv.addObject("profile_picture", up.getProfile_picture());
//		mv.setViewName("createdprofile");
//		return mv;
//	}
//	
//
//}
