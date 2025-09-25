package com.pkfokam.Shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String name;
    
    @OneToMany(mappedBy = "category",  orphanRemoval = true)
    private List<Product> product;
}
