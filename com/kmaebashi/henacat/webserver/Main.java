package com.kmaebashi.henacat.webserver;
import com.kmaebashi.henacat.servletimpl.ServletInfo;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static void main(String[] argv) throws Exception {
	ServletInfo.addServlet("/testbbs/TestBBS",
			       "C:\\maebashi\\henacat\\webapps\\testbbs",
			       "TestBBS");

        try (ServerSocket server = new ServerSocket(8001)) {
	    for (;;) {
		Socket socket = server.accept();

		ServerThread serverThread = new ServerThread(socket);
		Thread thread = new Thread(serverThread);
		thread.start();
	    }
	}
    }
}

