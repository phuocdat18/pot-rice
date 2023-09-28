package com.cg.product.mapper;

import com.cg.model.Product;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductCreationParam creationParam){
        return new Product()
                .setTitle(creationParam.getTitle())
                .setPrice(creationParam.getPrice())
                .setUnit(creationParam.getUnit())
                .setDescription(creationParam.getDescription())
                .setQuantity(creationParam.getQuantity())
                .setCategoryId(creationParam.getCategoryId());

    }

    public Product toEntity(ProductUdateParam updateParam){
        return new Product()
                .setTitle(updateParam.getTitle())
                .setPrice(updateParam.getPrice())
                .setUnit(updateParam.getUnit())
                .setDescription(updateParam.getDescription())
                .setQuantity(updateParam.getQuantity())
                .setCategoryId(updateParam.getCategoryId());

    }
    
    
}
