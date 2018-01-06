package com.tobetek.review.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobetek.review.dao.ReviewDao;
import com.tobetek.review.entity.CommodityInfo;
import com.tobetek.review.entity.LabelNames;
import com.tobetek.review.entity.Review;
import com.tobetek.review.entity.ShopInfo;
import com.tobetek.review.entity.UserInfo;
import com.tobetek.review.service.CommodityInfoService;
import com.tobetek.review.service.LabelNamesService;
import com.tobetek.review.service.ReviewService;
import com.tobetek.review.service.ShopInfoService;
import com.tobetek.review.service.UserInfoService;

/**
 * Created by 17090056 on 2018/1/2.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private CommodityInfoService commodityInfoService;
	@Autowired
	private LabelNamesService labelNamesService;
	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private UserInfoService userInfoService;
	
	public Review get(Long id) {
		logger.debug("arguments:id=" + id);
		try {
			return reviewDao.get(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int persist(Review entity) {
		int result = -1;
		try {
			if(null != entity && (entity.getId() == 0 || reviewDao.get(entity.getId()) == null)) {
				
				ShopInfo shopInfo = entity.getShopInfo();
				shopInfoService.persist(shopInfo);
				entity.setShopInfoId(shopInfo.getId());
				
				UserInfo userInfo = entity.getUserInfo();
				userInfoService.persist(userInfo);
				entity.setUserInfoId(userInfo.getId());

				CommodityInfo commodityInfo = entity.getCommodityInfo();
				commodityInfoService.persist(commodityInfo);
				entity.setCommodityInfoId(commodityInfo.getId());
				
				result = reviewDao.persist(entity);
				
				for(LabelNames obj : entity.getLabelNames()) {
					obj.setReview(entity.getId());
					labelNamesService.persist(obj);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
//code
	
}
