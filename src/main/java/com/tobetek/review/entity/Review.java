package com.tobetek.review.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import java.util.List;

/**
 * the model of these entity
 * Created by 17090056 on 2018/1/2.
 */
@Entity(name="review")
public class Review implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	private long id;
	
	@Column(name="best_tag")
	private String bestTag;

	@Column(name="commodity_review_id")
	private String commodityReviewId;

	@Column(name="content")
	private String content;

	@Column(name="publish_time")
	private String publishTime;

	@Column(name="device_type")
	private String deviceType;

	@Column(name="source_system")
	private String sourceSystem;

	@Column(name="anon_flag")
	private String anonFlag;

	private UserInfo userInfo;
	@Column(name="user_info_id")
	private long userInfoId;
	@Column(name="quality_star")
	private String qualityStar;

	@Column(name="best_flag")
	private String bestFlag;

	private ShopInfo shopInfo;
	@Column(name="shop_info_id")
	private long shopInfoId;
	@Column(name="pic_video_flag")
	private String picVideoFlag;

	@Column(name="content_length")
	private String contentLength;

	private CommodityInfo commodityInfo;
	@Column(name="commodity_info_id")
	private long commodityInfoId;
	@Column(name="again_flag")
	private String againFlag;

	private List<LabelNames> labelNames;
	@Column(name="score")
	private String score;

	@Column(name="useful_cnt")
	private String usefulCnt;

	@Column(name="reply_flag")
	private String replyFlag;

	@Column(name="is_show_review_detail")
	private String isShowReviewDetail;

	@Column(name="style_id")
	private String styleId;

	@Column(name="group_id")
	private String groupId;

	@Column(name="again_review_img_flag")
	private String againReviewImgFlag;

	@Column(name="voice_flag")
	private String voiceFlag;

	@Column(name="small_video_flag")
	private String smallVideoFlag;

	@Column(name="return_code")
	private String returnCode;

	@Column(name="return_msg")
	private String returnMsg;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBestTag() {
		return bestTag;
	}

	public void setBestTag(String bestTag) {
		this.bestTag = bestTag;
	}

	public String getCommodityReviewId() {
		return commodityReviewId;
	}

	public void setCommodityReviewId(String commodityReviewId) {
		this.commodityReviewId = commodityReviewId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getAnonFlag() {
		return anonFlag;
	}

	public void setAnonFlag(String anonFlag) {
		this.anonFlag = anonFlag;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(long userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getQualityStar() {
		return qualityStar;
	}

	public void setQualityStar(String qualityStar) {
		this.qualityStar = qualityStar;
	}

	public String getBestFlag() {
		return bestFlag;
	}

	public void setBestFlag(String bestFlag) {
		this.bestFlag = bestFlag;
	}

	public ShopInfo getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(ShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}

	public long getShopInfoId() {
		return shopInfoId;
	}

	public void setShopInfoId(long shopInfoId) {
		this.shopInfoId = shopInfoId;
	}

	public String getPicVideoFlag() {
		return picVideoFlag;
	}

	public void setPicVideoFlag(String picVideoFlag) {
		this.picVideoFlag = picVideoFlag;
	}

	public String getContentLength() {
		return contentLength;
	}

	public void setContentLength(String contentLength) {
		this.contentLength = contentLength;
	}

	public CommodityInfo getCommodityInfo() {
		return commodityInfo;
	}

	public void setCommodityInfo(CommodityInfo commodityInfo) {
		this.commodityInfo = commodityInfo;
	}

	public long getCommodityInfoId() {
		return commodityInfoId;
	}

	public void setCommodityInfoId(long commodityInfoId) {
		this.commodityInfoId = commodityInfoId;
	}

	public String getAgainFlag() {
		return againFlag;
	}

	public void setAgainFlag(String againFlag) {
		this.againFlag = againFlag;
	}

	public List<LabelNames> getLabelNames() {
		return labelNames;
	}

	public void setLabelNames(List<LabelNames> labelNames) {
		this.labelNames = labelNames;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getUsefulCnt() {
		return usefulCnt;
	}

	public void setUsefulCnt(String usefulCnt) {
		this.usefulCnt = usefulCnt;
	}

	public String getReplyFlag() {
		return replyFlag;
	}

	public void setReplyFlag(String replyFlag) {
		this.replyFlag = replyFlag;
	}

	public String getIsShowReviewDetail() {
		return isShowReviewDetail;
	}

	public void setIsShowReviewDetail(String isShowReviewDetail) {
		this.isShowReviewDetail = isShowReviewDetail;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAgainReviewImgFlag() {
		return againReviewImgFlag;
	}

	public void setAgainReviewImgFlag(String againReviewImgFlag) {
		this.againReviewImgFlag = againReviewImgFlag;
	}

	public String getVoiceFlag() {
		return voiceFlag;
	}

	public void setVoiceFlag(String voiceFlag) {
		this.voiceFlag = voiceFlag;
	}

	public String getSmallVideoFlag() {
		return smallVideoFlag;
	}

	public void setSmallVideoFlag(String smallVideoFlag) {
		this.smallVideoFlag = smallVideoFlag;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	@Override
    public String toString() {
        return "Review{" +
        		" id='" + id + "'" +
			" bestTag='" + bestTag + "'" +
			", commodityReviewId='" + commodityReviewId + "'" +
			", content='" + content + "'" +
			", publishTime='" + publishTime + "'" +
			", deviceType='" + deviceType + "'" +
			", sourceSystem='" + sourceSystem + "'" +
			", anonFlag='" + anonFlag + "'" +
			", userInfo='" + userInfo + "'" +
			", qualityStar='" + qualityStar + "'" +
			", bestFlag='" + bestFlag + "'" +
			", shopInfo='" + shopInfo + "'" +
			", picVideoFlag='" + picVideoFlag + "'" +
			", contentLength='" + contentLength + "'" +
			", commodityInfo='" + commodityInfo + "'" +
			", againFlag='" + againFlag + "'" +
			", labelNames='" + labelNames + "'" +
			", score='" + score + "'" +
			", usefulCnt='" + usefulCnt + "'" +
			", replyFlag='" + replyFlag + "'" +
			", isShowReviewDetail='" + isShowReviewDetail + "'" +
			", styleId='" + styleId + "'" +
			", groupId='" + groupId + "'" +
			", againReviewImgFlag='" + againReviewImgFlag + "'" +
			", voiceFlag='" + voiceFlag + "'" +
			", smallVideoFlag='" + smallVideoFlag + "'" +
			", returnCode='" + returnCode + "'" +
			", returnMsg='" + returnMsg + "'" +
			"}";
    }
}
