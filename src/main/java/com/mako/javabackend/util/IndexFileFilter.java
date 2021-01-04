package com.mako.javabackend.util;

import java.io.File;
import java.io.FileFilter;

public class IndexFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory())
            return false;
        String name = pathname.getName();
        if (name.startsWith("index") || name.startsWith("home")) {
            return name.endsWith("html") || name.endsWith("htm");
        }
        return false;
    }

}
