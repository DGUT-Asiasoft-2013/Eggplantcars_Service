package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Address;
import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.Comment;

import com.cloudage.membercenter.entity.Deal;
import com.cloudage.membercenter.entity.History;
import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.PrivateLatter;
import com.cloudage.membercenter.entity.ShoppingCar;
import com.cloudage.membercenter.entity.NewsComment;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IAddressService;
import com.cloudage.membercenter.service.IArticleService;
import com.cloudage.membercenter.service.ICommentService;
import com.cloudage.membercenter.service.IDealService;
import com.cloudage.membercenter.service.IHistoryService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.INewsCommentService;
import com.cloudage.membercenter.service.INewsService;
import com.cloudage.membercenter.service.IPrivateLatterService;
import com.cloudage.membercenter.service.IShoppingCarService;
import com.cloudage.membercenter.service.IUserService;
import org.apache.commons.io.FileUtils;
import org.junit.runner.Request;

@RestController
@RequestMapping("/api")
public class APIController {
	@Autowired
	IUserService userService;
	@Autowired
	IArticleService articleService;
	@Autowired
	ICommentService commentService;
	@Autowired
	ILikesService likesService;
	@Autowired
	IDealService dealService;
	@Autowired
	INewsService newsService;
	@Autowired
	IPrivateLatterService latterService;
	@Autowired
	IHistoryService historyService;
	@Autowired
	INewsCommentService newsCommentService;
	@Autowired
	IShoppingCarService shoppingCarService;
	@Autowired
	IAddressService addressService;



	@RequestMapping(value = "/hello", method=RequestMethod.GET)
	public @ResponseBody String hello(){	
		return "HELLO WORLD";
	}

	//	@RequestMapping(value="/register",method=RequestMethod.POST)
	//	public User register(
	//			@RequestParam String account
	//			@RequestParam String passwordHash
	//			@RequestParam String email
	//			@RequestParam String name){
	//		User user=new User();
	//		user.setAccount(account);
	//		user.setPasswordHash(passwordHash);
	//		user.setEmail(email);
	//		user.setName(name);
	//		return userService.save(user);
	//	}

	//注册（数据提交）
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public User register(
			@RequestParam String account,
			@RequestParam String passwordHash,
			@RequestParam String email,
			@RequestParam String name,
			MultipartFile avatar,
			HttpServletRequest request){

		User user = new User();
		user.setAccount(account);
		user.setPasswordHash(passwordHash);
		user.setEmail(email);
		user.setName(name);

		if (avatar !=null) {
			try {
				String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath,account+".png"));
				user.setAvatar("upload/"+account+".png");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return userService.save(user);
	}


	//登陆
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public User login(
			@RequestParam String account,
			@RequestParam String passwordHash,
			HttpServletRequest request){
		User user =userService.findByAccount(account);
		if (user!=null && user.getPasswordHash().equals(passwordHash)) {
			HttpSession session=request.getSession(true);
			session.setAttribute("uid", user.getId());
			//request.getSession().setAttribute("user", user);
			return user;
		} else {
			return null;
		}
	}


