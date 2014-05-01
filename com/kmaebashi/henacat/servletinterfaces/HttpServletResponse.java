package com.kmaebashi.henacat.servletinterfaces;
import java.io.*;

public interface HttpServletResponse {
    void setContentType(String contentType);
    void setCharacterEncoding(String charset);
    PrintWriter getWriter() throws IOException;
}
