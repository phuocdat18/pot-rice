package com.cg.product.dto;

import com.cg.avatar.dto.ProductAvatarResDTO;
import com.cg.model.Category;
import com.cg.model.Product;
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
public class ProductCreationParam extends BaseProduct {

}


