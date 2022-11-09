package com.dev.restfullapi.service.impl;

import com.dev.restfullapi.domain.entity.Client;
import com.dev.restfullapi.domain.entity.Order;
import com.dev.restfullapi.domain.entity.OrderItem;
import com.dev.restfullapi.domain.enums.StatusOrder;
import com.dev.restfullapi.domain.repository.ClientRepository;
import com.dev.restfullapi.domain.repository.OrderItemRepository;
import com.dev.restfullapi.domain.repository.OrderRepository;
import com.dev.restfullapi.domain.repository.ProductRepository;
import com.dev.restfullapi.exception.NotFoundException;
import com.dev.restfullapi.rest.dto.output.InfoOrderDTO;
import com.dev.restfullapi.rest.dto.output.InfoOrderItemDTO;
import com.dev.restfullapi.rest.dto.input.OrderDTO;
import com.dev.restfullapi.rest.dto.input.UpdateStatusOrderDTO;
import com.dev.restfullapi.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private static String CLIENT_NOT_FOUND_MESSAGE = "Client not found!";
    private static String PRODUCT_NOT_FOUND_MESSAGE = "Product with id: %s not found!";

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(ClientRepository clientRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public void create(OrderDTO orderDTO) {
        final var client = this.getClient(orderDTO);
        final var entity = new Order(client, LocalDate.now(), orderDTO.getTotal(), StatusOrder.COMPLETED);
        this.orderRepository.save(entity);
        this.saveOrderItemList(orderDTO, entity);
    }

    private Client getClient(OrderDTO orderDTO) {
        return this
                .clientRepository
                .findById(orderDTO.getClient())
                .orElseThrow(() -> new NotFoundException(CLIENT_NOT_FOUND_MESSAGE));
    }

    private void saveOrderItemList(OrderDTO orderDTO, Order order) {
        orderDTO
                .getItems()
                .forEach(dto -> {
                    final var productId = dto.getProduct();
                    final var product = this
                            .productRepository
                            .findById(productId)
                            .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, productId)));
                    final var entity = new OrderItem(order, product, dto.getQuantity());
                    this.orderItemRepository.save(entity);
                });
    }

    @Override
    public List<InfoOrderDTO> getAll() {
        final var orderList = this.orderRepository.findAllFetchItems();
        return orderList
                .stream()
                .map(order -> {
                    final var orderItemInfoList = this.getOrderItemInfoList(order);
                    return new InfoOrderDTO(order.getId(), order.getClient().getName(), order.getTotal(), orderItemInfoList, order.getStatus());
                }).collect(Collectors.toList());
    }

    private List<InfoOrderItemDTO> getOrderItemInfoList(Order order) {
        return order
                .getItems()
                .stream()
                .map(item -> new InfoOrderItemDTO(item.getProduct().getDescription(), item.getProduct().getPrice(), item.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Integer id, @RequestBody @Valid UpdateStatusOrderDTO newStatus) {
        final var entity = this.orderRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order with :id not found", id)));
        if (StatusOrder.CANCELLED.name().equals(newStatus.getNewStatus())) {
            entity.setStatus(StatusOrder.CANCELLED);
            this.orderRepository.save(entity);
        } else {
            throw new NotFoundException("Status not found");
        }
    }
}
