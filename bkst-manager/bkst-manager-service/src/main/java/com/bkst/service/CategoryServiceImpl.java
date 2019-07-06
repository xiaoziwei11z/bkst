package com.bkst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkst.mapper.BookMapper;
import com.bkst.mapper.CategoryMapper;
import com.bkst.pojo.Category;

import redis.clients.jedis.JedisCluster;

@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	JedisCluster jedisClient;
	
	public boolean addCategory(Category category) {
		Category result = categoryMapper.selectCategoryByCname(category);
		if(result != null) {
			return false;
		}
		categoryMapper.insertCategory(category);
		return true;
	}

	public List<Category> showCategory() {
		return categoryMapper.selectCategories();
	}

	public Category getCategoryByCid(Integer cid) {
		return categoryMapper.selectCategoryByCid(cid);
	}

	public boolean editCategory(Category category) {
		Category result = categoryMapper.selectCategoryByCname(category);
		if(result != null) {
			return false;
		}
		categoryMapper.updateCategory(category);
		return true;
	}

	public void deleteCategory(Integer cid) {
		bookMapper.deleteBookByCid(cid);
		categoryMapper.deleteCategoryByCid(cid);
		
		//清除redis缓存
		jedisClient.del("book");
		jedisClient.hdel("bookWithCat", cid+"");
	}
}
