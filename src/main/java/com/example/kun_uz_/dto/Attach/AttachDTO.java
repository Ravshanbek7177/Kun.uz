package com.example.kun_uz_.dto.Attach;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {
    @NotBlank(message = "ID required ")
    private String id;
    @NotBlank(message = "originalName required ")
    private String originalName;
    @NotBlank(message = "Path required ")
    private String path;
    @NotNull(message = "Size required ")
    @Size(max = 225,message = "size must be between 10 and 225 characters")
    private Long size;
    private String extension;
    @NotNull(message = "createDate required")
    private LocalDateTime createdData;
    private String url;
}
