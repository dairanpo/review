package com.tobetek.review.dao;

import java.sql.SQLException;

import com.tobetek.review.entity.Review;

/**
 * Created by 17090056 on 2018/1/2.
 */
public interface ReviewDao {

	Review get(long id) throws SQLException;

	int persist(Review entity) throws SQLException;
	
//code
	
}
