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
	private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
	public void executeRequest(String uri) {
		fixedThreadPool.execute(new RunableTest(uri));
	}

	public ExecutorService getFixedThreadPool() {
		return fixedThreadPool;
	}
	
	private static class RunableTest implements Runnable {
		private String uri;
		
		public RunableTest(String uri) {
			this.uri = uri;
		}

	    private void init() {
	    	if(reviewService == null) {
	    		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
	    		reviewService = ac.getBean(ReviewService.class);
	    	}
		}
	    
		public void run() {
			init();
			logger.error("----"+uri+"----"+Runtime.getRuntime().availableProcessors());
			String result = HttpUtil.sendGetString(uri);
			if(result == null) {
				return;
			}
			int from = result.indexOf("(") + 1;
			int to = result.lastIndexOf(")");
			JSONObject json = JSONObject.fromObject(result.substring(from, to) );
			JSONArray arr = json.getJSONArray("commodityReviews");
			if(null == arr || arr.size()==0) {
				return;
			}
			for(int i=0; i< arr.size(); i++) {
				logger.info(arr.getString(0));
			}
	        for(int i=0; i< arr.size(); i++) {
	        	reviewService.persist(JsonUtil.json2Entity(arr.getString(i), Review.class));
	        }
		}

//		private <T> void forOut(Collection<T> list) {
//	        for(T t : list) {
//	        	logger.info(t.toString());
//	        }
//	    }
	}

}
