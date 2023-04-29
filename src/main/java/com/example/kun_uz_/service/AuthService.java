package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.authDTO.RegistrationDTO;
import com.example.kun_uz_.dto.authDTO.AuthDTO;
import com.example.kun_uz_.dto.authDTO.AuthResponseDTO;
import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.GeneralStatus;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.exps.ItemNotFoundException;
import com.example.kun_uz_.repository.ProfileRepository;
import com.example.kun_uz_.util.JwtUtil;
import com.example.kun_uz_.util.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public AuthResponseDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPasswordAndVisible(
                dto.getEmail(),
                MD5Util.getMd5Hash(dto.getPassword()),
                true);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Email or password incorrect");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("Wrong status");
        }
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        responseDTO.setJwt(JwtUtil.encode(entity.getId(), entity.getRole()));
        return responseDTO;
    }

    public Object registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPasswordAndPhone(dto.getEmail(),MD5Util.getMd5Hash(dto.getPassword()),dto.getPhone());
        if(optional.isPresent()){
            throw new ItemNotFoundException("Email or phone or password incorrect");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setPhone(dto.getPhone());
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setRole(ProfileRole.USER);
        profileRepository.save(entity);

        return dto;
    }
}
