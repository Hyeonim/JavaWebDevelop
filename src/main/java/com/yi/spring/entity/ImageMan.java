package com.yi.spring.entity;

import com.yi.spring.repository.ImgTableRepository;

public class ImageMan {
    private String data;
    public ImageMan(String restImg) {
        data = restImg;
    }

    public String setRestImg(ImgTableRepository imageTableRepository, ImageFrom from, byte[] restImg) {
        ImgTb entity = new ImgTb();
        entity.setId( data!=null? Long.valueOf(data) :null );
        entity.setDtype( from.name() );
        entity.setBytes( restImg );
        imageTableRepository.save( entity );

        return String.valueOf(entity.getId());
    }
}

