package com.cg.avatar;

import com.cg.model.ProductAvatar;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductAvatarService {
    List<ProductAvatar> findAll();

    Optional<ProductAvatar> findById(String id);

    ProductAvatar save(ProductAvatar productAvatar);
}
