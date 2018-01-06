package com.tobetek.review.service;

import com.tobetek.review.entity.Demo;


/**
 * Created by 17090056 on 2018/1/2.
 */
public interface DemoService {
	
	Demo get(Long id);
	
	int persist(Demo entity);
//code
}
