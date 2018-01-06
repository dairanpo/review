package com.tobetek.review.service;

import com.tobetek.review.entity.Review;


/**
 * Created by 17090056 on 2018/1/2.
 */
public interface ReviewService {
	
	Review get(Long id);
	
	int persist(Review entity);
//code
}
