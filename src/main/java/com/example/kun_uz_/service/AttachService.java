package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.Attach.AttachDTO;
import com.example.kun_uz_.entity.AttachEntity;
import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.exps.ItemNotFoundException;
import com.example.kun_uz_.repository.AttachRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class AttachService {
    @Value("${server.host}")
    private String serverHost;
    @Autowired
    private AttachRepository attachRepository;

    public String saveToSystem(MultipartFile file) {
        try {
            File folder = new File("attaches");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get("attaches/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String saveToSystem2(MultipartFile file) {

        try {
            File folder = new File("attaches");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            String fileName = UUID.randomUUID().toString();
            String extension = getExtension(file.getOriginalFilename());
            Path path = Paths.get("attaches/" + fileName, extension);
            Files.write(path, bytes);
            return fileName + "." + extension;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getExtension(String Filename) {
        int lastIndex = Filename.lastIndexOf(".");
        return Filename.substring(lastIndex + 1);
    }

    public byte[] loadImage(String fileName) {
        byte[] imageInByte;
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File("attaches" + fileName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public byte[] open_general(String attachName) {
        byte[] data;
        try {
            Path file = Paths.get("attaches/" + attachName);   // Path ->  yo'lni ko'rsatish
            data = Files.readAllBytes(file); // readAllBytes -- bu o'qib olayapti
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public Resource download(String fileName) {
        try {
            int lastIndex = fileName.lastIndexOf(".");
            String id = fileName.substring(0, lastIndex);
            AttachEntity attachEntity = get(id);

            Path file = Paths.get("attaches/" + attachEntity.getPath() + "/" + fileName);
            org.springframework.core.io.Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

    }

    public AttachEntity get(String id) {
        return attachRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Attach not ound");
        });
    }

    public String saveToSystem3(MultipartFile file) {
        try {
            String pathFolder = getYmDString(); // 2022/04/23
            File folder = new File("attaches/" + pathFolder);  // attaches/2023/04/26
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            String extension = getExtension(file.getOriginalFilename());

            AttachEntity attachEntity = new AttachEntity();
            attachEntity.setId(UUID.randomUUID().toString());
            attachEntity.setCreatedData(LocalDateTime.now());
            attachEntity.setExtension(extension);
            attachEntity.setSize(file.getSize());
            attachEntity.setPath(pathFolder);
            attachEntity.setOriginalName(file.getOriginalFilename());
            attachRepository.save(attachEntity);

            Path path = Paths.get("attaches/" + pathFolder + "/" + attachEntity.getId() + "." + extension);
            // attaches/2023/04/26/uuid().jpg
            Files.write(path, bytes);
            return attachEntity.getId() + "." + extension;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        return year + "/" + month + "/" + day; // 2022/04/23
    }


    public Page<AttachEntity> getAll(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createdData");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<AttachEntity> pageObj = attachRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();
        List<AttachEntity> courseEntitieList = pageObj.getContent();
        List<AttachEntity> list = new LinkedList<>();

        for (AttachEntity entity : courseEntitieList) {

            AttachEntity attachEntity = new AttachEntity();
            attachEntity.setId(UUID.randomUUID().toString());
            attachEntity.setCreatedData(LocalDateTime.now());
            attachEntity.setExtension(entity.getExtension());
            attachEntity.setSize(entity.getSize());
            attachEntity.setPath(entity.getPath());
            attachEntity.setOriginalName(entity.getOriginalName());

            list.add(attachEntity);
        }
        Page<AttachEntity> response = new PageImpl<>(list, pageable, totalCount);

        return response;
    }

    public boolean delete(String fileName) {
        try {
            int lastIndex = fileName.lastIndexOf(".");
            String id = fileName.substring(0, lastIndex);
            AttachEntity attachEntity = get(id);
            Path file = Paths.get("attaches/" + attachEntity.getPath() + "/" + fileName);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String toOpenUrl(String id) {
        return "http://localhost:8080/api/v1/attach/open/" + id;
    }

    public AttachDTO getAttachLink(String attachId) {
        AttachDTO dto = new AttachDTO();
        dto.setId(attachId);
        dto.setUrl(serverHost + "/api/v1/attach/open/" + attachId);
        return dto;
    }
/*
    public AttachDTO saveToSystem3(MultipartFile file) {
        try {
            String pathFolder = getYmDString(); // 2022/04/23
            File folder = new File("attaches/" + pathFolder);  // attaches/2023/04/26
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            String extension = getExtension(file.getOriginalFilename());

            AttachEntity attachEntity = new AttachEntity();
            attachEntity.setId(UUID.randomUUID().toString());
            attachEntity.setCreatedData(LocalDateTime.now());
            attachEntity.setExtension(extension);
            attachEntity.setSize(file.getSize());
            attachEntity.setPath(pathFolder);
            attachEntity.setOriginalName(file.getOriginalFilename());
            attachRepository.save(attachEntity);

            Path path = Paths.get("attaches/" + pathFolder + "/" + attachEntity.getId() + "." + extension);
            // attaches/2023/04/26/uuid().jpg
            Files.write(path, bytes);

            AttachDTO dto = new AttachDTO();
            dto.setId(attachEntity.getId());
            dto.setOriginalName(file.getOriginalFilename());
            dto.setUrl(serverHost + "/api/v1/attach/open/" + attachEntity.getId() + "." + extension);

            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    */

}
