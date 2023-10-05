package com.cg.category;

import com.cg.model.Category;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryCreationParam param){

        return new Category().setTitle(param.getTitle());
    }

    public void transferFields(Category entity, CategoryUpdateParam updateParam){
        entity.setTitle(updateParam.getTitle());
    }

    public CategoryResult toDTO(Category entity){
        return new CategoryResult()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                ;
    }

    public List<CategoryResult> toDTOList(List<Category> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
