package com.java_learning.shopping_cart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String fileName;

    @Getter @Setter
    private String fileType;

    @Getter @Setter
    @Lob
    private Blob image;

    @Getter @Setter
    private String downloadURL;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "product_id")
    private Product product;
}
