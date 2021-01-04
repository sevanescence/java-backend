package com.mako.javabackend.config;

import java.util.HashMap;

public class HashMapConfiguration {

    private final HashMap<String, String> hashMap;

    public HashMapConfiguration() {
        this.hashMap = new HashMap<>();
    }

    public void set(String key, Object val) {
        hashMap.put(key, val.toString());
    }

    public String get(String key) {
        return hashMap.get(key);
    }

    public int getInt(String key) {
        try {
            return Integer.parseInt(hashMap.get(key));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
