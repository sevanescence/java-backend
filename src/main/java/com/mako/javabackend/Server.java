package com.mako.javabackend;

import com.mako.javabackend.util.Settings;
import com.mako.javabackend.util.StreamUtils;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class Server {

    public static HashMap<String, String> settingsMap = new HashMap<>();
    public static Settings settings = new Settings();

    private static HashMap<String, String> parseStartupArgs(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] hash = arg.split("=", 2);
                map.putIfAbsent(hash[0].toLowerCase(), hash[1]);
            } else {
                map.put(arg, null);
            }
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        settingsMap = parseStartupArgs(args);
        settings = Settings.build(settingsMap);
        HttpServer server = HttpServer.create(new InetSocketAddress(settings.PORT), 0);

        server.createContext("/", exchange -> {
            String clientPath = settings.CLIENT + exchange.getRequestURI().toString();
            boolean pathHasHome = StreamUtils.pathHasHome(clientPath);
            if (clientPath.endsWith("/") && !pathHasHome) {
                // TODO send 404
                exchange.sendResponseHeaders(404, 0);
                return;
            }

            BufferedReader reader = StreamUtils.createBufferedReader(clientPath);
            assert reader != null;
            StringBuilder res = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                res.append(line).append("\n");
            }

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", StreamUtils.getContentTypeOfFile(clientPath));
            exchange.sendResponseHeaders(200, res.length());

            OutputStream os = exchange.getResponseBody();
            os.write(res.toString().getBytes());
            os.close();
        });

        server.start();
        System.out.println("Listening for requests on port " + settings.PORT);
    }

}
