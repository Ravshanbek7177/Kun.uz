package com.example.kun_uz_.repository;


import com.example.kun_uz_.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> , PagingAndSortingRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email, String password, boolean visible);

    Optional<ProfileEntity> findByEmailAndPasswordAndPhone(String email, String md5Hash, String phone);

}
