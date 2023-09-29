package com.cg.order.dto;

import com.cg.model.OrderStatus;
import com.cg.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BillDetailResult {
    private Long id;
    private Product product;
    private String title;
    private String unit;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;
    private OrderResult orderResult;
    private OrderStatus status;

    public BillDetailResult(Long id, Product product, String title, String unit, BigDecimal price, Long quantity, BigDecimal amount, OrderResult orderResult) {
        this.id = id;
        this.product = product;
        this.title = title;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.orderResult = orderResult;
    }

    public BillDetailResult(Long id, Product product, String title, String unit, BigDecimal price, Long quantity, BigDecimal amount, OrderResult orderResult, OrderStatus status) {
        this.id = id;
        this.product = product;
        this.title = title;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.orderResult = orderResult;
        this.status = status;
    }

}
