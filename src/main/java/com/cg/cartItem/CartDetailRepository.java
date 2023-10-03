package com.cg.cartItem;

import com.cg.cartItem.dto.CartDetailResult;
import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsCartDetailByCart(Cart cart);

    CartDetail findCartDetailsByProductAndCart(Product productResult, Cart cart);

    List<CartDetailResult> findAllByCartId(Long cardId);

//    @Query("SELECT " +
//            "cd.id, " +
//            "cd.product, " +
//            "cd.title, " +
//            "cd.unit, " +
//            "cd.price, " +
//            "cd.quantity, " +
//            "cd.amount " +
//            "FROM CartDetail AS cd " +
//            "JOIN Cart c ON cd.cart.id = c.id " +
//            "WHERE c.user.id = :id"
//    )
//    List<CartDetailResult> findAllCartDetailDTO(Long id);
}