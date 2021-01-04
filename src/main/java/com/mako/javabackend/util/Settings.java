package com.mako.javabackend.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;

public class Settings {

    public int PORT = 3000;
    public String CLIENT = "/";
    public String CORS = "";

    public void setField(Field field, Type type, String value) throws IllegalAccessException {
        switch (type.getTypeName()) {
            case "java.lang.String":
                field.set(this, value);
                break;
            case "int":
                field.setInt(this, Integer.parseInt(value));
                break;
            case "boolean":
                field.setBoolean(this, Boolean.parseBoolean(value));
                break;
            case "long":
                field.setLong(this, Long.parseLong(value));
                break;
            case "byte":
                field.setByte(this, Byte.parseByte(value));
                break;
            case "char":
                field.setChar(this, value.charAt(0));
                break;
            case "short":
                field.setShort(this, Short.parseShort(value));
                break;
            case "float":
                field.setFloat(this, Float.parseFloat(value));
                break;
            case "double":
                field.setDouble(this, Double.parseDouble(value));
                break;
        }
    }

    public static Settings build(Map<String, String> settingsMap) {
        Settings settings = new Settings();
        for (Field field : Settings.class.getDeclaredFields()) {
            String name = field.getName().toLowerCase();
            String value;
            if (settingsMap.containsKey(name) && (value = settingsMap.get(name)) != null) {
                try {
                    settings.setField(field, field.getGenericType(), value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return settings;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
