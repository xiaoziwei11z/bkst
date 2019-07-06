package com.bkst.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bkst.common.utils.IDUtils;
import com.bkst.mapper.BookMapper;
import com.bkst.pojo.Book;

import redis.clients.jedis.JedisCluster;

@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private JedisCluster jedisClient;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource(name="addBookDestination")
	private Destination addBookDestination;
	@Resource(name="editBookDestination")
	private Destination editBookDestination;
	@Resource(name="deleteBookDestination")
	private Destination deleteBookDestination;
	
	public void addBook(Book book) {
		book.setBid(IDUtils.genBookId());
		bookMapper.insertBook(book);
		
		//清除redis缓存
		jedisClient.del("book");
		jedisClient.hdel("bookWithCat", book.getCategory().getCid()+"");
		
		//发送商品添加消息
		jmsTemplate.send(addBookDestination,new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(book.getBid()+"");
				return textMessage;
			}
		});
	}

	public List<Book> showBook(Integer cid) {
		//查询redis
		String json = jedisClient.get("book");
		if(json!=null && !json.trim().isEmpty()) {
			List<Book> bookList = JSONObject.parseArray(json,Book.class);
			return bookList;
		}
		
		//查询数据库
		List<Book> bookList = bookMapper.selectBooks(cid);
		
		//向redis存入缓存
		jedisClient.set("book",JSON.toJSONString(bookList));
		return bookList;
	}
	
	public List<Book> showBookByCid(Integer cid) {
		//查询redis
		String json = jedisClient.hget("bookWithCat", cid+"");
		if(json!=null && !json.trim().isEmpty()) {
			List<Book> bookList = JSON.parseArray(json,Book.class);
			return bookList;
		}
		
		//查询数据库
		List<Book> bookList = bookMapper.selectBooks(cid);
		
		//向redis存入缓存
		jedisClient.hset("bookWithCat",cid+"", JSON.toJSONString(bookList));
		return bookList;
	}

	public Book getBookByBid(Integer bid) {
		return bookMapper.selectBookByBid(bid);
	}

	public void editBook(Book book,Integer lastCid) {
		bookMapper.updateBook(book);
		
		//清除redis缓存
		jedisClient.del("book");
		jedisClient.hdel("bookWithCat", book.getCategory().getCid()+"");
		jedisClient.hdel("bookWithCat", lastCid+"");
		
		jmsTemplate.send(editBookDestination,new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(book.getBid()+"");
				return textMessage;
			}
		});
	}

	public void deleteBook(Integer bid) {
		Book book = bookMapper.selectBookByBid(bid);
		if(book == null) {
			return;
		}
		bookMapper.deleteBookByBid(bid);
		
		//清除redis缓存
		jedisClient.del("book");
		jedisClient.hdel("bookWithCat", book.getCategory().getCid()+"");
		
		jmsTemplate.send(deleteBookDestination,new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(book.getBid()+"");
				return textMessage;
			}
		});
	}

}
