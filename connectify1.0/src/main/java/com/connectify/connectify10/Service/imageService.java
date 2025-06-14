package com.connectify.connectify10.Service;

import org.springframework.web.multipart.MultipartFile;

public interface imageService {

    String uploadImage(MultipartFile contactImage,String filename);
    String getUrlFormPublicId(String publicId);


}
