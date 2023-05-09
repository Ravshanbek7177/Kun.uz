package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.RegionDTO.RegionDTO;
import com.example.kun_uz_.dto.article.*;
import com.example.kun_uz_.dto.articleTypeDto.ArticleTypeDTO;
import com.example.kun_uz_.dto.categoryDTO.CategoryDTO;
import com.example.kun_uz_.entity.*;
import com.example.kun_uz_.enums.ArticleStatus;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.exps.ItemNotFoundException;
import com.example.kun_uz_.mapper.ArticleFullMapper;
import com.example.kun_uz_.mapper.ArticleShortInfoMapper;
import com.example.kun_uz_.repository.ArticleFilterRepository;
import com.example.kun_uz_.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private final AttachService attachService;
    @Autowired
    private ArticleTypeService articleTypeService;

    @Autowired
    private final RegionService regionService;
    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private ArticleFilterRepository filterRepository;


    public ArticleRequestDTO create(ArticleRequestDTO dto, Integer moderId) {
        // check
        isValidProfile(dto);
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(moderId);
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setTypeId(dto.getTypeId());
        entity.setArticle_type(dto.getArticleType());

        // type
        articleRepository.save(entity);
        dto.setId(entity.getId());
    return dto;
    }

    public void isValidProfile(ArticleRequestDTO dto) {
        if (dto.getAttachId() == null) {
            throw new AppBadRequestException("invalid category");
        }
        if (categoryService.get(dto.getCategoryId()) == null) {
            throw new AppBadRequestException("not found category");
        }

        if (dto.getRegionId() == null) {
            throw new AppBadRequestException("invalid region");
        }

        if (regionService.get(dto.getRegionId()) == null) {
            throw new AppBadRequestException("not found region");
        }

        if (dto.getDescription() == null) {
            throw new AppBadRequestException("invalid desc");
        }
        if (dto.getContent() == null) {
            throw new AppBadRequestException("invalid con");
        }
        if (dto.getTitle() == null) {
            throw new AppBadRequestException("invalid title");

        }
    }

    public ArticleRequestDTO update(String id, ArticleRequestDTO dto) {

        ArticleEntity entity = get(id);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        articleRepository.save(entity);
        return dto;
    }

    public ArticleEntity get(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }

    public Boolean delete(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("yo'qku");
        }
        articleRepository.delete(optional.get());
        return true;
    }

    public boolean update2(String id, ChangeStatusDTO dto) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);

        if (optional.isEmpty()) {
            throw new AppBadRequestException(" yoqku ");
        }
        ArticleEntity article = optional.get();
        if (dto.getStatus().equals(ArticleStatus.NOT_PUBLISHED)) {
            article.setStatus(ArticleStatus.PUBLISHED);
        } else {
            article.setStatus(ArticleStatus.NOT_PUBLISHED);
        }
        articleRepository.save(article);
        return true;
    }

    public Page<ArticleRequestDTO> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ArticleEntity> pageObj = articleRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();

        List<ArticleEntity> courseEntitieList = pageObj.getContent();
        List<ArticleRequestDTO> list = new LinkedList<>();
        for (ArticleEntity entity : courseEntitieList) {
            ArticleRequestDTO dto = new ArticleRequestDTO();
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setContent(entity.getContent());
            dto.setRegionId(entity.getRegionId());
            dto.setCategoryId(entity.getCategoryId());
            dto.setAttachId(entity.getAttachId());

            list.add(dto);
        }
        Page<ArticleRequestDTO> response = new PageImpl<>(list, pageable, totalCount);

        return response;
    }

    /* public List<ArticleShortInfo> getAll5(Integer id) {
         List<ArticleShortInfo> entityList = articleRepository.getTopN(id, ArticleStatus.PUBLISHED.name(), 5);
         List<ArticleShortInfo> dtoList = new LinkedList<>();
         entityList.forEach(entity ->
                 dtoList.add((ArticleShortInfo) toArticleShortInfo(entity)));
         return dtoList;
     }*/
    // 1 chi yo'li
 /*   public List<ArticleShortInfo> getAll5(Integer id){
        List<ArticleEntity> entityList = articleRepository.findTop5ByTypeAndStatusAndVisibleOrderByCreatedDateDesc(id, ArticleStatus.PUBLISHED, true);
        List<ArticleShortInfo> dtoList = new LinkedList<>();
        entityList.forEach(entity ->
                dtoList.add(toArticleShortInfo(entity)));
        return dtoList;

    }*/
    public List<ArticleShortInfo> getAll5(Integer id) {
        List<ArticleShortInfoMapper> entityList = articleRepository.getTopN(id, ArticleStatus.PUBLISHED.name(), 5);
        List<ArticleShortInfo> dtoList = new LinkedList<>();
        entityList.forEach(entity ->
                dtoList.add(toArticleShortInfo(entity)));
        return dtoList;

    }

    public ArticleShortInfo toArticleShortInfo(ArticleShortInfoMapper entity) {
        ArticleShortInfo dto = new ArticleShortInfo();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublished_date());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }


    /* public List<ArticleShortInfo> getAll3(Integer id) {   // JPA kuveriy bo'ldi
         List<ArticleEntity> entityList = articleRepository.findTop3ByType_IdAndStatusAndVisibleOrderByCreatedDateDesc(id,ArticleStatus.NOT_PUBLISHED,true);
         List<ArticleShortInfo> dtoList = new LinkedList<>();
         entityList.forEach(entity ->
                 dtoList.add(toArticleShortInfo(entity)));
         return dtoList;
     }
 */
    public List<ArticleShortInfo> getAll3(String id) {
        List<ArticleShortInfoMapper> entityList = articleRepository.find3ByTypeIdNative(id, ArticleStatus.NOT_PUBLISHED.name(), 3);
        List<ArticleShortInfo> dtoList = new LinkedList<>();
        entityList.forEach(entity ->
                dtoList.add(toArticleShortInfo(entity)));
        return dtoList;
    }

    private ArticleShortInfo toArticleShortInfo(ArticleEntity entity) {
        ArticleShortInfo dto = new ArticleShortInfo();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }

    public List<ArticleShortInfo> pagination4(String id) {
        List<ArticleShortInfoMapper> articleEntities = articleRepository.findAll4(id, 4);
        List<ArticleShortInfo> articleShortInfos = new LinkedList<>();
        articleEntities.forEach(articleShortInfo -> {
            articleShortInfos.add(toArticleShortInfo(articleShortInfo));
        });
        return articleShortInfos;
    }

    public List<ArticleShortInfo> getALL10() {
        List<ArticleShortInfoMapper> articleEntities = articleRepository.getALL10(4);
        List<ArticleShortInfo> infos = new LinkedList<>();
        articleEntities.forEach(articleShortInfo -> {
            infos.add(toArticleShortInfo(articleShortInfo));
        });
        return infos;

    }

    public List<ArticleFullDTO> getAllLang( String lang) {  // 8

        List<ArticleFullMapper> articleEntities = articleRepository.getALL8(lang);
        List<ArticleFullDTO> infos = new LinkedList<>();
        articleEntities.forEach(articleFullMapper -> {
            infos.add(toArticleFullInfo(articleFullMapper));
        });
        return infos;
    }

    private ArticleFullDTO toArticleFullInfo(ArticleFullMapper entity) {
        ArticleFullDTO dto = new ArticleFullDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        dto.setCategory(new CategoryDTO(entity.getCategoryId(),entity.getCategoryName()));
        dto.setRegion(new RegionDTO(entity.getRegionId(),entity.getRegionName()));
        dto.setArticleType(new ArticleTypeDTO(entity.getCategoryName(),entity.getTypeName()));
         return  dto;
    }

