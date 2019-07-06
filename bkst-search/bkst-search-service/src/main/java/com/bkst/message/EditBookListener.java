package com.bkst.message;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.bkst.mapper.SearchBookMapper;
import com.bkst.pojo.SearchBook;
import com.bkst.service.SearchBookServiceImpl;

public class EditBookListener implements MessageListener {
	@Autowired
	SolrServer solrServer;
	@Autowired
	SearchBookMapper searchBookMapper;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage)message;
			Integer bid = Integer.parseInt(textMessage.getText());
			
			Thread.sleep(1000);
			SearchBook book = searchBookMapper.selectBookByBid(bid);
			
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id",book.getBid());
			document.addField("book_name", book.getBname());
			document.addField("book_author", book.getAuthor());
			document.addField("book_price", book.getPrice());
			document.addField("book_picture", book.getPicture());
			document.addField("book_category_name", book.getCategoryName());
			document.addField("book_text", book.getText());
			solrServer.deleteById(book.getBid()+"");
			solrServer.add(document);
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
