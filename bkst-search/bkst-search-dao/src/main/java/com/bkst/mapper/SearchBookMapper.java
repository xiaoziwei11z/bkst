package com.bkst.mapper;

import java.util.List;

import com.bkst.pojo.SearchBook;

public interface SearchBookMapper {

	List<SearchBook> selectBooks();

	SearchBook selectBookByBid(Integer bid);
	
}
