package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.CommentEntity;
import com.example.kun_uz_.mapper.CommendMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Integer> , PagingAndSortingRepository<CommentEntity,Integer> {

  @Query(value = "select c.id, c.created_Date, c.update_date, p.id, p.name, p.surname from" +
          " comment as c inner join profile as p on p.id = c.profile_id where c.article_id =:id ", nativeQuery = true)
    List<CommendMapper> getAll( @Param("id") Integer id);
@Query(value = "select c.id, c.created_Date, c.update_date, p.id, p.name, p.surname from " +
        " comment as c inner  join  profile as p on p.id = c.profile_id where c.id =:id ",nativeQuery = true)
  List<CommendMapper> getAllREP(Integer id);


/*  @Query(value = "select c.id           as id , " +   // 5 ni queriysi
          "              c.created_Date as createDate , " +
          "              c.update_date  as updateDate , " +
          "              p.id           as profileId , " +
          "              p.name         as profileName, " +
          "              p.surname      as profileSurname , " +
          "              c.article_id   as articleId , " +
          "              a.title        as articleTitle" +
          " from  comment as c inner join " +
          " profile as p on p.id = c.profile_id " +
          " inner join article as a on a.id = c.aricle_id ",nativeQuery = true)*/



/*
  @Transactional
  @Modifying
  @Query("from CategoryEntity inner join ProfileEntity.id =:")
*/

}
