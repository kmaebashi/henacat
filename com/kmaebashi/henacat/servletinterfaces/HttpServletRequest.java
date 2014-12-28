
package com.kmaebashi.henacat.servletinterfaces;
import java.util.*;
import java.io.*;

public interface HttpServletRequest {
    String getMethod();
    String getParameter(String name);
    void setCharacterEncoding(String env) throws UnsupportedEncodingException;
    Cookie[] getCookies();
}
