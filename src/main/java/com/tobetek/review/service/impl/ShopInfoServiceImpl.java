package com.tobetek.review.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobetek.review.dao.ShopInfoDao;
import com.tobetek.review.entity.ShopInfo;
import com.tobetek.review.service.ShopInfoService;

/**
 * Created by 17090056 on 2018/1/2.
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShopInfoDao shopinfoDao;
	
	public ShopInfo get(Long id) {
		logger.debug("arguments:id=" + id);
		try {
			return shopinfoDao.get(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int persist(ShopInfo entity) {
		if(null == entity) {
			return -1;
		}
		try {
			if(entity.getId() != 0 && shopinfoDao.get(entity.getId()) != null) {
				return -1;
			}
			return shopinfoDao.persist(entity);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}
	
//code
	
}
