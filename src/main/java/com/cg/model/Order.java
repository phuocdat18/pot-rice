package com.cg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "orders")
@Accessors(chain = true)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal totalAmount;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_bills"))
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "location_region_id", foreignKey = @ForeignKey(name = "fk_user_location_region"))
    private LocationRegion locationRegion;

    @Column(name = "location_region_id", insertable = false, updatable = false)
    private Long locationRegionId;

    @Column(name = "bill_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(BigDecimal totalAmount, User user, LocationRegion locationRegion, OrderStatus status) {
        this.totalAmount = totalAmount;
        this.user = user;
        this.locationRegion = locationRegion;
        this.status = status;
    }

    public Order() {

    }
}
