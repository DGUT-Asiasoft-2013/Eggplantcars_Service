package com.cloudage.membercenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.Concern;
import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IConcernService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.INewsService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/ye")
public class YeController {


	@Autowired
	IUserService userService;

	@Autowired
	ILikesService likesService;

	@Autowired
	INewsService newsService;

	@Autowired
	IConcernService concernService;


	@RequestMapping(value = "/hi", method=RequestMethod.GET)
	public @ResponseBody String hi(){	
		return "HELLO WORLD";
	}

	//显示用户信息 提供session
	@RequestMapping(value="/me",method=RequestMethod.GET)
	public User getCurrentUser(HttpServletRequest request)
	{
		HttpSession session=request.getSession(true);
		Integer uid=(Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}

	@RequestMapping("/{news_author_id}/isConcerned")
	public boolean checkConcerned(
			@PathVariable int news_author_id,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		return concernService.checkConcerned(me.getId(), news_author_id);
	}

	@RequestMapping(value="/{news_author_id}/Concerns",method = RequestMethod.POST)
	public boolean changeConcerns(
			@PathVariable int news_author_id,
			@RequestParam(name="Concern") boolean Concern,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		User news_author = userService.findById(news_author_id);

		if (Concern) {
			concernService.addConcern(me, news_author);
		} else {
			concernService.removeConcern(me, news_author);
		}
		return Concern;
	}

	//点赞
	@RequestMapping("/news/{news_id}/likes")
	public int countLikes(@PathVariable int news_id){
		return likesService.countLikes(news_id);
	}

	@RequestMapping("/news/{news_id}/isliked")
	public boolean checkLiked(
			@PathVariable int news_id,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		return likesService.checkLiked(me.getId(), news_id);
	}

	@RequestMapping(value="/news/{news_id}/likes",method = RequestMethod.POST)
	public int changeLikes(
			@PathVariable int news_id,
			@RequestParam boolean likes,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		News news = newsService.findOne(news_id);
		if (likes) {
			likesService.addLike(me, news);
		} else {
			likesService.removeLike(me, news);
		}
		return likesService.countLikes(news_id);
	} 

	//修改密码
	@RequestMapping(value="/passwordchange",method=RequestMethod.POST)
	public boolean repassword(
			@RequestParam String password,
			@RequestParam String passwordHash,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		User user =userService.findByPasswordHash(me.getId(),password);
		if (user==null) {
			return false;
		}else{
			user.setPasswordHash(passwordHash);
			userService.save(user);
			return true;
		}
	}

	//显示当前用户关注ID
	@RequestMapping("/Concerns/getMyConcerns")
	public List<Concern> getConcernsByUserId(
			HttpServletRequest request){
		User me = getCurrentUser(request);
		return concernService.getConcernsByUserId(me.getId());
	}
}
