package com.cg.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bill_details")
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_order_item_product"))
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


//    @ManyToOne
//    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_order"))
//    private Order order;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_order"))
    private Order order;

    @Column(name = "order_id", insertable = false, updatable = false)
    private int orderId;

}
