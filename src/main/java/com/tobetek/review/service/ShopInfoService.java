package com.tobetek.review.service;

import com.tobetek.review.entity.ShopInfo;


/**
 * Created by 17090056 on 2018/1/2.
 */
public interface ShopInfoService {
	
	ShopInfo get(Long id);
	
	int persist(ShopInfo entity);
//code
}
