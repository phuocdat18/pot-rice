package com.cg.order;


import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.model.CartItem;
import com.cg.model.OrderItem;
import com.cg.order.dto.OrderItemResult;
import com.cg.order.dto.OrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/orders"})
public class OrderAPI {

    private final IOrderService orderService;
    private final IOrderItemService orderItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAll(String status) {
        if (status == null)
            return orderService.findAll();
        return orderService.findAllByStatus(status);
    }


    @GetMapping("/{userId}/orders")
    @PreAuthorize("#userId == principal.id")
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAllByUserId(@PathVariable Long userId, String status) {
        if (status != null)
            return orderService.findAllByUserIdAndStatus(userId, status);
        return orderService.findAllByUserId(userId);
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem> findAllOrderItem(@PathVariable Long orderId) {
        return orderItemService.findAllByOrderId(orderId);
    }

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResult> findAllByStatus(@PathVariable String status) {
        if (status != null)
            return orderService.findAllByStatus(status);
        return orderService.findAll();
    }


    @GetMapping("/findAllByCreatedAt")
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAllByCreatedAt() {
        return orderService.findAllByCreatedAt(LocalDate.now());
    }
}
