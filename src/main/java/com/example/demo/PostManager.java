package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostManager {
	
	@Autowired
	UploadToS3 s3;
	@Autowired
	private UserProfileRespository uprepo;
	@Autowired
	private PostRepository postrepo;
	@Autowired 
	private CommentRepository cmrepo;
	@Autowired 
	NotificationRepository notrepo;
	
	@GetMapping(value="/recordAudio")
	public ModelAndView renderIndex() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("recordAudio");
		return mv;
	}
	
	@GetMapping(value="/takepicture")
	public ModelAndView renderCaptureImage() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("takepicture");
		return mv;
	}

	@GetMapping(value="/viewPost")
	public ModelAndView renderViewPost(
			HttpServletRequest request, 
			@RequestParam("postID") String postID) throws Exception
    {
		ModelAndView mv = new ModelAndView("viewPost");
		Post pst= new Post();
		int postid= Integer.parseInt(postID);
		pst=postrepo.findByPostId(postid);
		
		Comment cm = new Comment();
		List<Comment> comments = cmrepo.findByPostId(postid);
		
		mv.addObject("postID", postID);
		mv.addObject("imageURL", pst.getImgUrl());
		mv.addObject("audioURI", pst.getAudioUrl());
		mv.addObject("postCaption", pst.getCaption());
		mv.addObject("comments", comments);
		
		return mv;
	}
	
	
	@PostMapping(value="/base64Audio")
	public ModelAndView saveAudioRenderSomePage(
			HttpServletRequest request, 
			@RequestParam("recording") String recording,
			@RequestParam("post_image") String post_image,
			@RequestParam("postCaption") String postCaption) throws Exception
	{
		ModelAndView successPage = new ModelAndView("viewPost");
		System.out.println("incoming message..");
		//System.out.println("image captured: "+post_image);
		
		if (post_image.isEmpty()) throw new Exception("Image data is null..");
		if (recording.isEmpty()) throw new Exception("recording data is null..");
		
		Decoder decoder= Base64.getDecoder();
		System.out.println("recording");
		FileOutputStream fos;
		
		Post pst= new Post();
		
		String userID = (String) request.getSession().getAttribute("uid");
		String myFriends= (String) request.getSession().getAttribute("myFriends");
		
		long noOFpost = postrepo.countByUserId(userID);
				
		long post_ID= noOFpost+1;
		String postID= Long.toString(post_ID);
		System.out.println("postid: "+postID);
		
		System.out.println("userid+postid: "+userID+postID);
		
	//saving image
		byte[] decoded_image = decoder.decode(post_image.split(",")[1]);
		try {
			fos= new FileOutputStream("MyPicture.png");
			fos.write(decoded_image);
			fos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		String imageURL = s3.upload(userID+ postID+".png", new FileInputStream("MyPicture.png"));
		
	//saving audio
		byte[] decoded_audio = decoder.decode(recording.split(",")[1]);
		try {
			fos= new FileOutputStream("MyAudioTemp.webm");
			fos.write(decoded_audio);
			fos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		String audioURI = s3.upload(userID+ postID+".webm", new FileInputStream("MyAudioTemp.webm"));
		
	//saving caption
		pst.setCaption(postCaption);
		
		pst.setUserId(userID);
		pst.setImgUrl(imageURL);
		pst.setAudioUrl(audioURI);
		

		postrepo.save(pst);
		
	//saving notifications
	//	Notification not = new Notification();
		
		
		System.out.println("Displaying frnds from base64 : "+myFriends);
		String[] splitted= myFriends.split("/");
		
		for(int i=0; i< splitted.length;i=i+2)
			{	
				Notification not = new Notification();
				UserProfile up= new UserProfile();
				up=uprepo.findById(userID);
				not.setNotifyFrom(up.getProfile_name());
				System.out.println(i+"Friend ID :"+ splitted[i]);
				not.setNotifyTo(splitted[i]);
				not.setNotificationType(" added new post ");
				
				not.setNotifyPostUrl("/viewPost?postID="+postID);
				not.setNotificationSeen("NO");
				notrepo.save(not);
				System.out.println(i+"Friend ID :"+ splitted[i]+ "notification saved");
			}
		
		
		
		successPage.addObject("postCaption", postCaption);
		successPage.addObject("imageURL", imageURL);
		successPage.addObject("audioURI", audioURI);
		return successPage;
		
	}
}
