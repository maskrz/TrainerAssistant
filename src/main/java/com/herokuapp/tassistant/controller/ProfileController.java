package com.herokuapp.tassistant.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
	
	@Transactional
	@RequestMapping(value = "/")
	public void profile() {
		System.out.println("here!");
	}
}
