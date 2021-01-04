package com.mako.javabackend.config;

public class ServerConfiguration extends HashMapConfiguration {

    public static ServerConfiguration createServerConfig(String[] args) {
        ServerConfiguration config = new ServerConfiguration();
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] hash = arg.split("=", 2);
                config.set(hash[0].toLowerCase(), hash[1]);
            } else {
                config.set(arg, "");
            }
        }
        return config;
    }

    public ServerConfiguration() {
        super();
        this.set("client", System.getProperty("user.dir"));
        this.set("cors", "");
        this.set("port", 3000);
    }

    public String getClientPath() {
        return this.get("client");
    }
    public String getDefaultCORS() {
        return this.get("cors");
    }
    public int getPort() {
        return this.getInt("port");
    }

}
