package com.mako.javabackend.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class StreamUtils {

    public static BufferedReader createBufferedReader(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles(new IndexFileFilter());
            if (files == null || files.length < 1)
                return null; // already implied, but whatever
            file = files[0];
        }
        return new BufferedReader(new FileReader(file));
    }

    public static boolean pathHasHome(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles(new IndexFileFilter());
            return files != null && files.length > 0;
        }
        return false;
    }

    public static String getContentTypeOfFile(String path) throws IOException {
        String contentType = Files.probeContentType(Paths.get(path));
        if (contentType == null)
            return "text/html";
        else return contentType;
    }

}
