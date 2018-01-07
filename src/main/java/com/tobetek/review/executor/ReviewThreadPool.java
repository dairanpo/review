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
	private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*10);
	
	public void executeRequest(String uri) {
		fixedThreadPool.execute(new RunableTest(uri));
	}

	public ExecutorService getFixedThreadPool() {
		return fixedThreadPool;
	}
	
	private static class RunableTest implements Runnable {
		private int count = 0;
		private String uri;
		
		public RunableTest(String uri) {
			this.uri = uri;
		}

	    private synchronized void init() {
	    	if(reviewService == null) {
	    		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
	    		reviewService = ac.getBean(ReviewService.class);
	    	}
		}
	    
		public void run() {
			if(reviewService == null) {
				init();
			}
			for(int k=1; k<51; k++) {
				
				try {
					synchronized(this) {
						this.wait(800);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(uri.indexOf("tmp") == -1) {
					k = 51;
				}
				String tmp = uri.replace("tmp", String.valueOf(k));
				String result = HttpUtil.sendGetString(tmp);
				if(result == null || "".equals(result)) {
					return;
				}
				int from = result.indexOf("(") + 1;
				int to = result.lastIndexOf(")");
				JSONArray arr;
				try {
					JSONObject json = JSONObject.fromObject(result.substring(from, to) );
					arr = json.getJSONArray("commodityReviews");
					logger.info(tmp.substring(60,90) + "==" + result.substring(0,40));
				} catch (Exception e) {
					k--;
					logger.error(tmp.substring(60,90));
					if((count++) < 100) {
						continue;
					} else {
						logger.error("------------------100--------------------");
						return;
					}
				}
				if(null == arr || arr.size()==0) {
					return;
				}
		        for(int i=0; i< arr.size(); i++) {
		        	reviewService.persist(JsonUtil.json2Entity(arr.getString(i), Review.class));
		        }
			}
		}
	}

}
