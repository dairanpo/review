package com.tobetek.review.service;

import com.tobetek.review.entity.LabelNames;


/**
 * Created by 17090056 on 2018/1/2.
 */
public interface LabelNamesService {
	
	LabelNames get(Long id);
	
	int persist(LabelNames entity);
//code
}
