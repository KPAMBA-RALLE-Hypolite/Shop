package com.pkfokam.Shop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String url;
    private String altText;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
