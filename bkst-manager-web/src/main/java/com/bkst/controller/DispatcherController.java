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
public class DispatcherController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/index.html")
	public String showIndex() {
		return "homepage";
	}
	
	@RequestMapping("/toAddCategory.html")
	public String toAddCategory() {
		return "addCategory";
	}

	@RequestMapping("/toEditCategory/{cid}.html")
	public String toEditCategory(Model model,@PathVariable Integer cid) {
		Category category = categoryService.getCategoryByCid(cid);
		model.addAttribute("category",category);
		return "editCategory";
	}
	
	@RequestMapping("/toImportBook.html")
	public String toImportBook() {
		return "importBook";
	}
}
