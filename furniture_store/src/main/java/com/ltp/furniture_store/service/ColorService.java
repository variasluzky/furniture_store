package com.ltp.furniture_store.service;

import com.ltp.furniture_store.entity.Color;
import com.ltp.furniture_store.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {

    @Autowired
    private ColorRepository repository;


    // Fetch all colors
    public List<Color> getAllColors() {
        return repository.findAll();
    }

    // Fetch a single color  by ID
    public Color getColorById(Short id) {
        return repository.findById(id).orElse(null);
    }

    // Create a new color
    public Color createColor(Color color) {
        return repository.save(color);
    }

    // Update an existing color
    public Color updateColor(Short id, Color updatedColor) {
        Color existingColor = repository.findById(id).orElse(null);
        if (existingColor != null) {
            existingColor.setColorDescription(updatedColor.getColorDescription());
            return repository.save(existingColor);
        }
        return null;
    }

    // Delete a Catalog item  by ID
    public void deleteColor(Short id) {
        repository.deleteById(id);
    }
}



