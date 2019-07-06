package com.bkst.service;

import java.util.List;

import com.bkst.pojo.Book;

public interface BookService {

	void addBook(Book book);

	List<Book> showBook(Integer cid);
	
	List<Book> showBookByCid(Integer cid);

	Book getBookByBid(Integer bid);

	void editBook(Book book,Integer lastCid);

	void deleteBook(Integer bid);

}
