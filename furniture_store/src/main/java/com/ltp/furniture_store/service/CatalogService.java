package com.ltp.furniture_store.service;

import com.ltp.furniture_store.entity.Catalog;
import com.ltp.furniture_store.entity.CatalogUpdateDTO;
import com.ltp.furniture_store.entity.Color;
import com.ltp.furniture_store.entity.Promotion;
import com.ltp.furniture_store.repository.CatalogRepository;
import com.ltp.furniture_store.repository.ColorRepository;
import com.ltp.furniture_store.repository.PromotionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository repository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    // Fetch all Catalog items
    public List<Catalog> getAllCatalog() {
        return repository.findAll();
    }


    // Fetch a single item by ID
    public Catalog getCatalogById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Catalog item not found"));
    }

    // Create a new item in Catalog
    public Catalog createCatalog(CatalogUpdateDTO catalogDTO) {
        // Retrieve the Color entity based on colorCode
        Color color = colorRepository.findById(catalogDTO.getColorCode())
                .orElseThrow(() -> new EntityNotFoundException("Color not found"));
        Promotion promotion = promotionRepository.findById(catalogDTO.getPromotionCode())
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found"));

        // Map CatalogUpdateDTO to Catalog entity
        Catalog catalog = new Catalog();
        catalog.setProductName(catalogDTO.getProductName());
        catalog.setDescription(catalogDTO.getDescription());
        catalog.setDescriptionFull(catalogDTO.getDescriptionFull());
        catalog.setPrice(catalogDTO.getPrice());
        catalog.setStock(catalogDTO.getStock());
        catalog.setStatus(catalogDTO.getStatus());
        catalog.setTypeOfItem(catalogDTO.getTypeOfItem());
        catalog.setColor(color);
        catalog.setPromotion(promotion);

        // Save the Catalog item
        return repository.save(catalog);
    }

    // Update an existing item in Catalog
    public Catalog updateCatalog(Long productId, CatalogUpdateDTO updateDTO) {
        Catalog catalog = repository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Catalog item not found"));

        // Update Catalog fields
        if (updateDTO.getProductName() != null) {
            catalog.setProductName(updateDTO.getProductName());
        }
        if (updateDTO.getDescription() != null) {
            catalog.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getDescriptionFull() != null) {
            catalog.setDescriptionFull(updateDTO.getDescriptionFull());
        }
        if (updateDTO.getPrice() != null) {
            catalog.setPrice(updateDTO.getPrice());
        }
        if (updateDTO.getStock() != null) {
            catalog.setStock(updateDTO.getStock());
        }
        if (updateDTO.getStatus() != null) {
            catalog.setStatus(updateDTO.getStatus());
        }
        if (updateDTO.getTypeOfItem() != null) {
            catalog.setTypeOfItem(updateDTO.getTypeOfItem());
        }

        // Update the Color if provided
        if (updateDTO.getColorCode() != null) {
            Color color = colorRepository.findById(updateDTO.getColorCode())
                    .orElseThrow(() -> new EntityNotFoundException("Color not found"));
            catalog.setColor(color);
        }

        // Update the Promotion if provided
        if (updateDTO.getPromotionCode() != null) {
            Promotion promotion = promotionRepository.findById(updateDTO.getPromotionCode())
                    .orElseThrow(() -> new EntityNotFoundException("Promotion not found"));
            catalog.setPromotion(promotion);
        }

        // Save the updated Catalog item
        return repository.save(catalog);
    }

    // Delete a Catalog item by ID
    public void deleteCatalog(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Catalog item not found");
        }
        repository.deleteById(id);
    }

    public Catalog findCatalogById(Long productId) {

        return repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }
}
