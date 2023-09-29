package com.cg.product;

import com.cg.model.Product;
import com.cg.product.mapper.ProductResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT  " +
            "pr.id, " +
            "pr.title, " +
            "pr.price, " +
            "pr.quantity, " +
            "pr.description, " +
            "pr.unit, " +
            "pr.category, " +
            "pr.productAvatar " +
            "FROM Product AS pr " +
            "WHERE pr.quantity > -1"
    )
    List<ProductResult> findAllProductDTO();

    @Query("SELECT  " +
            "pr.id, " +
            "pr.title, " +
            "pr.price, " +
            "pr.description, " +
            "pr.unit, " +
            "pr.category, " +
            "pr.productAvatar " +
            "FROM Product AS pr " +
            "WHERE lower(pr.title)LIKE CONCAT('%', LOWER(?1), '%') " +
            "AND pr.category.id IN (?2) " +
            "AND pr.price BETWEEN ?3 AND ?4 " +
            "AND pr.quantity > -1" +
            "ORDER BY pr.id ASC"
    )
    Page<ProductResult> findAllProductDTOByKeyWordAndCategoryAndPrice (String search, List<Long> category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

     @Query("SELECT  " +
            "pr.id, " +
            "pr.title, " +
            "pr.price, " +
            "pr.description, " +
            "pr.unit, " +
            "pr.category, " +
            "pr.productAvatar " +
            "FROM Product AS pr " +
            "WHERE lower(pr.title)LIKE CONCAT('%', LOWER(?1), '%') " +
            "AND pr.category.id IN (?2) " +
            "AND pr.quantity > -1" +
            "ORDER BY pr.id ASC"
    )
    Page<ProductResult> findAllProductDTOByKeyWordAndCategory (String search, List<Long> category, Pageable pageable);


    @Query("SELECT " +
            "pr.id, " +
            "pr.title, " +
            "pr.price, " +
            "pr.quantity, " +
            "pr.description, " +
            "pr.unit, " +
            "pr.category, " +
            "pr.productAvatar " +
            "FROM Product AS pr " +
            "WHERE pr.quantity > -1" +
            "ORDER BY pr.id DESC"
    )
    Page<ProductResult> findAllProductDTOPage(Pageable pageable);
    Boolean existsProductById(Long id);
}