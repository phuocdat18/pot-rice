package com.cg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal totalAmount;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_cart"))
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public Cart setUserId(Long userId) {
        this.user = new User(this.userId = userId);
        return this;
    }
}
