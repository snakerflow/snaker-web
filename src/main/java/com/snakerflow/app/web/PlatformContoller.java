package com.snakerflow.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 平台级别Controller
 * @author yuqs
 * @since 0.1
 */
@Controller
public class PlatformContoller {
	/**
	 * 登录成功后系统首页（一般存在top、left、right三大区域通过frameset包含）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index" ,method=RequestMethod.GET)
	public String main(Model model) {
		return "system/index";
	}
	
	/**
	 * 如果首页为frameset布局，则存在top
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/top" ,method=RequestMethod.GET)
	public String top(Model model) {
		return "system/top";
	}
	
	/**
	 * 如果首页为frameset布局，则存在left
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/left" ,method=RequestMethod.GET)
	public String left(Model model) {
		return "system/left";
	}
	
	/**
	 * 如果首页为frameset布局，则存在middle时
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/middle" ,method=RequestMethod.GET)
	public String middle(Model model) {
		return "system/middle";
	}
	
	/**
	 * 如果首页为frameset布局，则存在right
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/right" ,method=RequestMethod.GET)
	public String right(Model model) {
		return "system/right";
	}
}
