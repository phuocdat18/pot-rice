package com.cg.product;

import com.cg.model.Product;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductFilter;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import com.cg.user.dto.UserResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.rananu.shared.exceptions.NotFoundException;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductFilterRepository filterRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));
    }

    @Override
    public ProductResult getById(Long id) {
        Product entity = findById(id);
        return modelMapper.map(entity, ProductResult.class);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Page<ProductResult> findAllByFilter(ProductFilter filter, Pageable pageable) {
//        Page<Product> entities = filterRepository.findAllByFilter(filter, pageable);
//
//        return entities.stream()
//                .map(product -> modelMapper.map(product, ProductResult.class))
//                .collect(Collectors.toList());
//    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResult> findAllByFilter(ProductFilter filter, Pageable pageable) {
        Page<Product> entities = filterRepository.findAllByFilter(filter, pageable);

        return entities.map(product -> modelMapper.map(product, ProductResult.class));
    }

    @Override
    @Transactional
    public ProductResult create(ProductCreationParam param) {
        Product entity = productMapper.toEntity(param);
        entity = productRepository.save(entity);
        return productMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public ProductResult update(Long id, ProductUpdateParam param) {
        Product entity = findById(id);
        productMapper.transferFields(entity, param);
        return productMapper.toDTO(entity);
    }
}
