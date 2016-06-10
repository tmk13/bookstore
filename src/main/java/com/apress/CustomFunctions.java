package com.apress;

import javax.servlet.jsp.JspPage;

public class CustomFunctions {
	public static String getJspInitParameter(JspPage page, String param) {
		String returnVal = page.getServletConfig().getInitParameter(param);
		return returnVal;
	}
}
