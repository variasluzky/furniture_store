package com.ltp.furniture_store.service;

import com.ltp.furniture_store.entity.Promotion;
import com.ltp.furniture_store.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromotionService {

    @Autowired
    private PromotionRepository repository;

    //Fetch all promotions
    public List<Promotion> getAllPromotions(){
        return repository.findAll();}
    //Fetch a single promotion by ID
    public Promotion getPromotionById(Short id){
        return repository.findById(id).orElse(null);}

    // Create a new promotion
    public Promotion createPromotion(Promotion promotion) {
        return repository.save(promotion);
        }

    // Update an existing color
    public Promotion updatePromotion(Short id, Promotion updatedPromotion) {
        Promotion existingPromotion = repository.findById(id).orElse(null);
            if (existingPromotion != null) {
                existingPromotion.setDescription(updatedPromotion.getDescription());
                return repository.save(existingPromotion);
            }
            return null;
        }

        // Delete a Catalog item  by ID
        public void deletePromotion(Short id) {
            repository.deleteById(id);
        }
    }

