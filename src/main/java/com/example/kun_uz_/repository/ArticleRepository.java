package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.ArticleEntity;
import com.example.kun_uz_.enums.LangEnum;
import com.example.kun_uz_.mapper.ArticleFullMapper;
import com.example.kun_uz_.mapper.ArticleShortInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<ArticleShortInfoMapper> getALL10(@Param("limit") Integer limit);
    @Query(value = "SELECT a.id , a.title,a.description,a.attach_id,a.published_date" +   // 9
            " FROM article AS a  inner join article_type as t on t.id = a.type_id where  a.id != :id order by a.created_date desc Limit :limit", nativeQuery = true)
    List<ArticleShortInfoMapper> getAllId(@Param("id") String id, @Param("limit") Integer limit);

    @Query(value = "select a.id         as articleId, " +
            "       a.attach_id         as attachId, " +
            "       a.category_id       as categoryId, " +
            "       a.type_id           as typeId, " +
            "       a.content           as content, " +
            "       a.title             as title, " +
            "       a.description       as description, " +
            "       a.created_date      as createdDate, " +
            "       a.moderator_id      as moderatorId, " +
            "       a.region_id         as regionId, " +
            "       (case :lang when 'uz' then r.name_uz when 'en' then r.name_eng else r.name_ru end) as regionName, " +
            "       a.category_id                                                                     as categoryId, " +
            "       (case :lang when 'uz' then c.name_uz when 'en' then c.name_eng else c.name_ru end) as categoryName, " +
            "       a.type_id                                                                          as typeId,  " +
            "       (case :lang when 'uz' then c.name_uz when 'en' then c.name_eng else c.name_ru end) as typeName " +
            "       from article as a " +
            "       inner join region as r on r.id = a.region_id " +
            "       inner join category as c on c.id = a.category_id ", nativeQuery = true)
    List<ArticleFullMapper> getALL8(@Param("lang") String lang);


    @Query(value = " select a " +
            " from ArticleEntity AS a where a.categoryId = :id ")
    Page<ArticleEntity> findAllByPagination( @Param("id") Integer id, Pageable paging);

    @Query(value = "update article as a set  " +
            "a.view_count = :view_count + 1  where a.id =:id" ,nativeQuery = true)
    Boolean getALLCount(@Param("id") String id);

    @Query(value = "update article as a set a.shared_count= :shared_count +1 where a.id =:id",nativeQuery = true)   // Increase Share View Count by Article Id
    int getALLShare(@Param("id") String id);





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
