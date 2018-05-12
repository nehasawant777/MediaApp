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
public class NotificationController {
	@Autowired
	NotificationRepository notrepo;
	
	@GetMapping(value="/viewNotification")
	public ModelAndView renderViewPost(
			HttpServletRequest request
			) throws Exception
    {
		System.out.println("in notification controller");
		ModelAndView mv = new ModelAndView("Notification");
		
		
		String notifyTo=request.getSession().getAttribute("uid").toString();
		System.out.println("iuser id : "+notifyTo);
		
		Long notcount=notrepo.countByNotifyToAndNotificationSeen(notifyTo, "NO");
		
		System.out.println("count of notifications : "+(notcount).toString());
		
		List<Notification> notifications = notrepo.findByNotifyToAndNotificationSeen(notifyTo, "NO");
		
		for(int i=0;i<notifications.size();i++)
		{
			Notification not= new Notification();
			not=notifications.get(i);
			System.out.println(" notifications from : "+(not.getNotifyFrom()).toString());
			System.out.println(" notifications to : "+(not.getNotifyTo()).toString());
		}
		
		mv.addObject("notifications", notifications);
		mv.addObject("notcount", notcount);
		return mv;
	}

}
