package com.tobetek.review.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import java.util.List;

/**
 * the model of these entity
 * Created by 17090056 on 2018/1/2.
 */
@Entity(name="demo")
public class Demo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	private long id;
	
//columns

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "Demo{" +
        		" id='" + id + "'//toString}";
    }
}
