package com.cg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cart_details")
@Accessors(chain = true)
public class CartDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false , foreignKey = @ForeignKey(name = "fk_product_cart_detail"))
    private Product product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    private String title;

    private String unit;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private Long quantity;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cart_bill_detail"))
    private Cart cart;

    @Column(name = "cart_id", insertable = false, updatable = false)
    private Long cartId;
}