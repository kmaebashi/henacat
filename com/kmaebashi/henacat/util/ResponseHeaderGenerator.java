package com.kmaebashi.henacat.util;
import java.io.*;

public interface ResponseHeaderGenerator {
    void generate(OutputStream output) throws IOException;
}