package com.ltp.furniture_store.service;

import com.ltp.furniture_store.entity.*;
import com.ltp.furniture_store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ShoppingCartStatusRepository shoppingCartStatusRepository;

    @Autowired
    private RegisteredCustomerRepository registeredCustomerRepository;

    // Create or update a shopping cart for a registered customer
    public ShoppingCart createOrUpdateCart(RegisteredCustomer customer) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findByCustomerAndShoppingCartStatus_StatusDescription(customer, ShoppingCartStatusEnum.ACTIVE);

        ShoppingCart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            ShoppingCartStatus activeStatus = shoppingCartStatusRepository.findByStatusDescription(ShoppingCartStatusEnum.ACTIVE);
            cart = new ShoppingCart();
            cart.setCustomer(customer);
            cart.setShoppingCartStatus(activeStatus);
            cart.setCreatedAt(new Date());
            cart.setUpdatedAt(new Date());
            shoppingCartRepository.save(cart);
        }
        return cart;
    }

    // Add an item to the shopping cart
    @Transactional
    public ShoppingCartItem addItemToCart(ShoppingCart cart, Catalog catalog, int quantity) {
        if (catalog.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + catalog.getProductName());
        }

        catalog.setStock(catalog.getStock() - quantity);
        catalogRepository.save(catalog);

        ShoppingCartItemId itemId = new ShoppingCartItemId(cart.getCartId(), Math.toIntExact(catalog.getProductID()));
        ShoppingCartItem item = new ShoppingCartItem(itemId, cart, catalog, quantity);
        shoppingCartItemRepository.save(item);

        cart.setUpdatedAt(new Date());
        shoppingCartRepository.save(cart);

        return item;
    }

    // Remove an item from the shopping cart
    @Transactional
    public void removeItemFromCart(ShoppingCart cart, Catalog catalog) {
        ShoppingCartItemId itemId = new ShoppingCartItemId(cart.getCartId(), Math.toIntExact(catalog.getProductID()));
        ShoppingCartItem item = shoppingCartItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found in cart"));

        catalog.setStock(catalog.getStock() + item.getQuantityCart());
        catalogRepository.save(catalog);

        shoppingCartItemRepository.delete(item);

        if (shoppingCartItemRepository.findByShoppingCart(cart).isEmpty()) {
            ShoppingCartStatus cancelledStatus = shoppingCartStatusRepository.findByStatusDescription(ShoppingCartStatusEnum.CANCELLED);
            cart.setShoppingCartStatus(cancelledStatus);
        }

        cart.setUpdatedAt(new Date());
        shoppingCartRepository.save(cart);
    }

    // Complete the cart
    @Transactional
    public void completeCart(ShoppingCart cart) {
        ShoppingCartStatus completedStatus = shoppingCartStatusRepository.findByStatusDescription(ShoppingCartStatusEnum.COMPLETED);
        cart.setShoppingCartStatus(completedStatus);
        cart.setUpdatedAt(new Date());
        shoppingCartRepository.save(cart);
    }
}
