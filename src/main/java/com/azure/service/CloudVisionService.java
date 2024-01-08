package com.azure.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudVisionService {
    String getImageDescription(MultipartFile file);
}
