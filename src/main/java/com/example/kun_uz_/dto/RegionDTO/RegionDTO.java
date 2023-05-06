package com.example.kun_uz_.dto.RegionDTO;

import com.example.kun_uz_.entity.RegionEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDTO  {
    public RegionDTO(Integer id, String name) {
        this.Id = id;
        this.name = name;
    }
    @NotNull(message = "ID  required")
    private Integer Id;
    @NotNull(message = "NameUZ  required")
    private String nameUZ;
    @NotNull(message = "NameRU  required")
    private String nameRU;
    @NotNull(message = "NameEN  required")
    private String nameEN;
    private String key;
    private String name;


    public RegionDTO() {

    }
}
