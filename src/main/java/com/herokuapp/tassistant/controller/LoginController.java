package com.herokuapp.tassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping()
	public String login() {
		return "/login/login";
	}


}
