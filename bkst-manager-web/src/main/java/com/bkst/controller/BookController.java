package com.bkst.controller;

import java.io.IOException;
import java.util.List;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bkst.pojo.Book;
import com.bkst.pojo.Category;
import com.bkst.service.BookService;
import com.bkst.service.CategoryService;
import com.bkst.service.SearchBookService;

@Controller
public class BookController {
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SearchBookService searchBookService;
	
	@RequestMapping("/toAddBook.html")
	public String toAddBook(Model model) {
		List<Category> categoryList = categoryService.showCategory();
		model.addAttribute("categoryList",categoryList);
		return "addBook";
	}
	
	@RequestMapping("/addBook.html")
	public String addBook(Book book,MultipartFile pictureFile,Model model,Integer cid) {
		if(book.getBname().trim().equals("") || book.getAuthor().trim().equals("") || book.getPrice()==null
				|| book.getText().trim().equals("") || pictureFile.getSize()==0 || cid==0) {
			model.addAttribute("msg", "请将信息填写完整");
			return toAddBook(model);
		}
		
		Category category = categoryService.getCategoryByCid(cid);
		book.setCategory(category);
		//取文件扩展名
		String originalFilename = pictureFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		try {
			//加载配置文件
			ClientGlobal.init(this.getClass().getResource("/").getPath() + "conf/fastDFS.conf");
			
			//得到客户端
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			StorageServer storageServer = null;
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			
			//上传文件
			String path = storageClient.upload_file1(pictureFile.getBytes(), extName, null);
			String url = IMAGE_SERVER_URL + path;
			
			book.setPicture(url);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
		
		bookService.addBook(book);
		model.addAttribute("book",null);
		model.addAttribute("msg", "添加图书成功");
		return toAddBook(model);
	}
	
	@RequestMapping("/showBook.html")
	public String showBook(Model model,Integer cid) {
		List<Category> categoryList = categoryService.showCategory();
		List<Book> bookList = null;
		if(cid == null) {
			bookList = bookService.showBook(cid);
		}else {
			bookList = bookService.showBookByCid(cid);
		}
		model.addAttribute("bookList", bookList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("cid",cid);
		return "showBook";
	}

	@RequestMapping("/toEditBook/{bid}.html")
	public String toEditBook(Model model,@PathVariable Integer bid) {
		Book book = bookService.getBookByBid(bid);
		List<Category> categoryList = categoryService.showCategory();
		model.addAttribute("book",book);
		model.addAttribute("categoryList",categoryList);
		return "editBook";
	}
	
	@RequestMapping("/editBook.html")
	public String editBook(Book book,MultipartFile pictureFile,Model model,Integer cid,Integer lastCid) {
		if(book.getBname().trim().equals("") || book.getAuthor().trim().equals("") || book.getPrice()==null
				|| book.getText().trim().equals("") || cid==0) {
			model.addAttribute("msg", "请将信息填写完整");
			return toEditBook(model,book.getBid());
		}
		
		Category category = categoryService.getCategoryByCid(cid);
		book.setCategory(category);
		
		if(pictureFile.getSize()!=0) {
			//取文件扩展名
			String originalFilename = pictureFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			try {
				//加载配置文件
				ClientGlobal.init(this.getClass().getResource("/").getPath() + "conf/fastDFS.conf");
				
				//得到客户端
				TrackerClient trackerClient = new TrackerClient();
				TrackerServer trackerServer = trackerClient.getConnection();
				StorageServer storageServer = null;
				StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
				
				//上传文件
				String path = storageClient.upload_file1(pictureFile.getBytes(), extName, null);
				String url = IMAGE_SERVER_URL + path;
				
				book.setPicture(url);
			} catch (IOException | MyException e) {
				e.printStackTrace();
			}
		}
		
		bookService.editBook(book,lastCid);
		model.addAttribute("msg", "修改图书成功");
		return toEditBook(model,book.getBid());
	}
	
	@RequestMapping("/deleteBook/{bid}.html")
	public String deleteBook(Model model,@PathVariable Integer bid,Integer cid) {
		bookService.deleteBook(bid);
		return showBook(model, cid);
	}
	
	@RequestMapping("/importSearchBook.html")
	public String importSearchBook(Model model) {
		if(searchBookService.importBook()) {
			model.addAttribute("msg", "导入索引成功");
			return "importBook";
		}else {
			model.addAttribute("msg", "导入索引失败");
			return "importBook";
		}
	}
}
