package com.example.responsereadyapp;

import com.example.responsereadyapp.config.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

@Import(MailConfig.class)
@SpringBootApplication
public class ResponseReadyApp {

    public static void main(String[] args) {
        SpringApplication.run(ResponseReadyApp.class, args);
    }
}
