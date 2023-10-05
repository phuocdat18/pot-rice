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
        Product product = findById(id);
        return modelMapper.map(product, ProductResult.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResult> findAllByFilter(ProductFilter filter, Pageable pageable) {
        Page<Product> entities = filterRepository.findAllByFilter(filter, pageable);
        return entities.map(product -> modelMapper.map(product, ProductResult.class));
    }

    @Override
    @Transactional
    public ProductResult create(ProductCreationParam param) {
//        Product product = productMapper.toEntity(param);
        Product product = modelMapper.map(param, Product.class);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductResult.class);
    }

//    @Override
//    @Transactional
//    public UserResult signup(UserCreationParam creationParam) {
//        validateByUsername(creationParam.getUsername());
//        validateByEmail(creationParam.getEmail());
//
//        User entity = modelMapper.map(creationParam, User.class);
//        entity.setRoleId(RoleCode.CUSTOMER);
//        String passwordEncode = passwordEncoder.encode(creationParam.getPassword());
//        entity.setPassword(passwordEncode);
//        entity = userRepository.save(entity);
//
//        return modelMapper.map(entity, UserResult.class);
//    }

    @Override
    @Transactional
    public ProductResult update(Long id, ProductUpdateParam param) {
        Product product = findById(id);
        productMapper.transferFields(product, param);
        return modelMapper.map(product, ProductResult.class);
    }
}
