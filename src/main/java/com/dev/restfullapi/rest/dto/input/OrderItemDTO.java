package com.dev.restfullapi.rest.dto.input;

import javax.validation.constraints.NotNull;

public class OrderItemDTO {

    @NotNull(message = "field [product] is required")
    private Integer product;

    @NotNull(message = "field [quantity] is required")
    private Integer quantity;

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
