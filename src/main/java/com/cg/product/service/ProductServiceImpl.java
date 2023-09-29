package com.cg.product.service;

import com.cg.avatar.ProductAvatarRepository;
import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.mapper.MapUtils;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import com.cg.product.ProductMapper;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import com.cg.product.ProductRepository;
import com.cg.service.upload.IUploadService;
import com.cg.utils.UploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductAvatarRepository productAvatarRepository;
    private final IUploadService uploadService;
    private final UploadUtils uploadUtils;


    @Override
    @Transactional
    public ProductResult create(ProductCreationParam param, Category category) {
        Product entity = productMapper.toEntity(param);
        entity = productRepository.save(entity);
        return productMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public ProductResult update(Long id, ProductUpdateParam productUpdateParam, Category category) {
        Product entity = findById(id);
        productMapper.transferFields(entity, productUpdateParam, category);
//        return productMapper.toDTO(entity);
        return MapUtils.toDTO(entity, ProductResult.class);
    }

    @Override
    public ProductResult getById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResult> findAll() {
        List<Product> entities = productRepository.findAll();
        return productMapper.toDTOList(entities);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));
    }

    @Override
    public Boolean existsProductById(Long id) {
        return productRepository.existsProductById(id);
    }

    @Override
    public Page<ProductResult> findAllProductDTOByKeyWordAndCategoryAndPrice(String search, List<Long> category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findAllProductDTOByKeyWordAndCategoryAndPrice(search, category, minPrice, maxPrice, pageable);
    }

    @Override
    public Page<ProductResult> findAllProductDTOByKeyWordAndCategory(String search, List<Long> category, Pageable pageable) {
        return productRepository.findAllProductDTOByKeyWordAndCategory(search, category, pageable);
    }

    @Override
    public Page<ProductResult> findAllProductDTOPage(Pageable pageable) {
        return productRepository.findAllProductDTOPage(pageable);
    }

    private void uploadAndSaveProductImage(ProductCreationParam param, ProductAvatar productAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(param.getProductAvatarId(), uploadUtils.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            productAvatarRepository.save(productAvatar);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
        productAvatarRepository.delete(product.getProductAvatar());
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
