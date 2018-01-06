package com.tobetek.review.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobetek.review.dao.CommodityInfoDao;
import com.tobetek.review.entity.CommodityInfo;
import com.tobetek.review.service.CommodityInfoService;

/**
 * Created by 17090056 on 2018/1/2.
 */
@Service
public class CommodityInfoServiceImpl implements CommodityInfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommodityInfoDao commodityinfoDao;
	
	public CommodityInfo get(Long id) {
		logger.debug("arguments:id=" + id);
		try {
			return commodityinfoDao.get(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int persist(CommodityInfo entity) {
		if(null == entity) {
			return -1;
		}
		try {
			if(entity.getId() != 0 && commodityinfoDao.get(entity.getId()) != null) {
				return -1;
			} else {
				return commodityinfoDao.persist(entity);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}
	
//code
	
}
