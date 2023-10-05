package com.cg.product;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product toEntity(ProductCreationParam creationParam){
        return new Product()
                .setTitle(creationParam.getTitle())
                .setPrice(creationParam.getPrice())
                .setUnit(creationParam.getUnit())
                .setDescription(creationParam.getDescription())
                .setQuantity(creationParam.getQuantity())
                .setCategoryId(creationParam.getCategoryId())
                .setProductAvatarId(creationParam.getProductAvatarId());

    }

    public void transferFields(Product entity, ProductUpdateParam updateParam){
        entity.setTitle(updateParam.getTitle());
        entity.setPrice(updateParam.getPrice());
        entity.setUnit(updateParam.getUnit());
        entity.setDescription(updateParam.getDescription());
        entity.setQuantity(updateParam.getQuantity());
        entity.setCategoryId(updateParam.getCategoryId());
        entity.setProductAvatarId(updateParam.getProductAvatarId());
    }

    public ProductResult toDTO(Product entity){
        return new ProductResult()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setPrice(entity.getPrice())
                .setUnit(entity.getUnit())
                .setDescription(entity.getDescription())
                .setQuantity(entity.getQuantity())
                .setCategory(entity.getCategory())
                .setAvatar(entity.getAvatar())
                ;
    }

    public List<ProductResult> toDTOList(List<Product> entities){
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }


    
}
