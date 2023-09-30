package com.cg.cart;

import com.cg.cart.dto.CartResult;
import com.cg.model.Cart;
import com.cg.model.Order;
import com.cg.model.OrderStatus;
import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByStatus(CartResult cartResult);

    List<Cart> findAllByUserId(Long userId);
}