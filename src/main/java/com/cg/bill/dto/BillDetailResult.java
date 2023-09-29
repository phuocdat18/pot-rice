package com.cg.bill.dto;

import com.cg.model.Bill;
import com.cg.model.EPayment;
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
    private BillResult billResult;
    private EPayment status;

    public BillDetailResult(Long id, Product product, String title, String unit, BigDecimal price, Long quantity, BigDecimal amount, BillResult billResult) {
        this.id = id;
        this.product = product;
        this.title = title;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.billResult= billResult;
    }

    public BillDetailResult(Long id, Product product, String title, String unit, BigDecimal price, Long quantity, BigDecimal amount, BillResult billResult, EPayment status) {
        this.id = id;
        this.product = product;
        this.title = title;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.billResult= billResult;
        this.status = status;
    }

}
