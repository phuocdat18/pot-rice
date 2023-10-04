package com.cg.product;

import com.cg.model.Product;
import com.cg.product.dto.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductFilterRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    default Page<Product> findAllByFilter(ProductFilter filter, Pageable pageable) {
        return findAll((root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getKeyword() != null) {
                Predicate predicateTitle = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), '%' + filter.getKeyword() + '%');
                predicates.add(predicateTitle);
            }
            if (!filter.getCategoryIds().isEmpty()) {
                Predicate predicateCategory = criteriaBuilder.or(root.get("category").get("id").in(filter.getCategoryIds()));
                predicates.add(predicateCategory);
            }
            if (filter.getMinPrice() != null && filter.getMaxPrice() != null) {
                Predicate predicatePrice = criteriaBuilder.between(root.get("price"), filter.getMinPrice(), filter.getMaxPrice());
                predicates.add(predicatePrice);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);

    }
}
