package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<RegionEntity,Integer> , PagingAndSortingRepository<RegionEntity,Integer> {
}
