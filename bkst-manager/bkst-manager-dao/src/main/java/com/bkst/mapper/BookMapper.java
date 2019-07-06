package com.bkst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bkst.pojo.Book;

public interface BookMapper {
	@Insert("INSERT INTO tb_book(bid,bname,author,price,cid,picture,text) "
			+ "VALUES(#{bid},#{bname},#{author},#{price},#{category.cid},#{picture},#{text})")
	void insertBook(Book book);

	@Delete("DELETE FROM tb_book WHERE cid=#{cid}")
	void deleteBookByCid(Integer cid);

	List<Book> selectBooks(@Param("cid") Integer cid);

	Book selectBookByBid(Integer bid);

	@Update("UPDATE tb_book SET bname=#{bname},author=#{author},price=#{price},"
			+ "cid=#{category.cid},picture=#{picture},text=#{text}"
			+ "WHERE bid=#{bid}")
	void updateBook(Book book);

	@Delete("DELETE FROM tb_book WHERE bid=#{bid}")
	void deleteBookByBid(Integer bid);
	
}
