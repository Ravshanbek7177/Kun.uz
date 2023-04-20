package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.ArticleTureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleTypeRepository extends CrudRepository<ArticleTureEntity,Integer> {

    Page<ArticleTureEntity> findAll(Pageable pageable);
}
