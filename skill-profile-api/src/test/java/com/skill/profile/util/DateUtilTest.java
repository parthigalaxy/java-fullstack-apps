package com.skill.profile.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

public class DateUtilTest {
	
	@Test
	public void isDateBeforeValidTest() {
		var days = 10;
		var today = Calendar.getInstance().getTime();
		
		var tenDaysBack = Calendar.getInstance();
		tenDaysBack.add(Calendar.DAY_OF_MONTH, -11);
		
		assertFalse(DateUtil.isDateBefore(days, today));
		assertTrue(DateUtil.isDateBefore(days, tenDaysBack.getTime()));
	}

}
