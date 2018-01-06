package com.tobetek.review.service;

import com.tobetek.review.entity.UserInfo;


/**
 * Created by 17090056 on 2018/1/2.
 */
public interface UserInfoService {
	
	UserInfo get(Long id);
	
	int persist(UserInfo entity);
//code
}
