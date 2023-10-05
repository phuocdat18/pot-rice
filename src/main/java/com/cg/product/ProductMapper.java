package com.cg.product;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.product.dto.BaseProduct;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import com.cg.user.dto.BaseUser;
import com.cg.user.dto.UserResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.rananu.shared.mappers.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper extends BaseMapper<ProductResult, Product, BaseProduct> {

}
