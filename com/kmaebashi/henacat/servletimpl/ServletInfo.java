package com.kmaebashi.henacat.servletimpl;
import com.kmaebashi.henacat.servletinterfaces.*;
import java.util.*;

public class ServletInfo {
    private static HashMap<String, ServletInfo> servletCollection =
	new HashMap<String, ServletInfo>();

    private String urlPattern;
    String servletDirectory;
    String servletClassName;
    HttpServlet servlet;

    public ServletInfo(String urlPattern, String servletDirectory,
		       String servletClassName) {
	this.urlPattern = urlPattern;
	this.servletDirectory = servletDirectory;
	this.servletClassName = servletClassName;
    }

    public static void addServlet(String urlPattern, String servletDirectory,
				  String servletClassName) {
	servletCollection.put(urlPattern,
			      new ServletInfo(urlPattern, servletDirectory,
					      servletClassName));
    }

    public static ServletInfo searchServlet(String urlPattern) {
	
	ServletInfo info = servletCollection.get(urlPattern);
	return servletCollection.get(urlPattern);
    }
}
