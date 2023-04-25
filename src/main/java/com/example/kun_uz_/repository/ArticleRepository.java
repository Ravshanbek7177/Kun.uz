package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article,String> {

}
