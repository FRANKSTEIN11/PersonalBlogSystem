package com.example.blogplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class BlogArticlePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogArticlePlatformApplication.class, args);
    }

}
