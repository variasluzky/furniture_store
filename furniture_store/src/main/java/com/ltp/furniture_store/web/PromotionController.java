package com.ltp.furniture_store.web;

import com.ltp.furniture_store.entity.Promotion;
import com.ltp.furniture_store.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
@CrossOrigin(origins = "http://localhost:4200")
public class PromotionController {

    @Autowired
    private PromotionService service;

    //Fetch all Promotions
    @GetMapping
    public List<Promotion> getAllPromotions(){
        return service.getAllPromotions();
    }

    //Fetch a single promotion by ID
    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable Short id){
        return service.getPromotionById(id);
    }

    //create a new promotion
    @PostMapping
    public Promotion createPromotion(@RequestBody Promotion promotion){
        return service.createPromotion(promotion);
    }

    //Update an existing promotion
    @PutMapping("/{id}")
    public Promotion updatePromotion(@PathVariable Short id, @RequestBody Promotion updatedPromotion){
        return service.updatePromotion(id,updatedPromotion);
    }

    //Delete promotion by ID
    public void deletePromotion(@PathVariable Short id){
        service.deletePromotion(id);
    }
}
