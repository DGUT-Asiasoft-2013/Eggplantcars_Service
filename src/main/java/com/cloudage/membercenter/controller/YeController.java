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

import com.cloudage.membercenter.entity.Address;
import com.cloudage.membercenter.entity.Concern;
import com.cloudage.membercenter.entity.Deal;
import com.cloudage.membercenter.entity.Money;
import com.cloudage.membercenter.entity.News;
import com.cloudage.membercenter.entity.OrderForm;
import com.cloudage.membercenter.entity.Record;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IRecordRepository;
import com.cloudage.membercenter.service.IAddressService;
import com.cloudage.membercenter.service.IConcernService;
import com.cloudage.membercenter.service.IDealService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.IMoneyService;
import com.cloudage.membercenter.service.INewsService;
import com.cloudage.membercenter.service.IOrderFormService;
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

	@Autowired
	IDealService dealService;

	@Autowired 
	IAddressService addressService;

	@Autowired
	IMoneyService moneyService;

	@Autowired
	IRecordRepository recordRepo;

	@Autowired
	IOrderFormService orderService;


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

	//显示当前用户关注ID
	@RequestMapping("/Concerns/getMyConcerns")
	public List<Concern> getConcernsByUserId(
			HttpServletRequest request){
		User me = getCurrentUser(request);
		return concernService.getConcernsByUserId(me.getId());
	}

	//显示当前用户发出新闻
	@RequestMapping("/{news_author_id}/News")
	public List<News> getConcernsByUserId(
			@PathVariable int news_author_id){
		return newsService.findAllByAuthorId(news_author_id);
	}

	@RequestMapping("/{user_id}/isMoneyed")
	public boolean checkMoneyed(
			@PathVariable int user_id){
		return moneyService.checkMoneyed(user_id);
	}

	@RequestMapping(value="/creatMoneyUser", method=RequestMethod.POST)
	public Money register(
			@RequestParam int cash,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		Money money = new Money();
		money.setUser(me);
		money.setCash(cash);

		return moneyService.save(money);
	}

	//获取当前用户钱包的数据
	@RequestMapping(value="/{user_id}/Moneys",method=RequestMethod.GET)
	public Money getMoneysByUserId(
			@PathVariable int user_id){
		return moneyService.getMoneyByUserId(user_id);
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

	//充值
	@RequestMapping(value="/CashDeposit",method=RequestMethod.POST)
	public boolean csahdeposit(
			@RequestParam int cash,
			@RequestParam String password,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		User user =userService.findByPasswordHash(me.getId(),password);
		Money money = moneyService.getMoneyByUserId(me.getId());
		if (money==null || user == null) {
			return false;
		}else{
			money.setCash(cash);
			moneyService.save(money);
			return true;
		}
	}

	//卖家收款
	@RequestMapping(value="/CashOrder",method=RequestMethod.POST)
	public boolean csahorder(
			@RequestParam int cash,
			@RequestParam int sellerid,
			@RequestParam String password,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		User user =userService.findByPasswordHash(me.getId(),password);
		User seller = userService.findById(sellerid);
		Money money = moneyService.getMoneyByUserId(seller.getId());
		if (money==null || user == null) {
			return false;
		}else{
			money.setCash(cash);
			moneyService.save(money);
			return true;
		}
	}

	//保存自己消费记录
	@RequestMapping(value="/recordsave",method=RequestMethod.POST)
	public Record recordsave(
			@RequestParam String record_type,
			@RequestParam String text,
			@RequestParam int my_cash,
			@RequestParam int record_cash,
			HttpServletRequest request){
		User me = getCurrentUser(request);
		Record record = new Record();
		record.setUser(me);
		record.setRecord_type(record_type);
		record.setMy_cash(my_cash);
		record.setText(text);
		record.setRecord_cash(record_cash);
		return recordRepo.save(record);
	}

	//保存卖家消费记录
	@RequestMapping(value="/sellerrecordsave",method=RequestMethod.POST)
	public Record sellerecordsave(
			@RequestParam String record_type,
			@RequestParam String text,
			@RequestParam int my_cash,
			@RequestParam int record_cash,
			@RequestParam int sellerid){
		User seller = userService.findById(sellerid);
		Record record = new Record();
		record.setUser(seller);
		record.setRecord_type(record_type);
		record.setMy_cash(my_cash);
		record.setText(text);
		record.setRecord_cash(record_cash);
		return recordRepo.save(record);
	}
	
	//保存卖家消费记录
		@RequestMapping(value="/buyerrecordsave",method=RequestMethod.POST)
		public Record buyerecordsave(
				@RequestParam String record_type,
				@RequestParam String text,
				@RequestParam int my_cash,
				@RequestParam int record_cash,
				@RequestParam int buyerid){
			User seller = userService.findById(buyerid);
			Record record = new Record();
			record.setUser(seller);
			record.setRecord_type(record_type);
			record.setMy_cash(my_cash);
			record.setText(text);
			record.setRecord_cash(record_cash);
			return recordRepo.save(record);
		}


	//获取当前用户钱包的数据
	@RequestMapping(value="/{user_id}/Records",method=RequestMethod.GET)
	public List<Record> findAllByUserId(
			@PathVariable int user_id){
		return recordRepo.findAllByUserId(user_id);
	}

	//保存订单
	@RequestMapping(value="/orderdsave",method=RequestMethod.POST)
	public OrderForm orderdsave(
			@RequestParam int address_id,
			@RequestParam int seller_id,
			@RequestParam int deal_id,
			@RequestParam String type,
			HttpServletRequest request){
		Address address = addressService.findAddressById(address_id);
		User me = getCurrentUser(request);
		User seller = userService.findById(seller_id);
		Deal deal = dealService.findById(deal_id);
		OrderForm orderform = new OrderForm();

		orderform.setAddress(address);
		orderform.setSeller(seller);
		orderform.setBuyer(me);
		orderform.setDeal(deal);
		orderform.setType(type);

		return orderService.save(orderform);
	}

	//买家读取订单
	@RequestMapping(value="/{buyer_id}/buyergetorder",method=RequestMethod.GET)
	public List<OrderForm> findByBuyerId(
			@PathVariable int buyer_id){
		return orderService.findByBuyerId(buyer_id);
	}

	//卖家读取订单
	@RequestMapping(value="/{seller_id}/sellergetorder",method=RequestMethod.GET)
	public List<OrderForm> findBySellerId(
			@PathVariable int seller_id){
		return orderService.findBySellerId(seller_id);
	}

	//修改订单状态
	@RequestMapping(value="/changeorder",method=RequestMethod.POST)
	public boolean changeorder(
			@RequestParam int  orderid,
			@RequestParam String type){
		OrderForm orderform = orderService.findByOrderId(orderid);
		if (orderform==null) {
			return false;
		}else{
			orderform.setType(type);
			orderService.save(orderform);
			return true;
		}
	}

	//保存订单
	@RequestMapping(value="/{orderid}/getorderbyid",method=RequestMethod.GET)
	public OrderForm getorderbyid(
			@PathVariable int orderid){

		return orderService.findByOrderId(orderid);
	}
}
