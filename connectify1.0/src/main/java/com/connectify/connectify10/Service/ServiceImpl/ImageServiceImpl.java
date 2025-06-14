package com.connectify.connectify10.Service.ServiceImpl;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.connectify.connectify10.Service.imageService;
import com.connectify.connectify10.helpers.AppConstants;

@Service
public class ImageServiceImpl implements imageService {
    @Autowired
    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary){
        this.cloudinary = cloudinary;

    }
    @Override
    public String uploadImage(MultipartFile contactImage,String filename) {
       
        try{
        byte [] data =new byte[contactImage.getInputStream().available()];
        contactImage.getInputStream().read(data);
        cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id",filename
            
        ));
        return this.getUrlFormPublicId(filename);


        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
       //code for the upload image



       //return kar raha hoga url
    }
    @Override
    public String getUrlFormPublicId(String publicId) {
      


        return cloudinary
        .url()
        .transformation(
            new Transformation<>()
            .width(AppConstants.CONTACT_IMAGE_WIDTH).height(AppConstants.CONTACT_IMAGE_HEIGHT).crop(AppConstants.CONTACT_IMAGE_CROP)
        )
        .generate(publicId);
        
    }
    


}
