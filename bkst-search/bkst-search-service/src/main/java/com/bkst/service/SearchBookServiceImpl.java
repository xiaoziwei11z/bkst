package com.bkst.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkst.mapper.SearchBookMapper;
import com.bkst.pojo.SearchBook;

@Service("searchBookServiceImpl")
public class SearchBookServiceImpl implements SearchBookService {
	@Autowired
	SolrServer solrServer;
	@Autowired
	SearchBookMapper searchBookMapper;
	
	@Override
	public boolean importBook() {
		try {
			List<SearchBook> bookList = searchBookMapper.selectBooks();
			for (SearchBook book : bookList) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id",book.getBid());
				document.addField("book_name", book.getBname());
				document.addField("book_author", book.getAuthor());
				document.addField("book_price", book.getPrice());
				document.addField("book_picture", book.getPicture());
				document.addField("book_category_name", book.getCategoryName());
				document.addField("book_text", book.getText());
				solrServer.add(document);
			}
			solrServer.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SearchBook> searchBook(String key) {
		SolrQuery query = new SolrQuery();
		query.setQuery(key);
		query.set("df", "book_keywords");

		List<SearchBook> bookList = new ArrayList<>();
		try {
			QueryResponse queryResponse = solrServer.query(query);
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			for (SolrDocument document : solrDocumentList) {
				SearchBook book = new SearchBook();
				book.setBid(Integer.parseInt((String)document.get("id")));
				book.setBname((String)document.get("book_name"));
				book.setAuthor((String)document.get("book_author"));
				book.setCategoryName((String)document.get("book_category_name"));
				book.setPicture((String)document.get("book_picture"));
				book.setPrice((Double)document.get("book_price"));
				book.setText((String)document.get("book_text"));
				bookList.add(book);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return bookList;
	}
}
