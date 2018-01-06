package com.tobetek.review.web;

import java.util.Map.Entry;

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
	public String forSave(){
		for(int i = 1; i<51; i++) {
			String tmp = "http://review.suning.com/ajax/review_lists/general-000000000154722868-0000000000-total-" +
					i +
					"-default-20-----reviewList.htm?callback=reviewList";
			threadPool.executeRequest(tmp);
		}
		return "demo/forsave";
	}
}

