package com.example.msachat;

import com.example.msachat.entities.Achat;
import com.example.msachat.repositories.AchatRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class MsAchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAchatApplication.class, args);
    }
    @Bean
    WebClient webClient(){
        return WebClient.builder().build();
    }
}
