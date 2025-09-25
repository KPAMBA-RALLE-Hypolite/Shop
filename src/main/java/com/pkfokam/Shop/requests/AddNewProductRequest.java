package com.pkfokam.Shop.requests;

import com.pkfokam.Shop.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddNewProductRequest {
    private  long id;
    private String name;
    private BigDecimal price;
    private String brand;
    private String description;
    private Category category;
}