	//显示用户信息 提供session
	@RequestMapping(value="/me",method=RequestMethod.GET)
	public User getCurrentUser(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		Integer uid=(Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}


	//找回密码
	@RequestMapping(value="/passwordrecover",method=RequestMethod.POST)
	public boolean repassword(
			@RequestParam String email,
			@RequestParam String passwordHash,
			HttpServletRequest request){
		User user =userService.findByEmail(email);
		if (user==null) {
			return false;
		}else{
			user.setPasswordHash(passwordHash);
			userService.save(user);
			return true;
		}
	}

	@RequestMapping(value="/article",method=RequestMethod.POST)
	public Article addArticle(
			@RequestParam String title,
			@RequestParam String text,
			HttpServletRequest request){
		User currentUser=getCurrentUser(request);
		Article article = new Article();
		article.setAuthor(currentUser);
		article.setTitle(title);
		article.setText(text);
		return articleService.save(article);
	}


	//显示文章
	@RequestMapping(value="/feeds/{page}",method=RequestMethod.GET)
	public Page<Article> getFeeds(@PathVariable int page){
		return articleService.getFeeds(page);
	}	
	@RequestMapping(value="/feeds",method=RequestMethod.GET)
	public Page<Article> getFeeds(){
		return getFeeds(0);
	}
	
	//获取所有用户
	@RequestMapping(value="/alluser",method=RequestMethod.GET)
	public Page<User> getAllUser(){
		return userService.getAllUser(0);
	}



	//显示文章评论
	@RequestMapping("/article/{article_id}/comments/count")//文章总数
	public int getCommentsCountOfArticle(@PathVariable int article_id){
		return commentService.getCommentCountOfArticle(article_id);
	}	
	@RequestMapping("/article/{article_id}/comments/{page}")//分页
	public Page<Comment> getCommentsOfArticle(
			@PathVariable int article_id,
			@PathVariable int page){
		return commentService.findCommentsOfArticle(article_id, page); 
	}
	@RequestMapping("/article/{article_id}/comments")//基本方法
	public Page<Comment> getCommentsOfArticle(
			@PathVariable int article_id){
		return commentService.findCommentsOfArticle(article_id, 0);
	}
	@RequestMapping("/article/author_id/receivedcomment")//显示所有对某人的评论
	public Page<Comment> getCommentsOfAuthor(
			HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		int author_id = currentUser.getId();
		return commentService.findCommentsOfAuthor(author_id, 0);
	}
	@RequestMapping("/article/author_id/receivedcomment/{page}")//分页
	public Page<Comment> getCommentsOfAuthor(
			HttpServletRequest request,
			@PathVariable int page){
		User currentUser = getCurrentUser(request);
		int author_id = currentUser.getId();
		return commentService.findCommentsOfAuthor(author_id, page);
	}

	@RequestMapping("/article/author_id/mycomments") //所有登陆用户发表过的评论
	public Page<Comment> getCommentsOfMe(
			HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		int author_id = currentUser.getId();
		return commentService.findAllOfMyComment(author_id, 0);
	}
	@RequestMapping("/article/author_id/mycomments/{page}")
	public Page<Comment> getCommentsOfMe(
			HttpServletRequest request,
			@PathVariable int page){
		User currentUser= getCurrentUser(request);
		int author_id = currentUser.getId();
		return commentService.findAllOfMyComment(author_id, page);
	}




	//文章上传

	
	
	//评论上传

	@RequestMapping(value="/article/{article_id}/comments",method=RequestMethod.POST)
	public Comment postComment(
			@PathVariable int article_id,
			@RequestParam String text,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		Article article = articleService.findOne(article_id);
		Comment comment = new Comment();
		comment.setAuthor(me);
		comment.setArticle(article);
		comment.setText(text);
		return commentService.save(comment);
	}


	//news 上传
	@RequestMapping(value="/news",method = RequestMethod.POST)
	public News postNews(
			@RequestParam String title,
			@RequestParam String text,
			MultipartFile newsavatar,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		News news  = new News();
		news.setAuthor(me);
		news.setTitle(title);
		news.setText(text);
		if(newsavatar != null){
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/newsload");
				FileUtils.copyInputStreamToFile(newsavatar.getInputStream(), new File(realPath,title+".png"));
				news.setAvatar("newsload/"+title+".png");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return newsService.save(news);
	}
	//显示news
	@RequestMapping(value="/shownews",method = RequestMethod.GET)
	public Page<News> getNews(){
		return newsService.getNews(0);
	}
	@RequestMapping(value="/shownews/{page}",method= RequestMethod.GET)
	public Page<News> getNews(
			@PathVariable int page){
		return newsService.getNews(page);
	}
	
	
	

//	a
	//用户与文章共同组成主键
	@RequestMapping("/article/s/{keyword}")
	public Page<Article> searchArticlesWithKeyword(
			@PathVariable String keyword,
			@RequestParam(defaultValue="0") int page){
		return articleService.searchTextWithKeyword(keyword, page);
	}

	@RequestMapping(value="/deal", method=RequestMethod.POST)
	public Deal addDeal(
			@RequestParam String title,
			@RequestParam String carModel,
			@RequestParam String buyDate,
			@RequestParam String travelDistance,
			@RequestParam String price,
			@RequestParam String text,
			MultipartFile dealAvatar,
			HttpServletRequest request){
		User currentUser=getCurrentUser(request);
		Deal deal = new Deal();
		deal.setSeller(currentUser);
		deal.setTitle(title);
		deal.setCarModel(carModel);
		deal.setBuyDate(buyDate);
		deal.setTravelDistance(travelDistance);
		deal.setPrice(price);
		deal.setText(text);

		if (dealAvatar !=null) {
			try {
				String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/picload");
				FileUtils.copyInputStreamToFile(dealAvatar.getInputStream(), new File(realPath,carModel+".png"));
				deal.setDealAvatar("picload/"+carModel+".png");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return dealService.save(deal);
	}
	
	@RequestMapping(value="/dealitems/{page}",method=RequestMethod.GET)
	public Page<Deal> getDealItems(@PathVariable int page){
		return dealService.getDealItems(page);
	}
	
	@RequestMapping(value="/dealitems",method=RequestMethod.GET)
	public Page<Deal> getDealItems(){
		return getDealItems(0);
	}
	
	@RequestMapping("/deal/s/{keyword}")
	public Page<Deal> searchDealByKeword(
			@PathVariable String keyword,
			@RequestParam (defaultValue="0") int page
			){
		return dealService.searchTextByKeyword(keyword,page);
	}
	
	
	//发送私信
	@RequestMapping(value="/privateLatter",method=RequestMethod.POST)
	public PrivateLatter savePrivateLatter(
			@RequestParam String text,
			@RequestParam String latterType,
			@RequestParam String receiverAccount,
			HttpServletRequest request ){
		
		//User currentUser=getCurrentUser(request);
		//通过 别人的Account(账号)给他发送私信
		User me = getCurrentUser(request);
		User receiver = userService.findByAccount(receiverAccount);
		PrivateLatter latter = new PrivateLatter();
		latter.setSender(me);
		latter.setReceiver(receiver);
		latter.setSendtype(latterType);
		latter.setLatterText(text);
		return latterService.save(latter);
	}
	
	//接受私信(当前登录用户为接收者)
	@RequestMapping(value="/getprivateLatter/{receiverId}")
	public Page<PrivateLatter> getLatter(
			@PathVariable int receiverId,
			@RequestParam (defaultValue="0") int page,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		return latterService.findPrivateLetterByReveiverId(receiverId, me.getId(), page);
	}
	
	
	
	//收货地址删除
	@RequestMapping(value="/address/del",method=RequestMethod.POST)
	public Page<Address> delAddress(
			@RequestParam String addressIdString,
			@RequestParam (defaultValue = "0") int page,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		int addressId = Integer.parseInt(addressIdString);
		int meId = me.getId();
		addressService.delectAddressById(addressId);
		return addressService.findAddressOfUser(meId, page);
	}
	
	
	
	//编辑收货地址
	@RequestMapping(value="/address/update",method=RequestMethod.POST)
	public Address addressUpdate(
			@RequestParam String addressIdString,
			@RequestParam String text,
			@RequestParam String name,
			@RequestParam String phoneNumber,
			HttpServletRequest request){
		int addressId = Integer.parseInt(addressIdString);
		Address address = addressService.findAddressById(addressId);
		address.setText(text);
		address.setName(name);
		address.setPhoneNumber(phoneNumber);
		return addressService.save(address);
	}
	
	
	
	
	
	
	


	@RequestMapping(value="/history",method=RequestMethod.POST)
	public History addHistory(
			@RequestParam String text,
			HttpServletRequest request){
		User currentUser=getCurrentUser(request);
		History history=new History();
		history.setAuthor(currentUser);
		history.setText(text);
		return historyService.save(history);
	}
	
	@RequestMapping(value="/historyitems/{page}",method=RequestMethod.GET)
	public Page<History> getHistoryItems(@PathVariable int page){
		return historyService.getHistoryItems(page);
	}
	
	@RequestMapping(value="/historyitems",method=RequestMethod.GET)
	public Page<History> getHistoryItems(){
		return getHistoryItems(0);
	}
	@RequestMapping("/delecthistory")
	public void  delectHistory(
			HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		int author_id = currentUser.getId();
		historyService.delectAllOfHistory(author_id);
		}
		
	@RequestMapping(value="/News/{news_id}/comments",method=RequestMethod.POST)
	public NewsComment postNewsComment(
			@PathVariable int news_id,
			@RequestParam String text,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		News news = newsService.findOne(news_id);
		NewsComment newsComment = new NewsComment();
		newsComment.setAuthor(me);
		newsComment.setNews(news);
		newsComment.setText(text);
		return newsCommentService.save(newsComment);
	}
	
	@RequestMapping("/News/{news_id}/comments/{page}")//分页
	public Page<NewsComment> getNewsCommentsOfNews(
			@PathVariable int news_id,
			@PathVariable int page){
		return newsCommentService.findNewsCommentsOfNews(news_id, page); 
	}
	@RequestMapping("/News/{news_id}/comments")//基本方法
	public Page<NewsComment> getNewsCommentsOfNews(
			@PathVariable int news_id){
		return newsCommentService.findNewsCommentsOfNews(news_id, 0);
	}
	
	@RequestMapping("/news/author_id/receivedcomment")//显示所有对某人的评论
	public Page<NewsComment> getNewsCommentsOfAuthor(
			HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		int author_id = currentUser.getId();
		return newsCommentService.findNewsCommentsOfAuthor(author_id, 0);
	}
	@RequestMapping("/news/author_id/receivedcomment/{page}")//分页
	public Page<NewsComment> getNewsCommentsOfAuthor(
			HttpServletRequest request,
			@PathVariable int page){
		User currentUser = getCurrentUser(request);
		int author_id = currentUser.getId();
		return newsCommentService.findNewsCommentsOfAuthor(author_id, page);
	}

	@RequestMapping("/news/author_id/mycomments") //所有登陆用户发表过的评论
	public Page<NewsComment> getNewsCommentsOfMe(
			HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		int author_id = currentUser.getId();
		return newsCommentService.findAllOfMyNewsComment(author_id, 0);
	}
	@RequestMapping("/news/author_id/mycomments/{page}")
	public Page<NewsComment> getNewsCommentsOfMe(
			HttpServletRequest request,
			@PathVariable int page){
		User currentUser= getCurrentUser(request);
		int author_id = currentUser.getId();
		return newsCommentService.findAllOfMyNewsComment(author_id, page);
	}
	

	@RequestMapping("deal/{deal_id}/shoppingcar")
	public void addShoppingCar(
			@PathVariable int deal_id,
			HttpServletRequest request){
		User currentUser=getCurrentUser(request);
		Deal deal=dealService.findById(deal_id);
		shoppingCarService.addShoppingCar(deal,currentUser);
	}
	
	@RequestMapping("/myshoppingcar")
	public Page<ShoppingCar> getMyShoppingCar(
			HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		int buyer_id = currentUser.getId();
		return shoppingCarService.findMyShoppingCar(buyer_id, 0);
	}
	@RequestMapping("/myshoppingcar/{page}")//分页
	public Page<ShoppingCar> getMyShoppingCar(
			HttpServletRequest request,
			@PathVariable int page){
		User currentUser = getCurrentUser(request);
		int buyer_id = currentUser.getId();
		return shoppingCarService.findMyShoppingCar(buyer_id, page);
	}
	
	@RequestMapping("/myshoppingcar/{deal_id}/delect")
	public void  delectMyShoppingCar(
			@PathVariable int deal_id,
			HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		int buyer_id = currentUser.getId();
		shoppingCarService.delectMyShoppingCar(deal_id,buyer_id);
		}

	//上传收货地址
	@RequestMapping(value="/setaddress",method=RequestMethod.POST)
	public Address setAddress(
			@RequestParam String text,
			@RequestParam String name,
			@RequestParam String phoneNumber,
			HttpServletRequest request
			){
		User user = getCurrentUser(request);
		Address address = new Address();
		address.setUser(user);
		address.setText(text);
		address.setName(name);
		address.setPhoneNumber(phoneNumber);
		return addressService.save(address);
	}
	//获取收获地址
	@RequestMapping("/getaddress")
	public Page<Address> getAddress(
			@RequestParam(defaultValue="0") int page,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		int meId = me.getId();
		return addressService.findAddressOfUser(meId, page);
	}
	
	
	//或许未读消息条数  get
	@RequestMapping("/unread/{senderId}")
	public int countUnreadMessage(
			@PathVariable int senderId,
			HttpServletRequest request){
		User me= getCurrentUser(request);
		int meId = me.getId();
		return latterService.countUnreadMessages(meId, senderId);
	}
	
	//修改未读消息为已读    post
	@RequestMapping(value="/unread/update",method=RequestMethod.POST)
	public void updateUnread(
			@RequestParam String senderIdString,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		int meId = me.getId();	
		int senderId = Integer.parseInt(senderIdString);
		latterService.updateUnread(meId, senderId);
	} 

}
