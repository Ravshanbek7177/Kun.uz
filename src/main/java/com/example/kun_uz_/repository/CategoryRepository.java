package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {


    Page<CategoryEntity> findAll(Pageable pageable);
}
