package com.ltp.furniture_store.web;

import com.ltp.furniture_store.entity.*;
import com.ltp.furniture_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/{customerId}/add")
    public ResponseEntity<?> addItemToCart(@PathVariable Integer customerId, @RequestParam Long productId, @RequestParam int quantity) {
        RegisteredCustomer customer = registeredCustomerService.findUserById(customerId);
        Catalog catalog = catalogService.findCatalogById(productId);
        ShoppingCart cart = shoppingCartService.createOrUpdateCart(customer);
        ShoppingCartItem item = shoppingCartService.addItemToCart(cart, catalog, quantity);
        return ResponseEntity.ok(item);
    }

    // Remove item from cart
    @DeleteMapping("/{customerId}/delete")
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

    // Get all items in the cart for a specific customer
    @GetMapping("/{customerId}/items")
    public ResponseEntity<List<ShoppingCartItemDTO>> getCartItems(@PathVariable Integer customerId) {
        List<ShoppingCartItemDTO> cartItems = shoppingCartService.getCartItemsByCustomerId(customerId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cartItems);
    }
}
