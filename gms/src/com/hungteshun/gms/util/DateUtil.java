package com.hungteshun.gms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String formatDate(Date date) {
		return new SimpleDateFormat("yyyy/MM/dd").format(date);
	}
}
