package com.cg.product;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;
    public Product toEntity(ProductCreationParam creationParam){
        return modelMapper.map(creationParam, Product.class);

    }

    public void transferFields(Product entity, ProductUpdateParam updateParam){
        modelMapper.map(entity,updateParam);
    }

    public ProductResult toDTO(Product entity){
        return modelMapper.map(entity,ProductResult.class);
    }

    public List<ProductResult> toDTOList(List<Product> entities){
        return entities.stream().map(entity->modelMapper.map(entity, ProductResult.class)).collect(Collectors.toList());
    }
    
}
