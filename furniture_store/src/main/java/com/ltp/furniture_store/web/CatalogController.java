package com.ltp.furniture_store.web;


import com.ltp.furniture_store.entity.Catalog;
import com.ltp.furniture_store.entity.CatalogUpdateDTO;
import com.ltp.furniture_store.entity.ItemType;
import com.ltp.furniture_store.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
@CrossOrigin(origins = "http://localhost:4200")

public class CatalogController {

    @Autowired
    private CatalogService service;


    @GetMapping("/items/{id}")
    public Catalog getItem(@PathVariable Long id) {
        System.out.println("Received ID: " + id);
        return service.getCatalogById(id);
    }

    // Fetch all items
    @GetMapping
    public List<Catalog> getAllCatalog() {
        return service.getAllCatalog();
    }



    // Fetch a single item by ID
    @GetMapping("/{id}")
    public Catalog getCatalogById(@PathVariable long id) {
        return service.getCatalogById(id);

    }

    //Create a new color
    @PostMapping
    public Catalog createColor(@RequestBody CatalogUpdateDTO catalog) {
        return service.createCatalog(catalog);
    }
    // Update an existing color
    @PutMapping("/{id}")
    public Catalog updateCatalog(@PathVariable long id, @RequestBody CatalogUpdateDTO updatedCatalog) {
        return service.updateCatalog(id, updatedCatalog);
    }
    // Delete a color by ID
    @DeleteMapping("/{id}")
    public void deleteCatalog(@PathVariable long id) {
        service.deleteCatalog(id);
    }

    @GetMapping("/item-types")
    public ItemType[] getItemTypes() {
        return ItemType.values();
    }



}






