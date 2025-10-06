package com.pkfokam.Shop.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private long id;
    private String name;
    private String url;

    public ImageDTO(long id, String name, String url) {
    }
}
