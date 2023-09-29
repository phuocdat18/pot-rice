package com.cg.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class CategoryUpdateParam {
    @NotNull(message = "name not null")
    private String title;
}
