package com.cg.product.dto;

import com.cg.avatar.dto.ProductAvatarResDTO;
import com.cg.model.ProductAvatar;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductCreationParam {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    @Size(min = 1, message = "")
    private Long quantity;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String unit;
    @Column(nullable = false)
    private Long categoryId;

    private MultipartFile avatar;}
