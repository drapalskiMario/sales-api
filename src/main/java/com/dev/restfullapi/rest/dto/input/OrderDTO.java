package com.dev.restfullapi.rest.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    @NotNull(message = "field [client] is required")
    private Integer client;

    @NotNull(message = "field [total] is required")
    private BigDecimal total;

    @NotEmpty(message = "field [items] is required")
    @Valid
    private List<OrderItemDTO> items;

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
