package com.cg.service.upload;

import java.io.IOException;
import java.util.Map;

public interface IUploadService {
    Map uploadImage(String multipartFile, Map params) throws IOException;

    Map destroyImage(String publicId, Map params) throws IOException;

}
