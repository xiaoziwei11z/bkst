package com.bkst.service;

import java.util.List;

import com.bkst.pojo.SearchBook;

public interface SearchBookService {

	boolean importBook();

	List<SearchBook> searchBook(String key);
	
}
