package com.herokuapp.tassistant.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.herokuapp.tassistant.database.dao.GeneralRepository;
import com.herokuapp.tassistant.database.entity.DailyRecord;
import com.herokuapp.tassistant.database.entity.User;
import com.herokuapp.tassistant.util.calendar.DateTimeUtil;

@Controller
@RequestMapping(value = "/drecord")
public class DailyRecordController {

	@Autowired
	private DateTimeUtil dateTimeUtil;
	
	@Autowired
	private GeneralRepository generalRepository;
	
	@Transactional
	@RequestMapping(value = "/test")
	public void test() {
		/*DailyRecordId id = new DailyRecordId();
		id.setUserId(21);
		id.setRecordDate(getDateTimeUtil().currentTimestamp());
		DailyRecord record = new DailyRecord();
		record.setDailyRecordId(id);
		record.setT1(1);
		record.setT2(2);
		record.setT3(3);
		record.setWsr(2.3);
		getGeneralRepository().merge(record);
		*/
		User user = getGeneralRepository().findById(User.class, 21).get();
		DailyRecord record = user.getDailyRecords().get(0);
		record.setT1(5);
		getGeneralRepository().merge(user);
		
		
		System.out.println("here!");
	}

	public DateTimeUtil getDateTimeUtil() {
		return dateTimeUtil;
	}

	public void setDateTimeUtil(DateTimeUtil dateTimeUtil) {
		this.dateTimeUtil = dateTimeUtil;
	}

	public GeneralRepository getGeneralRepository() {
		return generalRepository;
	}

	public void setGeneralRepository(GeneralRepository generalRepository) {
		this.generalRepository = generalRepository;
	}
}
