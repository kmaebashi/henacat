package com.kmaebashi.henacat.servletimpl;
import com.kmaebashi.henacat.servletinterfaces.*;
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.net.*;

class HttpServletRequestImpl implements HttpServletRequest {
    private String method;
    private String characterEncoding;
    private Map<String, String> parameterMap;

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

    HttpServletRequestImpl(String method, Map<String, String> parameterMap) {
	this.method = method;
	this.parameterMap = parameterMap;
    }
}
