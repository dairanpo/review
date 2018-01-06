package com.tobetek.review.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import java.util.List;

/**
 * the model of these entity
 * Created by 17090056 on 2018/1/2.
 */
@Entity(name="label_names")
public class LabelNames implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	private long id;
	
	@Column(name="label_id")
	private String labelId;

	@Column(name="label_name")
	private String labelName;

	@Column(name="review")
	private long review;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public long getReview() {
		return review;
	}

	public void setReview(long review) {
		this.review = review;
	}

	@Override
    public String toString() {
        return "LabelNames{" +
        		" id='" + id + "'" +
			" labelId='" + labelId + "'" +
			", labelName='" + labelName + "'" +
			", review='" + review + "'" +
			"}";
    }
}
