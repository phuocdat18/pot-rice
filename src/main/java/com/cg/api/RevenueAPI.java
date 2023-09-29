package com.cg.api;

import com.cg.order.IOrderService;

import com.cg.category.ICategoryService;


import com.cg.product.service.IProductService;

import com.cg.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/revenue")
@RequiredArgsConstructor
public class RevenueAPI {
    private final IProductService productService;
    private final IOrderService billService;
    private ICategoryService categoryService;
    private final ValidateUtils validateUtils;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<?> getAllBills() {
       return billService.findAll();
    }



}
