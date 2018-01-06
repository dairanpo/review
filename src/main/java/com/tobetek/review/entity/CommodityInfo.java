package com.tobetek.review.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import java.util.List;

/**
 * the model of these entity
 * Created by 17090056 on 2018/1/2.
 */
@Entity(name="commodity_info")
public class CommodityInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	private long id;
	
	@Column(name="commodity_code")
	private String commodityCode;

	@Column(name="commodity_name")
	private String commodityName;

	@Column(name="charater_id1")
	private String charaterId1;

	@Column(name="charater_desc1")
	private String charaterDesc1;

	@Column(name="charater_id2")
	private String charaterId2;

	@Column(name="charater_desc2")
	private String charaterDesc2;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCharaterId1() {
		return charaterId1;
	}

	public void setCharaterId1(String charaterId1) {
		this.charaterId1 = charaterId1;
	}

	public String getCharaterDesc1() {
		return charaterDesc1;
	}

	public void setCharaterDesc1(String charaterDesc1) {
		this.charaterDesc1 = charaterDesc1;
	}

	public String getCharaterId2() {
		return charaterId2;
	}

	public void setCharaterId2(String charaterId2) {
		this.charaterId2 = charaterId2;
	}

	public String getCharaterDesc2() {
		return charaterDesc2;
	}

	public void setCharaterDesc2(String charaterDesc2) {
		this.charaterDesc2 = charaterDesc2;
	}

	@Override
    public String toString() {
        return "CommodityInfo{" +
        		" id='" + id + "'" +
			" commodityCode='" + commodityCode + "'" +
			", commodityName='" + commodityName + "'" +
			", charaterId1='" + charaterId1 + "'" +
			", charaterDesc1='" + charaterDesc1 + "'" +
			", charaterId2='" + charaterId2 + "'" +
			", charaterDesc2='" + charaterDesc2 + "'" +
			"}";
    }
}
