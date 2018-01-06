package com.tobetek.review.service;

import com.tobetek.review.entity.CommodityInfo;


/**
 * Created by 17090056 on 2018/1/2.
 */
public interface CommodityInfoService {
	
	CommodityInfo get(Long id);
	
	int persist(CommodityInfo entity);
//code
}
