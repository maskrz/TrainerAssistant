package com.herokuapp.tassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herokuapp.tassistant.bean.general.Response;
import com.herokuapp.tassistant.database.entity.User;
import com.herokuapp.tassistant.service.user.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@RequestMapping()
	public String registration() {
		return "/registration/registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Response register(@RequestBody User user) {
		return getUserService().registerUser(user);
	}

	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public String confirm(@RequestParam("email") String email, @RequestParam("token") String token, Model model) {
		model.addAttribute("response", getUserService().confirmRegistration(email, token).getMessage());
		return "/registration/confirmation";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
