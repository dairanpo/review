package com.tobetek.review.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobetek.review.dao.LabelNamesDao;
import com.tobetek.review.entity.LabelNames;
import com.tobetek.review.service.LabelNamesService;

/**
 * Created by 17090056 on 2018/1/2.
 */
@Service
public class LabelNamesServiceImpl implements LabelNamesService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LabelNamesDao labelnamesDao;
	
	public LabelNames get(Long id) {
		logger.debug("arguments:id=" + id);
		try {
			return labelnamesDao.get(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int persist(LabelNames entity) {
		if(null == entity) {
			return -1;
		}
		try {
			if(entity.getId() != 0 && labelnamesDao.get(entity.getId()) != null) {
				return -1;
			} else {
				return labelnamesDao.persist(entity);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}
	
//code
	
}
