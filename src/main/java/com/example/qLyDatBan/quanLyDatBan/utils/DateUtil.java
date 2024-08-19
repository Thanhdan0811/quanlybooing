package com.example.qLyDatBan.quanLyDatBan.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
	public static Date convertISOStrToDateUtil(String dateStr) {
		Instant instant = Instant.parse(dateStr);

		return Date.from(instant);

	}

	public static java.sql.Date convertISOStrToDateSql(String dateStr) {
		Instant instant = Instant.parse(dateStr);

		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

		return java.sql.Date.valueOf(localDate);

	}

}