/*    public ArticleFullDTO toFullDTO(ArticleEntity entity ) {
        ArticleFullDTO dto = new ArticleFullDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setViewCount(entity.getViewCount());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        dto.setCategory(categoryService.get(new CategoryDTO(entity.getCategoryId(),entity.getCategory())));
        dto.setRegion(regionService.getByIdAndLang(entity.getCategoryId(), langEnum));
        dto.setArticleType(articleTypeService.getByIdAndLang(entity.getCategoryId(), langEnum));
        // tag_list
        return dto;
    }*/

    public List<ArticleShortInfo> getALLId(String id) {
        List<ArticleShortInfoMapper> mappers = articleRepository.getAllId(id,4);
        List<ArticleShortInfo> infos = new LinkedList<>();
        mappers.forEach(articleShortInfo -> {
            infos.add(toArticleShortInfo(articleShortInfo));
        });
        return infos;
    }    //

    public Page<ArticleShortInfo> getALLPagination(Integer id, Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC , "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);

        Page<ArticleEntity> pageObj = articleRepository.findAllByPagination(id, paging);
        long content =  pageObj.getTotalElements();
        List<ArticleEntity> entityList = pageObj.getContent();
        List<ArticleShortInfo> list = new LinkedList<>();
        entityList.forEach(entity -> {
            list.add(toArticleShortInfo(entity));
        });
        return new PageImpl<>(list,paging,content);
    }

    public Boolean getALLCount(String id) {
       boolean infos = articleRepository.getALLCount(id);
        return infos;
    }

    public boolean getALLShare(String id) {
       int i = articleRepository.getALLShare(id);
        return i>0;
    }
    public List<ArticleEntity> getFilter(ArticleFilterDTO dto , Integer page, Integer size) {
        List<ArticleEntity> mappers = filterRepository.getFilter(dto,page , size);
        return mappers;
    }


 /*   public List<ArticleShortInfo> getALLTagName(String tagName) {
        List<ArticleEntity> mappers = articleRepository.getALLTagName(tagName,4);
        List<ArticleShortInfo> infos = new LinkedList<>();
        mappers.forEach(articleEntity -> toArticleShortInfo((ArticleEntity) infos) );
       return null;
    }*/

/*        public List<ArticleShortInfo> getLast8WithoutList(List<String> list1) {
            List<ArticleEntity> entityList = articleRepository.getAllArticle(ArticleStatus.NOT_PUBLISHED);
            List<ArticleEntity> list = new LinkedList<>();
            List<ArticleShortInfo> infoDTOS = new LinkedList<>();
            for (String str : list1) {
                for (ArticleEntity articleEntity : entityList) {
                    if (!str.equals(articleEntity.getId())) {
                        list.add(articleEntity);
                    } else {
                        break;
                    }
                    if (list.size() == 8) break;
                }
                if(list.size()==8)break;
            }
            list.forEach(entity -> {
                infoDTOS.add(toArticleShortInfo(entity));
            });
            return infoDTOS;
        }*/


}