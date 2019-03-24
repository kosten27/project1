package com.kostenko.controllers;

import com.kostenko.domain.Order;
import com.kostenko.services.ClientService;
import com.kostenko.services.OrderService;
import com.kostenko.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String showOrders(ModelMap modelMap) {
        List<String> orders = new ArrayList<>();
        for (Order order : orderService.getOrders()) {
            orders.add(order.toString());
        }
        modelMap.put("orders", orders);
        return "orders/orders";
    }

    @PostMapping("/orders")
    public String postOrder(ModelMap modelMap,
                            @RequestParam(required = false) String method,
                            @RequestParam(required = false) Long orderId,
                            @RequestParam(required = false) Long clientId,
                            @RequestParam(required = false) Long productId1,
                            @RequestParam(required = false) Long productId2,
                            @RequestParam(required = false) Long productId3,
                            @RequestParam(required = false) Long productId4,
                            @RequestParam(required = false) Long productId5) {
        if ("put".equals(method)) {
            return putOrder(modelMap, orderId, productId1, productId2, productId3, productId4, productId5);
        } else if ("delete".equals(method)) {
            return deleteOrder(modelMap, orderId);
        }

        List<Long> productsId = new ArrayList<>();
        if (productId1 != null && productId1 > 0) {
            productsId.add(productId1);
        }
        if (productId2 != null && productId2 > 0) {
            productsId.add(productId2);
        }
        if (productId3 != null && productId3 > 0) {
            productsId.add(productId3);
        }
        if (productId4 != null && productId4 > 0) {
            productsId.add(productId4);
        }
        if (productId5 != null && productId5 > 0) {
            productsId.add(productId5);
        }
        orderService.createOrder(clientId, productsId);
        return showOrders(modelMap);
    }

    @PutMapping("/orders")
    public String putOrder(ModelMap modelMap,
                           @RequestParam Long orderId,
                           @RequestParam Long productId1,
                           @RequestParam Long productId2,
                           @RequestParam Long productId3,
                           @RequestParam Long productId4,
                           @RequestParam Long productId5) {

        List<Long> productsId = new ArrayList<>();
        if (productId1 != null && productId1 > 0) {
            productsId.add(productId1);
        }
        if (productId2 != null && productId2 > 0) {
            productsId.add(productId2);
        }
        if (productId3 != null && productId3 > 0) {
            productsId.add(productId3);
        }
        if (productId4 != null && productId4 > 0) {
            productsId.add(productId4);
        }
        if (productId5 != null && productId5 > 0) {
            productsId.add(productId5);
        }
        orderService.modifyOrder(orderId, productsId);
        return showOrders(modelMap);
    }

    @DeleteMapping("/orders")
    public String deleteOrder(ModelMap modelMap, @RequestParam long orderId) {
        orderService.deleteOrder(orderId);
        return showOrders(modelMap);
    }

    @GetMapping("/orders/addOrder")
    public String mappingAddOrder(ModelMap modelMap) {
        return "orders/addOrder";
    }

    @GetMapping("/orders/modifyOrder")
    public String mappingModifyOrder(ModelMap modelMap) {
        return "orders/modifyOrder";
    }

    @GetMapping("/orders/deleteOrder")
    public String mappingDeleteOrder(ModelMap modelMap) {
        return "orders/deleteOrder";
    }
}
