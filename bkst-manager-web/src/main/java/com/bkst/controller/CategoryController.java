package com.bkst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkst.pojo.Category;
import com.bkst.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/addCategory.html")
	public String addCategory(Model model,Category category) {
		if(category.getCname().trim().equals("")) {
			model.addAttribute("msg","输入不可为空");
			return "addCategory";
		}
		if(categoryService.addCategory(category)) {
			model.addAttribute("msg","添加分类成功");
		}else {
			model.addAttribute("cname", category.getCname());
			model.addAttribute("msg", "该分类已存在");
		}
		return "addCategory";
	}
	
	@RequestMapping("/showCategory.html")
	public String showCategory(Model model){
		List<Category> categoryList = categoryService.showCategory();
		model.addAttribute("categoryList", categoryList);
		return "showCategory";
	}
	
	@RequestMapping("/editCategory.html")
	public String editCategory(Model model,Category category) {
		if(category.getCname().trim().equals("")) {
			model.addAttribute("msg","输入不可为空");
			return "editCategory";
		}
		if(categoryService.editCategory(category)) {
			model.addAttribute("msg","修改分类成功");
			Category result = new Category();
			result.setCid(category.getCid());
			model.addAttribute("category", result);
		}else {
			model.addAttribute("msg", "该分类已存在");
		}
		return "editCategory";
	}
	
	@RequestMapping("/deleteCategory/{cid}.html")
	public String deleteCategory(Model model,@PathVariable Integer cid) {
		categoryService.deleteCategory(cid);
		return showCategory(model);
	}
}
