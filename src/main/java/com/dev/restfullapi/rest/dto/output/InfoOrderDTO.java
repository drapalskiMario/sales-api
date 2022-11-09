package com.dev.restfullapi.rest.dto.output;

import com.dev.restfullapi.domain.enums.StatusOrder;

import java.math.BigDecimal;
import java.util.List;

public class InfoOrderDTO {
    private Integer id;
    private String nameClient;
    private BigDecimal total;
    private List<InfoOrderItemDTO> items;
    private StatusOrder status;

    public InfoOrderDTO(Integer id, String nameClient, BigDecimal total, List<InfoOrderItemDTO> items, StatusOrder status) {
        this.id = id;
        this.nameClient = nameClient;
        this.total = total;
        this.items = items;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<InfoOrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<InfoOrderItemDTO> items) {
        this.items = items;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }
}
