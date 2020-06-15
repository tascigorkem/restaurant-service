package com.tascigorkem.zipkinapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinAppApplication.class, args);
    }

}
