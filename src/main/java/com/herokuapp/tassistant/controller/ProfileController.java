package com.herokuapp.tassistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herokuapp.tassistant.bean.general.DailyRecordBean;
import com.herokuapp.tassistant.bean.general.Response;
import com.herokuapp.tassistant.bean.general.ResponseCode;
import com.herokuapp.tassistant.database.entity.DailyRecord;
import com.herokuapp.tassistant.database.entity.User;
import com.herokuapp.tassistant.service.user.UserService;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

	@Autowired
	UserService userService;

	@RequestMapping()
	public String profile() {
		return "/profile/profile";
	}

	@RequestMapping(value = "/dailyRecords", method = RequestMethod.GET)
	public @ResponseBody List<DailyRecord> getDailyRecords() {
		// TODO move to service
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<DailyRecord> resultList = getUserService().getUserByColumnValue("login", auth.getName()).get()
				.getDailyRecords();
		resultList.sort((d1, d2) -> d1.getDailyRecordId().getRecordDate().compareTo(d2.getDailyRecordId().getRecordDate()));
		return resultList;
	}

	@RequestMapping(value = "/merge", method = RequestMethod.POST)
	public @ResponseBody DailyRecord merge(@RequestBody DailyRecordBean dailyRecordBean) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = getUserService().getUserByColumnValue("login", auth.getName()).get();
		return getUserService().addRecord(user, dailyRecordBean);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
