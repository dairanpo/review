package com.tobetek.review.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tobetek.review.entity.Demo;
import com.tobetek.review.executor.ReviewThreadPool;
import com.tobetek.review.service.DemoService;

@Controller
@RequestMapping("/demo") // url:/模块/资源/{id}/细分 /seckill/list
public class DemoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DemoService demoService;
	
	@Autowired
	private ReviewThreadPool threadPool;
	
	@RequestMapping(value = "/insert")
	public String insert(HttpServletRequest request, Demo demo, Model model){
		int n = demoService.persist(demo );
		logger.debug("insert");
		model.addAttribute("n", n);
		model.addAttribute("demo", demo);
		return "demo/result";
	}
	
	@RequestMapping(value = "/save")
	public String saveTest(HttpServletRequest request, Model model) {
		String uri = request.getParameter("uri");
		logger.info(uri);
		threadPool.executeRequest(uri);
		return "demo/save";
	}

    @RequestMapping(value="/forsave")
	public String forSave(HttpServletRequest request, Model model){
    	String from = request.getParameter("from");
    	String to = request.getParameter("to");
    	if(null == from || "".equals(from)) {
    		from = "0";
    	}
    	if(null == to || "".equals(to)) {
    		to = "100";
    	}
    	
    	for(long k=Long.valueOf(from); k<Long.valueOf(to); k++) {
    		String kstr = String.valueOf(k);
    		while( "000000000154722868".length() != kstr.length() ) {
    			kstr = "0" + kstr;
    		}
			String tmp = "https://review.suning.com/ajax/review_lists/general-"
					+ kstr
					+ "-0000000000-total-tmp-default-20-----reviewList.htm?callback=reviewList";
			threadPool.executeRequest(tmp);
	    	model.addAttribute("uri", tmp);
    	}
    	model.addAttribute("from", from);
    	model.addAttribute("to", to);
		return "demo/forsave";
	}
    @RequestMapping(value="/forsend")
    public String forSend(HttpServletRequest request, Model model) {
    	ReviewThreadPool pool = new ReviewThreadPool(1);
    	long from = 154000000l;
    	long to = 154000300l;
    	String uri = "http://127.0.0.1:8080/review-web/demo/forsave?from="
    			+ String.valueOf(from)
    			+ "&to="
    			+ String.valueOf(to);
    	for(;to < 154500000l; from +=300, to +=300) {
    		pool.executeRequest(uri , 1000*60);
    	}
    	return "demo/forsave";
    }
}

