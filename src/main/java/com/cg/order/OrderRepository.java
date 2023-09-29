package com.cg.order;

import com.cg.model.Order;
import com.cg.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus orderStatus);

    List<Order> findAllByUserId(Long id);

    List<Order> findAllByUserIdAndStatus(Long userId, OrderStatus status);

    List<Order> findAllByCreatedAtAndStatus(LocalDate createdAt, OrderStatus status);
}