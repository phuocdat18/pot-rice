package com.cg.category;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class CategoryCreationParam {
    @NotNull(message = "title not null")
    private String title;

}
