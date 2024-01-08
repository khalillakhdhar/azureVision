package com.azure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.azure.model.Image;
import com.azure.service.CloudVisionService;
import com.azure.service.StorageService;

@Controller
public class ImageUploadController {

    private final StorageService storageService;
    private final CloudVisionService cloudVisionService;

    @Autowired
    public ImageUploadController(StorageService storageService, CloudVisionService cloudVisionService) {
        this.storageService = storageService;
        this.cloudVisionService = cloudVisionService;
    }

    @RequestMapping("/")
    public String index() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
        String imageUrl = storageService.store(file);
        String description = cloudVisionService.getImageDescription(file);

        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setDescription(description);
        image.setImageUrl(imageUrl);

        // Save the image details to the database
        // imageRepository.save(image);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");
        modelAndView.addObject("image", image);

        return modelAndView;
    }
}