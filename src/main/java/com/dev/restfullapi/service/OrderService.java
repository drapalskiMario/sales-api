package com.dev.restfullapi.service;

import com.dev.restfullapi.rest.dto.output.InfoOrderDTO;
import com.dev.restfullapi.rest.dto.input.OrderDTO;
import com.dev.restfullapi.rest.dto.input.UpdateStatusOrderDTO;

import java.util.List;

public interface OrderService {
    void create(OrderDTO orderDTO);

    List<InfoOrderDTO> getAll();

    void updateStatus(Integer id, UpdateStatusOrderDTO newStatus);
}
