package com.cg.product;

import com.cg.avatar.ProductAvatarRepository;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductFilter;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import com.cg.service.upload.IUploadService;
import com.cg.user.dto.UserResult;
import com.cg.utils.UploadUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.rananu.shared.Result;
import vn.rananu.shared.exceptions.NotFoundException;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductFilterRepository filterRepository;
    private final ModelMapper modelMapper;
    private final UploadUtils uploadUtils;
    private final IUploadService uploadService;
    private final ProductAvatarRepository productAvatarRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("product.exception.notFound"));
    }

    @Override
    public ProductResult getById(Long id) {
        Product entities = findById(id);
        return productMapper.toDTO(entities);
    }

//    @Override
//    public Result<?> getById(Long id) {
//        Product entities = findById(id);
//        ProductResult dto = productMapper.toDTO(entities);
//        return Result.success(dto);
//    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResult> findAllByFilter(ProductFilter filter, Pageable pageable) {
        Page<Product> entities = filterRepository.findAllByFilter(filter, pageable);
        return entities.map(product -> modelMapper.map(product, ProductResult.class));
    }

    @Override
    @Transactional
    public ProductResult create(ProductCreationParam param) {
        Product entities = productMapper.toEntity(param);

        ProductAvatar productAvatar = new ProductAvatar();
        productAvatarRepository.save(productAvatar);

        uploadAndSaveProductImage(param, productAvatar);

        entities.setQuantity(100L);
        entities.setAvatar(productAvatar);

        entities = productRepository.save(entities);
        return productMapper.toDTO(entities);
    }

    @Override
    @Transactional
    public ProductResult update(Long id, ProductUpdateParam param) {
        Product entities = findById(id);
        productMapper.transferFields(param, entities, true);
        return productMapper.toDTO(entities);
    }

    private void uploadAndSaveProductImage(ProductCreationParam param, ProductAvatar productAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(param.getAvatar(), uploadUtils.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            productAvatarRepository.save(productAvatar);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Upload hình ảnh thất bại");
        }
    }
}
