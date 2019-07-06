package com.bkst.service;

import java.util.List;

import com.bkst.pojo.Category;

public interface CategoryService {

	boolean addCategory(Category category);

	List<Category> showCategory();

	Category getCategoryByCid(Integer cid);

	boolean editCategory(Category category);

	void deleteCategory(Integer cid);

}
