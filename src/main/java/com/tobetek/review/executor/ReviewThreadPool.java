package com.tobetek.review.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.tobetek.review.entity.Review;
import com.tobetek.review.service.ReviewService;
import com.tobetek.review.util.HttpUtil;
import com.tobetek.review.util.JsonUtil;

@Service
public class ReviewThreadPool {
	
	private final static Logger logger = LoggerFactory.getLogger(ReviewThreadPool.class);
	
	private static ReviewService reviewService = null;
	/**
	 * 线程池
	 */
	private ExecutorService fixedThreadPool;
	
	public ReviewThreadPool() {
		this(Runtime.getRuntime().availableProcessors());
	}
	
	public ReviewThreadPool(int n) {
		fixedThreadPool = Executors.newFixedThreadPool(n);
		
	}
	public void executeRequest(String uri) {
		fixedThreadPool.execute(new RunableTest(uri));
	}

	public void executeRequest(String uri, long wait) {
		fixedThreadPool.execute(new RunableTest(uri, wait));
	}

	public ExecutorService getFixedThreadPool() {
		return fixedThreadPool;
	}
	
	private static class RunableTest implements Runnable {
		private String uri;
		private long waitTime;
		
		public RunableTest(String uri) {
			this.uri = uri;
			this.waitTime = new Double(Math.random()*1000).longValue();
		}
		
		public RunableTest(String uri, long waitTime) {
			this.uri = uri;
			this.waitTime = waitTime;
		}

	    private static synchronized void init() {
	    	if(reviewService == null) {
	    		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
	    		reviewService = ac.getBean(ReviewService.class);
	    	}
		}
	    
		public void run() {
			if(reviewService == null) {
				init();
			}
			int k = 0;
			int count = 0;
			while(true && count<100) {
				k++;
				String tmp = uri;
				if(uri.indexOf("tmp") != -1) {
					tmp = uri.replace("tmp", String.valueOf(k));
				} else {
					count = 500;
				}
				String result = HttpUtil.sendGetString(tmp);
				try {
					synchronized(this) {
						this.wait(waitTime);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(result == null || "".equals(result)) {
					break;
				}
				int from = result.indexOf("(") + 1;
				int to = result.lastIndexOf(")");
				JSONArray arr;
				try {
					JSONObject json = JSONObject.fromObject(result.substring(from, to) );
					arr = json.getJSONArray("commodityReviews");
					logger.info(tmp.substring(60,90) + "==" + result.substring(0,40));
				} catch (Exception e) {
					logger.info(tmp.substring(60,90) + "$$" + result.substring(0,40));
					k--;
					if((count++) < 100) {
						continue;
					} else {
						logger.error(tmp.substring(60,90) + "------------------100--------------------");
						break;
					}
				}
				if(null == arr || arr.size()==0) {
					break;
				}
		        for(int i=0; i< arr.size(); i++) {
		        	reviewService.persist(JsonUtil.json2Entity(arr.getString(i), Review.class));
		        }
			}
		}
	}
}
