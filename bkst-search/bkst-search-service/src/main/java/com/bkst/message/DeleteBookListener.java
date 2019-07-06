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

public class DeleteBookListener implements MessageListener {
	@Autowired
	SolrServer solrServer;
	@Autowired
	SearchBookMapper searchBookMapper;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage)message;
			Integer bid = Integer.parseInt(textMessage.getText());
			solrServer.deleteById(bid+"");
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
