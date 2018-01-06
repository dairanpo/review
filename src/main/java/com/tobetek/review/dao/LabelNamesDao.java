package com.tobetek.review.dao;

import java.sql.SQLException;

import com.tobetek.review.entity.LabelNames;

/**
 * Created by 17090056 on 2018/1/2.
 */
public interface LabelNamesDao {

	LabelNames get(long id) throws SQLException;

	int persist(LabelNames entity) throws SQLException;
	
//code
	
}
