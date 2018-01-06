package com.tobetek.review.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobetek.review.dao.UserInfoDao;
import com.tobetek.review.entity.UserInfo;
import com.tobetek.review.service.UserInfoService;

/**
 * Created by 17090056 on 2018/1/2.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserInfoDao userinfoDao;
	
	public UserInfo get(Long id) {
		logger.debug("arguments:id=" + id);
		try {
			return userinfoDao.get(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int persist(UserInfo entity) {
		if(null == entity) {
			return -1;
		}
		try {
			if(entity.getId() != 0 && userinfoDao.get(entity.getId()) != null) {
				return -1;
			} else {
				return userinfoDao.persist(entity);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}
	
//code
	
}
