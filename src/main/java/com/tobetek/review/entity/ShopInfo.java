package com.tobetek.review.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import java.util.List;

/**
 * the model of these entity
 * Created by 17090056 on 2018/1/2.
 */
@Entity(name="shop_info")
public class ShopInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	private long id;
	
	@Column(name="shop_id")
	private String shopId;

	@Column(name="shop_name")
	private String shopName;

	@Column(name="shop_type")
	private String shopType;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	@Override
    public String toString() {
        return "ShopInfo{" +
        		" id='" + id + "'" +
			" shopId='" + shopId + "'" +
			", shopName='" + shopName + "'" +
			", shopType='" + shopType + "'" +
			"}";
    }
}
