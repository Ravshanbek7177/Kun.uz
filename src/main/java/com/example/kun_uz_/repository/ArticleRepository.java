package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.ArticleEntity;
import com.example.kun_uz_.mapper.ArticleShortInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
    Page<ArticleEntity> findAll(Pageable pageable);
   /*@Query(value = "SELECT a.id , a.title, a.description , a.attach , a.published_date" +
           " FROM article as a where id =:id and status=:status and limit :limit",nativeQuery = true)
   List<ArticleShortInfoMapper> getTopN(@Param("id") Integer id,@Param("status") String name,@Param("limit") Integer limit);
*/

    // List<ArticleEntity> findTop5ByType_IdAndStatusAndVisibleOrderByCreatedDateDesc(Integer type_id, ArticleStatus status, Boolean visible);

    //   5 ni nativ kuveriysi
    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id,a.published_date " +
            " FROM article AS a  where  a.type_id =:t_id and status =:status order by created_date desc Limit :limit", nativeQuery = true)
    List<ArticleShortInfoMapper> getTopN(@Param("t_id") Integer t_id, @Param("status") String status, @Param("limit") Integer limit);

    // List<ArticleEntity> findTop3Tyre_IdAndStatusAndVisibleOrderByCreatedDateDesc(Integer id, ArticleStatus status, Boolean visible);
    /// 6 ni JPA da yozlishi
    // List<ArticleEntity> findTop3ByType_IdAndStatusAndVisibleOrderByCreatedDateDesc(Integer type_id, ArticleStatus status, Boolean visible);

    @Query(value = "SELECT a.id , a.title,a.description,a.attach_id,a.published_date" +   // 8
            " FROM article AS a where  a.id =:id and status =:status order by created_date desc Limit :limit", nativeQuery = true)
    List<ArticleShortInfoMapper> find3ByTypeIdNative(@Param("id") String id, @Param("status") String status, @Param("limit") Integer limit);

    @Query(value = "SELECT a.id , a.title,a.description,a.attach_id,a.published_date" +   // 9
            " FROM article AS a  inner join article_type as t on t.id = a.type_id where  a.id != :id order by a.created_date desc Limit :limit", nativeQuery = true)
    List<ArticleShortInfoMapper> findAll4(@Param("id") String id, @Param("limit") Integer limit);


    @Query(value = "SELECT a.id , a.title,a.description,a.attach_id,a.published_date" +  // 10
            " FROM article AS a order by view_count desc Limit :limit", nativeQuery = true)
    List<ArticleShortInfoMapper> getALL4(@Param("limit") Integer limit);

/*
    @Query(value = "SELECT a.id , a.title,a.description,a.attach_id,a.published_date" +    //11
            " FROM article AS a  where a.TagName =: TagName  order by created_date desc Limit :limit", nativeQuery = true)
    List<ArticleEntity> getALLTagName(@Param("TagName") String TagName, @Param("limit") Integer limit);

*/




/*
    @Query(value = "select  a.id , a.title,a.description,a.attach_id,a.published_date " +
            "FROM article AS a where  a.id =:id and status =:status order by created_date desc" ,nativeQuery = true)
    List<ArticleShortInfoMapper> findTop8ByTypeNative(@Param("id") String id, @Param("name") String name);
*/
/*
@Query(value = "select  a.id , a.title,a.description,a.attach_id,a.published_date " +
        "FROM article AS a where status =:status order by created_date desc" ,nativeQuery = true)
    List<ArticleEntity> getAllArticle( @Param("name") ArticleStatus notPublished);
*/

    // 8
/*
@Query(value = " from ArticleEntity where id =:id and status =:status ")
    List<ArticleEntity> findByLang(@Param("id") String id, @Param("status") String status);

*/

}
