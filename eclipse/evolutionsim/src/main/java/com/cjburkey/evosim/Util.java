package com.cjburkey.evosim;

import java.util.Locale;

public final class Util {
	
	public static String formatDecimal(Locale locale, double d, int places) {
		return String.format(locale, "%." + places + "f", d);
	}
	
	public static String formatDecimal(double d, int places) {
		return formatDecimal(Locale.US, d, places);
	}
	
	public static String format0Decimal(double d) {
		return formatDecimal(d, 0);
	}
	
	public static String format1Decimal(double d) {
		return formatDecimal(d, 1);
	}
	
	public static String format2Decimal(double d) {
		return formatDecimal(d, 2);
	}
	
	public static String format3Decimal(double d) {
		return formatDecimal(d, 3);
	}
	
	public static String format4Decimal(double d) {
		return formatDecimal(d, 4);
	}
	
	public static String format5Decimal(double d) {
		return formatDecimal(d, 5);
	}
	
}