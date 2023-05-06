package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.Comment.*;
import com.example.kun_uz_.entity.ArticleEntity;
import com.example.kun_uz_.entity.CommentEntity;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.exps.ItemNotFoundException;
import com.example.kun_uz_.mapper.CommendMapper;
import com.example.kun_uz_.repository.CommentFilterRepository;
import com.example.kun_uz_.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentFilterRepository commentFilterRepository;

    public CommentDTO create(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setArticleId(dto.getArticleId());
        entity.setReplyId(dto.getReplyId());
        entity.setProfileId(dto.getProfileId());
        commentRepository.save(entity);
        return dto;
    }

    public void isValidProfile(CommentDTO dto) {
        if (dto.getId() == null) {
            throw new AppBadRequestException("invalid category");
        }
    }

    public Object update(Integer id, CommentDTO dto) {
        CommentEntity entity = get(id);
        entity.setContent(dto.getContent());
        entity.setArticleId(dto.getArticleId());
        entity.setReplyId(dto.getReplyId());
        entity.setProfileId(dto.getProfileId());
        entity.setUpdateDate(LocalDateTime.now());
        commentRepository.save(entity);
        return null;
    }

    private CommentEntity get(Integer id) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }

    public CommentDTO delete(Integer id) {
        CommentEntity entity = get(id);
        commentRepository.delete(entity);
        return null;
    }

    public List<CommentResponsDTO> getAll(Integer id) {
        List<CommendMapper> mappers = commentRepository.getAll(id);
        List<CommentResponsDTO> list = new LinkedList<>();
        mappers.forEach(commendMapper -> {
            list.add(toComment(commendMapper));
        });
        return list;
    }

    private CommentResponsDTO toComment(CommendMapper entity) {
        CommentResponsDTO dto = new CommentResponsDTO();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setProfileResponseDTO(new ProfileResponseDTO(entity.getProfileId(), entity.getProfileName(), entity.getProfileSurname()));

        return dto;
    }


    public Page<CommentDTO> getPagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<CommentEntity> pageOBJ = commentRepository.findAll(pageable);  //
        long content = pageOBJ.getTotalElements();
        List<CommentEntity> entityList = pageOBJ.getContent();
        List<CommentDTO> list = new LinkedList<>();
        entityList.forEach(commentEntity -> {
           list.add(toCommentPagination(commentEntity));
        });
     Page<CommentDTO> dtos = new PageImpl<>(list,pageable,content);
        return dtos;
    }

    private CommentDTO toCommentPagination(CommentEntity entity) {
        CommentPagination dto = new CommentPagination();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreatedDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setProfileResponseDTO(new ProfileResponseDTO(entity.getProfile().getId(), entity.getProfile().getName(), entity.getProfile().getSurname()));
        dto.setArticleResponseDTO(new ArticleResponseDTO(entity.getArticle().getId(),entity.getArticle().getTitle()));
        dto.setReplyId(entity.getReplyId());
          return null;
    }

    public List<CommentEntity> getFilter(CommentFilterDTO dto, Integer page, Integer size) {
        List<CommentEntity> commentEntities = commentFilterRepository.getFilter(dto,page,size);
        return commentEntities;
    }

    public List<CommentResponsDTO> getAllREP(Integer id) {
        List<CommendMapper> entityList = commentRepository.getAllREP(id);
        List<CommentResponsDTO> list = new LinkedList<>();
        entityList.forEach(commendMapper -> {
            list.add(toComment(commendMapper));
        });
        return null;
    }
}
