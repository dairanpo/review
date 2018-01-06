package com.tobetek.review.dao;

import java.sql.SQLException;

import com.tobetek.review.entity.CommodityInfo;

/**
 * Created by 17090056 on 2018/1/2.
 */
public interface CommodityInfoDao {

	CommodityInfo get(long id) throws SQLException;

	int persist(CommodityInfo entity) throws SQLException;
	
//code
	
}
