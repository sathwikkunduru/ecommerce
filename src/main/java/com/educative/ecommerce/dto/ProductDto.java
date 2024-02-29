package com.educative.ecommerce.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull String price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
}
