package ru.namibios.bdofishbot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

	private DateUtils(){}

	public static String getYYYY_MM_DD_HH_MM_SS() {
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd_HHmmss");
		return format.format(new Date());
	}
	
	public static String getYYYY_MM_DD_HH_MM_SS_S() {
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd_HHmmss_S");
		return format.format(new Date());
	}

	public static String getDDMMYYYY(Date date){
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return format.format(date);
	}

	public static long now(){
		return new Date().getTime();
	}

}