package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bill_details")
public class BillDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_bill_detail"))
    private Product product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    private String title;
    private String unit;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private Long quantity;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal amount;


    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_bill"))
    private Bill bill;

    @Column(name = "bill_id", insertable = false, updatable = false)
    private int billId;

    public BillDetail(Product product, String title, String unit, BigDecimal price, Long quantity, BigDecimal amount, Bill bill) {
        this.product = product;
        this.title = title;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.bill = bill;
    }

    public BillDetail() {

    }
}
