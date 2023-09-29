package com.cg.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private Long quantity;

    @Lob
    private String description;

    private String unit;

    //    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_category_product"))
    private Category category;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @OneToOne
    @JoinColumn(name = "product_avatar_id", nullable = false)
    private ProductAvatar productAvatar;
    @Column(name = "product_avatar_id", insertable = false, updatable = false)
    private String productAvatarId;

    public Product setCategoryId(long categoryId) {
        category = new Category(this.categoryId = categoryId);
        return this;
    }

    public Product setProductAvatarId(String productAvatarId) {
        productAvatar = new ProductAvatar(this.productAvatarId = productAvatarId);
        return this;
    }

    public Object toProductDTO() {
        return new ProductDTO().setId(id).setTitle(title).setPrice(price).setQuantity(quantity).setUnit(unit).setDescription(description).setCategory(category).setAvatar(productAvatar.toProductAvatarResDTO());
    }

    public ProductCreateResDTO toProductCreateResDTO() {
        return new ProductCreateResDTO().setId(id).setTitle(title).setPrice(price).setUnit(unit).setQuantity(quantity).setDescription(description).setCategoryTitle(category.getTitle()).setAvatar(productAvatar.toProductAvatarResDTO());
    }

    public ProductUpdateResDTO toProductUpdateResDTO() {
        return new ProductUpdateResDTO().setId(id).setTitle(title).setPrice(price).setUnit(unit).setQuantity(String.valueOf(quantity)).setDescription(description).setCategoryTitle(category.getTitle()).setAvatar(productAvatar.toProductAvatarResDTO());
    }
}
