package org.example;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Server {

    private final Logger logger;
    private io.grpc.Server server;

    public Server(Logger logger) {
        this.logger = logger;
    }

    public void start() throws IOException {
        initServer();
        hookForServerShutdown();
    }

    private void hookForServerShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            logger.info("*** shutting down gRPC server since JVM is shutting down");
            try {
                Server.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            logger.info("*** server shut down");
        }));
    }

    private void initServer() throws IOException {
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new ServiceImpl(logger))
                .build()
                .start();
        logger.info("Server started, listening on " + port);
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}