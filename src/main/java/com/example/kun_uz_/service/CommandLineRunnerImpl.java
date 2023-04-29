package com.example.kun_uz_.service;

import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.GeneralStatus;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.repository.ProfileRepository;
import com.example.kun_uz_.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void run(String... args) throws Exception {
        String email = "adminjon_mazgi@gmail.com";
        Optional<ProfileEntity> profileEntity = profileRepository.findByEmail(email);
        if (profileEntity.isEmpty()) {
            ProfileEntity entity = new ProfileEntity();
            entity.setName("admin");
            entity.setSurname("adminjon");
            entity.setPhone("1234567");
            entity.setEmail(email);
            entity.setRole(ProfileRole.ADMIN);
            entity.setPassword(MD5Util.getMd5Hash("12345"));
            entity.setStatus(GeneralStatus.ACTIVE);
            profileRepository.save(entity);
            System.out.println("Amdin created");
        }
    }
}
