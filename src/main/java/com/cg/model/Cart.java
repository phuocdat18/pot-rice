package com.cg.model;

import com.cg.model.dto.cart.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private int userId;

//    public CartDTO toCartDTO() {
//        return new CartDTO()
//                .setId(id)
//                .setTotalAmount(totalAmount);
//    }
}
