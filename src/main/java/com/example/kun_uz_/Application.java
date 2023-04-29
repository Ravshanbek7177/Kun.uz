package com.example.kun_uz_;

import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
      //  System.out.println(JwtUtil.encode(7, ProfileRole.PUBLISHER));
    }

}
