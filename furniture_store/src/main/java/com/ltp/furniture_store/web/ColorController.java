package com.ltp.furniture_store.web;

import com.ltp.furniture_store.entity.Color;
import com.ltp.furniture_store.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/colors")
@CrossOrigin(origins = "http://localhost:4200")

public class ColorController {

     @Autowired
     private ColorService service;

     // Fetch all colors
     @GetMapping
     public List<Color> getAllColors() {
           return service.getAllColors();
     }

     // Fetch a single color by ID
     @GetMapping("/{id}")
     public Color getColorById(@PathVariable Short id) {
         return service.getColorById(id);
     }

     //Create a new color
    @PostMapping
    public Color createColor(@RequestBody Color color) {
         return service.createColor(color);
     }
    // Update an existing color
    @PutMapping("/{id}")
    public Color updateColor(@PathVariable Short id, @RequestBody Color updatedColor) {
         return service.updateColor(id, updatedColor);
        }
    // Delete a color by ID
    @DeleteMapping("/{id}")
    public void deleteColor(@PathVariable Short id) {
         service.deleteColor(id);
    }
}
