package com.example.kun_uz_.dto.RegionDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDTO {
    @NotNull(message = "ID  required")
    private Integer Id;
    @NotNull(message = "NameUZ  required")
    private String nameUZ;
    @NotNull(message = "NameRU  required")
    private String nameRU;
    @NotNull(message = "NameEN  required")
    private String nameEN;
    private String key;
}
