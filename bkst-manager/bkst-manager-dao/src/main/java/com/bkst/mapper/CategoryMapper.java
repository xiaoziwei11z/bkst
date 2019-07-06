package com.bkst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bkst.pojo.Category;

public interface CategoryMapper {

	Category selectCategoryByCname(Category category);

	@Insert("INSERT INTO tb_category(cname) VALUES(#{cname})")
	void insertCategory(Category category);

	@Select("SELECT * FROM tb_category")
	List<Category> selectCategories();

	@Select("SELECT * FROM tb_category WHERE cid=#{cid}")
	Category selectCategoryByCid(Integer cid);

	@Update("UPDATE tb_category SET cname=#{cname} WHERE cid=#{cid}")
	void updateCategory(Category category);

	@Delete("DELETE FROM tb_category WHERE cid=#{cid}")
	void deleteCategoryByCid(Integer cid);

}
