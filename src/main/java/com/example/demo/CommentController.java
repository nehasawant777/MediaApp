package com.example.demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
	
	@Autowired
	CommentRepository comrepo;
	@Autowired
	PostRepository pstrepo;
	@Autowired
	UserProfileRespository userrepo;
	@Autowired 
	NotificationRepository notrepo;
	

	@PostMapping(value="/commentSave")
	public ModelAndView saveCommentRenderPost(
			HttpServletRequest request, 
			@RequestParam("postID") String postID,
			@RequestParam("commentText") String commentText) throws Exception {
		
//		System.out.println("postid while saving comment: "+postID);
//		System.out.println("commentText while saving comment: "+commentText);
		
		HttpSession session=request.getSession();
		ModelAndView mv = new ModelAndView("redirect:/viewPost");
		int post_ID= Integer.parseInt(postID);
		Comment cm = new Comment();
		UserProfile up = new UserProfile();
		Post pst=new Post();
		cm.setPostId(post_ID);
		//System.out.println("userid in http will saving comment: "+(session.getAttribute("uid")).toString());
		cm.setCommentOwner((session.getAttribute("uid")).toString());
		up=userrepo.findById((session.getAttribute("uid")).toString());
		cm.setCommentOwnerName(up.getProfile_name());
		pst=pstrepo.findByPostId(post_ID);
		cm.setPostOwner(pst.getUserId());
		cm.setCommentText(commentText);
		
		comrepo.save(cm);
		//notify post owner
		Notification not = new Notification();
		
		not.setNotifyTo(cm.getPostOwner());
		
		not.setNotifyFrom(up.getProfile_name());
		
		not.setNotificationType(" commented on your post ");
		not.setNotifyPostUrl("/viewPost?postID="+postID);
		not.setNotificationSeen("NO");
		notrepo.save(not);
		
		//notify other friends in post
		List<Comment> comments=comrepo.findByPostId(post_ID);
		
		for(int i=0;i<comments.size();i++)
		{
			Comment tempcm = new Comment();
			tempcm= comments.get(i);
			if ((tempcm.getCommentOwner()).toString()!=(tempcm.getPostOwner()).toString() &&
					(tempcm.getCommentOwner()).toString()!=(cm.getCommentOwner()).toString())
			{
				System.out.println("Notify to "+tempcm.getCommentOwner());
				not.setNotifyTo(tempcm.getCommentOwner());
				System.out.println("Notify from"+cm.getCommentOwner());
				not.setNotifyFrom(up.getProfile_name());
				not.setNotificationType(" commented on your friend's post");
				not.setNotifyPostUrl("/viewPost?postID="+postID);
				not.setNotificationSeen("NO");
				notrepo.save(not);
			}
			
		}
		
		
		mv.addObject("postID", postID);
		return mv;
	}
	
	
	
}
