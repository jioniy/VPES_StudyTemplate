package com.suresoft.study.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Choi Yeon Ho
 */

@Controller
public class ViewController {

	private static Logger log = LoggerFactory.getLogger("ViewControllerLog");

	/**
	 * Login page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String loginPage(Model model) {
		log.info("REQUEST url - login");
		return "login";
	}

	/**
	 * Root Page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String mainPage(Model model) {
		log.info("REQUEST url - /");
		return "main";
	}

	/**
	 * View 1 Page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/page_1")
	public String view1Page(Model model) {
		log.info("REQUEST url - page 1");
		return "page1";
	}

	/**
	 * View 2 Page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/page_2")
	public String view2Page(Model model) {
		log.info("REQUEST url - page 2");
		return "page2";
	}

	/**
	 * View 3 Page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/page_3")
	public String view3Page(Model model) {
		log.info("REQUEST url - page 3");
		return "page3";
	}
	
	/**
	 * View Join Page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/join")
	public String joinPage(Model model) {
		log.info("REQUEST url - join");
		return "join";
	}
}
