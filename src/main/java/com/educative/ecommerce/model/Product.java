package com.educative.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull String price;
    private @NotNull String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

}
