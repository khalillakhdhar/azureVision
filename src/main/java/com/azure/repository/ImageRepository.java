package com.azure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.azure.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    // This will allow basic CRUD operations on the 'images' table
}
