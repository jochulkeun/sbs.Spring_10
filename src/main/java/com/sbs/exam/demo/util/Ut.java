package com.sbs.exam.demo.util;

public class Ut {

	public static boolean Empty(Object obj) {
		
		if(obj == null) {
			return true;
		}
		if(obj instanceof String == false) {
			return true;
		}
		String str = (String)obj;
		
		return str.trim().length()==0;
	}

	public static Object f(String format, Object... args) {

		return String.format(format, args);
	}

}
