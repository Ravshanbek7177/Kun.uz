package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.ProfileDto.ProfileDTO;
import com.example.kun_uz_.dto.ProfileDto.ProfileFilterDTO;
import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.GeneralStatus;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.repository.ProfileFilterRepository;
import com.example.kun_uz_.repository.ProfileRepository;
import com.example.kun_uz_.util.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileFilterRepository profileFilterRepository;

    public ProfileDTO create(ProfileDTO dto) {  // ,
        // check - homework
        //isValidProfile(dto);

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword())); // MD5 ?
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
       // entity.setPrtId(adminId);
        profileRepository.save(entity); // save profile

        dto.setPassword(null);
        dto.setId(entity.getId());
        return dto;
    }

   /* public void isValidProfile(ProfileDTO dto) {
        // throw ...
        Optional<ProfileEntity> optional = profileRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            throw new AppBadRequestException(" bu foydalanuvchi mavjud");
        }
    }*/

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new AppBadRequestException("Profile not found");
        });
    }

    public ProfileDTO update(Integer id, ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);

        if (optional.isEmpty()) {
            throw new AppBadRequestException("yo'qku");
        }
        ProfileEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword())); // MD5 ?
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        profileRepository.save(entity); // save profile
        dto.setId(entity.getId());
        return dto;
    }

    public ProfileDTO update2(Integer id, ProfileDTO dto) {
          Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("yo'qku");
        }
        ProfileEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword())); // MD5 ?
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        profileRepository.save(entity); // save profile
        dto.setId(entity.getId());
        return dto;
    }

    public Page<ProfileEntity> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1,size,sort);

        Page<ProfileEntity> pageObj = profileRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();

        List<ProfileEntity> courseEntitieList = pageObj.getContent();
        List<ProfileEntity> list = new LinkedList<>();

        for(ProfileEntity entity : courseEntitieList){
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setEmail(entity.getEmail());
            dto.setRole(entity.getRole());
            dto.setPassword(entity.getPassword()); // MD5 ?
            //dto.setVisible(true);
            dto.setStatus(GeneralStatus.ACTIVE);

            list.add(dto);
        }
        Page<ProfileEntity> response = new PageImpl<>(list,pageable,totalCount);

        return response;
    }

    public boolean delete(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("yo'qku");
        }
         profileRepository.delete(optional.get());
        return true;
    }

    public List<ProfileEntity> getFilter(ProfileFilterDTO studentCourseDTO) {
        List<ProfileEntity> entityList = profileFilterRepository.filter(studentCourseDTO);
        return entityList;
    }
}


