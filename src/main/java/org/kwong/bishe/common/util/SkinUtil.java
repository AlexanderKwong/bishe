package org.kwong.bishe.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SkinUtil {
	public static String getStringValue(HttpServletRequest request, String name) {
		Object obj = request.getSession().getAttribute(name);
		if (obj != null)
			return obj.toString();
		else
			return getCookieValue(request, name);
	}

	public static void setStringValue(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			int saveTime) {
		saveSession(request, name, value);
		saveCookie(response, name, value, saveTime);
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		String value = null;

		if (cookie != null) {
			value = cookie.getValue();
			if (value != null) {
				try {
					value =URLDecoder.decode(value,"UTF-8");//将要保存的值进行URLEncoder.encode(value,"utf-8")编码。 
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return value;
	}

	/**
	 * Returns the specified cookie, or <code>null</code> if the cookie does not
	 * exist.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            the name of the cookie.
	 * @return the Cookie object if it exists, otherwise <code>null</code>.
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		// Return null if there are no cookies or the name is invalid.
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		// Otherwise, we do a linear scan for the cookie.
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i];
			}
		}
		return null;
	}

	public static void saveSession(HttpServletRequest request, String name,
			String value) {
		request.getSession().setAttribute(name, value);
	}

	/**
	 * Stores a value in a cookie. This cookie will persist for the amount
	 * specified in the <tt>saveTime</tt> parameter.
	 * 
	 * @param request
	 *            The HttpServletResponse object, known as "response" in a JSP
	 *            page.
	 * @param name
	 *            a name to identify the cookie
	 * @param value
	 *            the value to store in the cookie
	 * @param saveTime
	 *            the time (in seconds) this cookie should live
	 */
	public static void saveCookie(HttpServletResponse response, String name,
			String value, int saveTime) {
		// Check to make sure the new value is not null (appservers like Tomcat
		// 4 blow up if the value is null).
		if (value == null) {
			value = "";
		} else {			
			try {
				value=URLEncoder.encode(value,"UTF-8");//进行解编码。 
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(saveTime);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * Invalidates the specified cookie.
	 */
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName) {
		// invalidate the cookie
		Cookie cookie = new Cookie(cookieName, "");
		// delete the cookie when the user closes their webbrowser
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}