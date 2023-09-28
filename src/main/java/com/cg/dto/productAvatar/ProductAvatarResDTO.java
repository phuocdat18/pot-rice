package com.cg.dto.productAvatar;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ProductAvatarResDTO {

    private String id;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
}
