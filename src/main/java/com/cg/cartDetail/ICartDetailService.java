package com.cg.cartDetail;

import com.cg.cartDetail.dto.CartDetailResult;
import com.cg.model.CartDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ICartDetailService {
    List<CartDetail> findAll();

    Optional<CartDetail> findById(Long id);

    CartDetail create(CartDetail cartDetail);

    void delete(CartDetail cartDetail);

    void deleteById(Long id);


    List<CartDetailResult> findAllCartDetailDTO(Long id);

    List<CartDetail> findAllByCartId(Long cartId);
}