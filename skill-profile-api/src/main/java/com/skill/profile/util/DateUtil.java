package com.skill.profile.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {
	
	private DateUtil() {}
	
	public static boolean isDateBefore(int days, Date date) {
		
		log.info("days >> {} >> date >> {} ", days,date.toString());
		
		LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate daysBefore = LocalDate.now(ZoneId.systemDefault());
		daysBefore = daysBefore.minusDays(days);
		
		log.info("localDate >> {} >> daysBefore >> {} ", localDate.toString() ,daysBefore.toString());
		return localDate.isBefore(daysBefore);
	}

}
