////package com.ltp.furniture_store.web;
////
////import com.ltp.furniture_store.entity.ShoppingCartItem;
////import com.ltp.furniture_store.service.shopingCartService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/api/cart")
////public class shopingCartController {
////
////
////    @Autowired
////    private shopingCartService shoppingCartService;
////
////    @PostMapping("/add")
////    public ShoppingCartItem addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
////        return shopingCartService.addToCart(productId, quantity);
////    }
////
////    @GetMapping("/items")
////    public List<ShoppingCartItem> getCartItems() {
////        return shopingCartService.getAllItems();
////    }
////}
//package com.ltp.furniture_store.web;
//import com.ltp.furniture_store.entity.ShoppingCartItem;
//import com.ltp.furniture_store.service.shopingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/cart")
//public class ShoppingCartController {
//
//    @Autowired
//    private shopingCartService shoppingCartService;
//
//    @PostMapping("/add")
//    public ShoppingCartItem addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
//        return shoppingCartService.addToCart(productId, quantity);
//    }
//
//    @GetMapping("/items")
//    public List<ShoppingCartItem> getCartItems() {
//        return shopingCartService.getAllItems();
//    }
//}
