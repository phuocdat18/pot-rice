package com.cg.cartDetail;

import com.cg.cartDetail.dto.CartDetailResult;
import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ICartDetailService extends IGeneralService<CartDetail, Long> {
    boolean existsCartDetailByCart(Cart cart);

    CartDetail findCartDetailsByProductAndCart(Product product, Cart cart);

    List<CartDetailResult> findAllCartDetailDTO(Long id);

    List<CartDetail> findAllByCartId(Long cartId);
}