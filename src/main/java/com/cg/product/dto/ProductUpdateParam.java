package com.cg.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductUpdateParam extends BaseProduct {

}
