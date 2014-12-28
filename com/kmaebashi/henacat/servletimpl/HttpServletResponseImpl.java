package com.kmaebashi.henacat.servletimpl;
import com.kmaebashi.henacat.servletinterfaces.*;
import com.kmaebashi.henacat.util.*;
import java.util.*;
import java.io.*;

class HttpServletResponseImpl implements HttpServletResponse {
    String contentType = "application/octet-stream";
    private String characterEncoding = "ISO-8859-1";
    private OutputStream outputStream;
    ArrayList<Cookie> cookies = new ArrayList<Cookie>();
    PrintWriter printWriter;

    @Override
    public void setContentType(String contentType) {
	this.contentType = contentType;
	String[] temp = contentType.split(" *;");
	if (temp.length > 1) {
	    String[] keyValue = temp[1].split("=");
	    if (keyValue.length == 2 && keyValue[0].equals("charset")) {
		setCharacterEncoding(keyValue[1]);
	    }
	}
    }

    @Override
    public void setCharacterEncoding(String charset) {
	this.characterEncoding = charset;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
	this.printWriter
	    = new PrintWriter(new OutputStreamWriter(outputStream,
						     this.characterEncoding));
	return this.printWriter;
    }

    @Override
    public void addCookie(Cookie cookie) {
	this.cookies.add(cookie);
    }

    HttpServletResponseImpl(OutputStream output) {
	this.outputStream = output;
    }
}
