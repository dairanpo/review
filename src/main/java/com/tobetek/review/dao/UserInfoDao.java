package com.tobetek.review.dao;

import java.sql.SQLException;

import com.tobetek.review.entity.UserInfo;

/**
 * Created by 17090056 on 2018/1/2.
 */
public interface UserInfoDao {

	UserInfo get(long id) throws SQLException;

	int persist(UserInfo entity) throws SQLException;
	
//code
	
}
