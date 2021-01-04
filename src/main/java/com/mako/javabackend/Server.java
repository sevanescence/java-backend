package com.mako.javabackend;

import com.mako.javabackend.config.ServerConfiguration;
import com.mako.javabackend.util.StreamUtils;
import com.sun.net.httpserver.HttpServer;

import javax.print.DocFlavor;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    private static ServerConfiguration settings;

    public static void main(String[] args) throws IOException {
        settings = ServerConfiguration.createServerConfig(args);
        HttpServer server = HttpServer.create(new InetSocketAddress(settings.getPort()), 0);

        server.createContext("/", exchange -> {
            String clientPath = settings.getClientPath() + exchange.getRequestURI().toString();
            boolean pathHasHome = StreamUtils.pathHasHome(clientPath);
            if (clientPath.endsWith("/") && !pathHasHome) {
                // TODO send 404
                exchange.sendResponseHeaders(404, 0);
                return;
            }

            byte[] osBytes = StreamUtils.writeFileToBytes(clientPath);

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", settings.getDefaultCORS());
            exchange.getResponseHeaders().add("Content-Type", StreamUtils.getContentTypeOfFile(clientPath));
            exchange.sendResponseHeaders(200, osBytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(osBytes);
            os.close();
        });

        server.start();
        System.out.println("Listening for requests on port " + settings.getPort());
    }

}
