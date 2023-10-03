package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"vn.rananu.shared", "com.cg"})
public class PotRiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PotRiceApplication.class, args);
    }

}
