package com.tobetek.review.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobetek.review.dao.DemoDao;
import com.tobetek.review.entity.Demo;
import com.tobetek.review.service.DemoService;

/**
 * Created by 17090056 on 2018/1/2.
 */
@Service
public class DemoServiceImpl implements DemoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DemoDao demoDao;
	
	public Demo get(Long id) {
		logger.debug("arguments:id=" + id);
		try {
			return demoDao.get(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int persist(Demo entity) {
		int flag = -1;
		try {
			if(null != entity && (entity.getId() == 0 || demoDao.get(entity.getId()) == null) ) {
				flag = demoDao.persist(entity);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}
	
//code
	
}
