package com.ltp.furniture_store.web;

import com.ltp.furniture_store.entity.*;
import com.ltp.furniture_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private RegisteredCustomerService registeredCustomerService;

    @Autowired
    private CatalogService catalogService;

    // Add item to cart
    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@PathVariable Integer customerId, @RequestParam Long productId, @RequestParam int quantity) {
        RegisteredCustomer customer = registeredCustomerService.findUserById(customerId);
        Catalog catalog = catalogService.findCatalogById(productId);
        ShoppingCart cart = shoppingCartService.createOrUpdateCart(customer);
        ShoppingCartItem item = shoppingCartService.addItemToCart(cart, catalog, quantity);
        return ResponseEntity.ok(item);
    }

    // Remove item from cart
    @DeleteMapping("/{customerId}/remove")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Integer customerId, @RequestParam Long productId) {
        RegisteredCustomer customer = registeredCustomerService.findUserById(customerId);
        Catalog catalog = catalogService.findCatalogById(productId);
        ShoppingCart cart = shoppingCartService.createOrUpdateCart(customer);
        shoppingCartService.removeItemFromCart(cart, catalog);
        return ResponseEntity.ok("Item removed from cart");
    }

    // Complete the cart
    @PostMapping("/{customerId}/complete")
    public ResponseEntity<?> completeCart(@PathVariable Integer customerId) {
        RegisteredCustomer customer = registeredCustomerService.findUserById(customerId);
        ShoppingCart cart = shoppingCartService.createOrUpdateCart(customer);
        shoppingCartService.completeCart(cart);
        return ResponseEntity.ok("Cart completed successfully");
    }
}
