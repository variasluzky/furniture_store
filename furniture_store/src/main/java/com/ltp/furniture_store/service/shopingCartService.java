//package com.ltp.furniture_store.service;
////import com.example.store.entity.Product;
//import com.ltp.furniture_store.entity.ShoppingCartItem;
////import com.example.store.repository.ProductRepository;
//import com.ltp.furniture_store.repository.ShoppingCartItemRepository;
//import com.ltp.furniture_store.entity.ShoppingCartItem;
//import com.ltp.furniture_store.repository.ShoppingCartItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class shopingCartService {
//
//    @Autowired
//    private ShoppingCartItemRepository shoppingCartItemRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    public ShoppingCartItem addToCart(Long productId, Integer quantity) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        ShoppingCartItem cartItem = new ShoppingCartItem();
//        cartItem.setProduct(product);
//        cartItem.setQuantity(quantity);
//
//        return shoppingCartItemRepository.save(cartItem);
//    }
//
//    public List<ShoppingCartItem> getAllItems() {
//        return shoppingCartItemRepository.findAll();
//    }
//
//}
