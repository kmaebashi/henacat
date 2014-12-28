package com.kmaebashi.henacat.util;
import java.io.*;
import java.net.*;

public class SendResponse {
    public static void sendOkResponseHeader(OutputStream output,
					    String contentType,
					    ResponseHeaderGenerator hg)
	throws IOException {
	Util.writeLine(output, "HTTP/1.1 200 OK");
	Util.writeLine(output, "Date: " + Util.getDateStringUtc());
	Util.writeLine(output, "Server: Henacat");
	Util.writeLine(output, "Connection: close");
	Util.writeLine(output, "Content-type: " + contentType);
	hg.generate(output);
	Util.writeLine(output, "");
    }

    public static void sendOkResponse(OutputStream output, InputStream fis,
				      String ext) throws Exception {
	Util.writeLine(output, "HTTP/1.1 200 OK");
	Util.writeLine(output, "Date: " + Util.getDateStringUtc());
	Util.writeLine(output, "Server: Henacat");
	Util.writeLine(output, "Connection: close");
	Util.writeLine(output, "Content-type: "
		       + Util.getContentType(ext));
	Util.writeLine(output, "");

	int ch;
	while ((ch = fis.read()) != -1) {
	    output.write(ch);
	}
    }

    public static void sendMovePermanentlyResponse(OutputStream output,
						   String location)
	throws Exception {
	Util.writeLine(output, "HTTP/1.1 301 Moved Permanently");
	Util.writeLine(output, "Date: " + Util.getDateStringUtc());
	Util.writeLine(output, "Server: Henacat");
	Util.writeLine(output, "Location: " + location);
	Util.writeLine(output, "Connection: close");
	Util.writeLine(output, "");
    }

    public static void sendNotFoundResponse(OutputStream output,
					    String errorDocumentRoot)
	throws Exception {
	Util.writeLine(output, "HTTP/1.1 404 Not Found");
	Util.writeLine(output, "Date: " + Util.getDateStringUtc());
	Util.writeLine(output, "Server: Henacat");
	Util.writeLine(output, "Connection: close");
	Util.writeLine(output, "Content-type: text/html");
	Util.writeLine(output, "");

	try (InputStream fis
	     = new BufferedInputStream(new FileInputStream(errorDocumentRoot
							   + "/404.html"))) {
	    int ch;
	    while ((ch = fis.read()) != -1) {
		output.write(ch);
	    }
	}
    }
}