package com.ltp.furniture_store.web;

import com.ltp.furniture_store.entity.*;
import com.ltp.furniture_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addItemToCart(@PathVariable Integer customerId, @RequestBody CartItemDTO cartItemDTO) {
        try {
            RegisteredCustomer customer = registeredCustomerService.findUserById(customerId);
            Catalog catalog = catalogService.findCatalogById(cartItemDTO.getProductId());
            ShoppingCart cart = shoppingCartService.createOrUpdateCart(customer);
            ShoppingCartItem item = shoppingCartService.addItemToCart(cart, catalog, cartItemDTO.getQuantity());
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding item to cart: " + e.getMessage());
        }
    }

    // Remove or update item quantity in cart
    @PutMapping("/{customerId}/update")
    public ResponseEntity<?> updateCartItem(@PathVariable Integer customerId, @RequestBody CartItemDTO cartItemDTO) {
        try {
            RegisteredCustomer customer = registeredCustomerService.findUserById(customerId);
            Catalog catalog = catalogService.findCatalogById(cartItemDTO.getProductId());
            ShoppingCart cart = shoppingCartService.createOrUpdateCart(customer);

            shoppingCartService.updateCartItemQuantity(cart, catalog, cartItemDTO.getQuantity());

            return ResponseEntity.ok("Item quantity updated or removed from cart");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating cart item: " + e.getMessage());
        }
    }

    // Get all items in the cart for a specific customer
    @GetMapping("/{customerId}/items")
    public ResponseEntity<List<ShoppingCartItemDTO>> getCartItems(@PathVariable Integer customerId) {
        try {
            List<ShoppingCartItemDTO> cartItems = shoppingCartService.getCartItemsByCustomerId(customerId);
            if (cartItems.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}