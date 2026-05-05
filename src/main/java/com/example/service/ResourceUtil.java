package com.example.service;

import java.io.File;
import java.net.URL;

public final class ResourceUtil {
    private ResourceUtil() {
    }

    public static String resolvePath(String fileName) {
        File direct = new File(fileName);
        if (direct.exists()) {
            return direct.getAbsolutePath();
        }

        File projectRelative = new File("src/main/resouces/" + fileName);
        if (projectRelative.exists()) {
            return projectRelative.getAbsolutePath();
        }

        URL resource = ResourceUtil.class.getClassLoader().getResource(fileName);
        if (resource != null && "file".equalsIgnoreCase(resource.getProtocol())) {
            return new File(resource.getPath()).getAbsolutePath();
        }

        return direct.getAbsolutePath();
    }
}
