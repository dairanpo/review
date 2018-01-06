package com.tobetek.review.dao;

import java.sql.SQLException;

import com.tobetek.review.entity.Demo;

/**
 * Created by 17090056 on 2018/1/2.
 */
public interface DemoDao {

	Demo get(long id) throws SQLException;

	int persist(Demo entity) throws SQLException;
	
//code
	
}
