package com.tobetek.review.dao;

import java.sql.SQLException;

import com.tobetek.review.entity.ShopInfo;

/**
 * Created by 17090056 on 2018/1/2.
 */
public interface ShopInfoDao {

	ShopInfo get(long id) throws SQLException;

	int persist(ShopInfo entity) throws SQLException;
	
//code
	
}
