package org.example;

import java.util.logging.Logger;

public class Starter {

    private static final Logger logger = Logger.getLogger(Server.class.getName());
    public static void main(String[] args) {
        final Server server = new Server(logger);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (Exception e){

        }
    }

}
