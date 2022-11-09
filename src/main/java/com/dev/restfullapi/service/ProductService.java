package com.dev.restfullapi.service;

import com.dev.restfullapi.domain.entity.Product;
import com.dev.restfullapi.rest.dto.input.ProductDTO;

import java.util.List;

public interface ProductService {
    void create(ProductDTO productDTO);

    List<Product> getAll();

    Product getById(Integer id);

    void update(Integer id, ProductDTO productDTO);

    void delete(Integer id);
}
