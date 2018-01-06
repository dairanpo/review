package com.tobetek.review.service.impl;

import com.tobetek.review.dao.BookDao;
import com.tobetek.review.entity.Book;
import com.tobetek.review.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private BookDao bookDao;

	public Book getById(long bookId) {
		logger.debug("");
		return bookDao.queryById(bookId);
	}

	public List<Book> getList() {
		return bookDao.queryAll(0, 1000);
	}

}
