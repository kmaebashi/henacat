package com.kmaebashi.henacat.servletimpl;
import com.kmaebashi.henacat.servletinterfaces.*;
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.net.*;

class HttpServletRequestImpl implements HttpServletRequest {
    private String method;
    private String characterEncoding;
    private Map<String, String> requestHeader;
    private Map<String, String> parameterMap;
    private Cookie[] cookies;

    @Override
    public String getMethod() {
	return this.method;
    }

    @Override
    public String getParameter(String name) {
	String value = this.parameterMap.get(name);
	String decoded = null;
	try {
	    decoded = URLDecoder.decode(value, this.characterEncoding);
	} catch (UnsupportedEncodingException ex) {
	    throw new AssertionError(ex);
	}
	return decoded;
    }

    @Override
    public void setCharacterEncoding(String env)
	throws UnsupportedEncodingException {
	if (!Charset.isSupported(env)) {
	    throw new UnsupportedEncodingException("encoding.." + env);
	}
	this.characterEncoding = env;
    }

    @Override
    public Cookie[] getCookies() {
	return this.cookies;
    }

    private static Cookie[] parseCookies(String cookieString) {
	if (cookieString == null) {
	    return null;
	}
	String[] cookiePairArray = cookieString.split(";");
	Cookie[] ret = new Cookie[cookiePairArray.length];
	int cookieCount = 0;

	for (String cookiePair : cookiePairArray) {
	    String[] pair = cookiePair.split("=", 2);

	    ret[cookieCount] = new Cookie(pair[0], pair[1]);
	    cookieCount++;
	}

	return ret;
    }

    HttpServletRequestImpl(String method, Map<String, String> requestHeader,
			   Map<String, String> parameterMap) {
	this.method = method;
	this.requestHeader = requestHeader;
	this.cookies = parseCookies(requestHeader.get("COOKIE"));
	this.parameterMap = parameterMap;
    }
}
