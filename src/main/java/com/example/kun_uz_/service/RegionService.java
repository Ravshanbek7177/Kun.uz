package com.example.kun_uz_.service;


import com.example.kun_uz_.dto.RegionDTO.RegionDTO;
import com.example.kun_uz_.entity.RegionEntity;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.exps.ItemNotFoundException;
import com.example.kun_uz_.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    public RegionDTO create(RegionDTO dto) {

            RegionEntity entity = new RegionEntity();
            entity.setNameUZ(dto.getNameUZ());
            entity.setNameRU(dto.getNameRU());
            entity.setNameEN(dto.getNameEN());
            entity.setCreatedDate(LocalDateTime.now());
            entity.setVisible(true);
            regionRepository.save(entity);

            dto.setId(entity.getId());
            return dto;


    }
    public RegionDTO update(Integer id, RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if(optional.isEmpty()){
            throw new AppBadRequestException("yo'qku");
        }
        RegionEntity entity = optional.get();
        entity.setNameUZ(dto.getNameUZ());
        entity.setNameRU(dto.getNameRU());
        entity.setNameEN(dto.getNameEN());
        entity.setVisible(true);
        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public RegionEntity get(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("ArticleEntity not found: " + id);
        }
        return optional.get();
    }

    public Boolean delete(Integer id) {
        RegionEntity entity = get(id);
        if(entity == null){
            throw new AppBadRequestException("yo'qku");
        }
        entity.setVisible(Boolean.FALSE);
        regionRepository.save(entity);
        return true;
    }
    public Page<RegionDTO> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<RegionEntity> pageObj = regionRepository.findAll(paging);

        Long totalCount = pageObj.getTotalElements();

        List<RegionEntity> entityList = pageObj.getContent();
        List<RegionDTO> dtoList = new LinkedList<>();

        if (!pageObj.equals(null)) {
            for (RegionEntity entity : entityList) {
                RegionDTO dto = new RegionDTO();
                dto.setId(entity.getId());
                dto.setNameUZ(entity.getNameUZ());
                dto.setNameRU(entity.getNameRU());
                dto.setNameEN(entity.getNameEN());
                dtoList.add(dto);
            }
            Page<RegionDTO> response = new PageImpl<RegionDTO>(dtoList, paging, totalCount);
            return response;
        }
        throw new ItemNotFoundException("ArticleType is empty");
    }

}
