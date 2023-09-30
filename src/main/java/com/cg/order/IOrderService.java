package com.cg.order;

import com.cg.order.dto.OrderResult;
import com.cg.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
    Order findById(Long id);

    OrderResult getById(Long id);

    List<OrderResult> findAll();

    List<OrderResult> findAllByStatus(String status);

    List<?> findAllByUserId(Long userId);

    List<?> findAllByUserIdAndStatus(Long userId, String status);

    List<?> findAllByCreatedAt(LocalDate createdAt);
}