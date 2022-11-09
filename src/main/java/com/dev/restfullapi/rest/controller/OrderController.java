package com.dev.restfullapi.rest.controller;

import com.dev.restfullapi.rest.dto.output.InfoOrderDTO;
import com.dev.restfullapi.rest.dto.input.OrderDTO;
import com.dev.restfullapi.rest.dto.input.UpdateStatusOrderDTO;
import com.dev.restfullapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid OrderDTO orderDTO) {
        this.orderService.create(orderDTO);
    }

    @GetMapping
    public List<InfoOrderDTO> getAll() {
        return this.orderService.getAll();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody @Valid UpdateStatusOrderDTO newStatus) {
        this.orderService.updateStatus(id, newStatus);
    }
}
