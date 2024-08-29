package com.ltp.furniture_store.service;//package com.ltp.furniture_store.service;
//
//import com.ltp.furniture_store.entity.OrderItem;
//import com.ltp.furniture_store.repository.paymentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//public class PaymentService {
//
//    @Autowired
//    private paymentRepository paymentRepository;
//
//    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
//        return paymentRepository.findByOrder_OrderId(orderId);
//    }
//
//    public Double calculateTotalPrice(Integer orderId) {
//        List<OrderItem> orderItems = getOrderItemsByOrderId(orderId);
//        BigDecimal totalPrice = orderItems.stream()
//                .map(item -> item.getCatalog().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        return totalPrice.doubleValue();
//    }
//}

import com.example.model.*;
import com.example.repository.*;
import com.example.utils.EncryptionService;
import com.ltp.furniture_store.entity.Order;
import com.ltp.furniture_store.repository.CatalogRepository;
import com.ltp.furniture_store.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private CreditCardDetailRepository creditCardDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void processPayment(Order order, ShoppingCart cart, List<OrderItem> orderItems, CreditCardDetail creditCardDetail, EncryptionService encryptionService) {
        // שמירת פרטי כרטיס אשראי
        saveCreditCardDetails(creditCardDetail, encryptionService);

        // ריקון סל הקניות
        clearShoppingCart(cart);

        // עדכון המלאי של המוצרים
        updateProductStock(orderItems);

        // עדכון סטטוס ההזמנה
        updateOrderStatus(order, Status.PAID);

        // הצגת הודעת הצלחה
        System.out.println("תשלום בוצע בהצלחה!");
    }

    private void saveCreditCardDetails(CreditCardDetail creditCardDetail, EncryptionService encryptionService) {
        creditCardDetail.setCardholderId(creditCardDetail.getCardholderId(encryptionService), encryptionService);
        creditCardDetail.setCreditCardNumber(creditCardDetail.getCreditCardNumber(encryptionService), encryptionService);
        creditCardDetail.setCvv(creditCardDetail.getCvv(encryptionService), encryptionService);
        creditCardDetailRepository.save(creditCardDetail);
    }

    private void clearShoppingCart(ShoppingCart cart) {
        cart.setShoppingCartStatus(ShoppingCartStatus.EMPTY);
        shoppingCartRepository.save(cart);
    }

    private void updateProductStock(List<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            Catalog product = item.getProduct();
            int updatedStock = product.getStock() - item.getQuantity();
            product.setStock(updatedStock);
            catalogRepository.save(product);
        }
    }

    private void updateOrderStatus(Order order, Status newStatus) {
        order.setStatus(newStatus);
        orderRepository.save(order);
    }
}

