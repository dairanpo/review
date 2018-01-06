package com.tobetek.review.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import java.util.List;

/**
 * the model of these entity
 * Created by 17090056 on 2018/1/2.
 */
@Entity(name="user_info")
public class UserInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	private long id;
	
	@Column(name="nick_name")
	private String nickName;

	@Column(name="level_id")
	private String levelId;

	@Column(name="level_name")
	private String levelName;

	@Column(name="img_url")
	private String imgUrl;

	@Column(name="is_vip")
	private String isVip;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	@Override
    public String toString() {
        return "UserInfo{" +
        		" id='" + id + "'" +
			" nickName='" + nickName + "'" +
			", levelId='" + levelId + "'" +
			", levelName='" + levelName + "'" +
			", imgUrl='" + imgUrl + "'" +
			", isVip='" + isVip + "'" +
			"}";
    }
}
