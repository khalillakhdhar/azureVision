package com.azure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Image {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String description; // This will store the description from Azure Computer Vision
    private String imageUrl; // This will store the URL of the image

    // Standard getters and setters
}
