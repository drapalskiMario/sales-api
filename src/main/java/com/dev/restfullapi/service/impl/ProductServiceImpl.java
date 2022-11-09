package com.dev.restfullapi.service.impl;

import com.dev.restfullapi.domain.entity.Product;
import com.dev.restfullapi.domain.repository.ProductRepository;
import com.dev.restfullapi.exception.NotFoundException;
import com.dev.restfullapi.rest.dto.input.ProductDTO;
import com.dev.restfullapi.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static String NOT_FOUND_MESSAGE = "Product not found!";
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(ProductDTO productDTO) {
        var entity = new Product();
        this.mapperToEntity(productDTO, entity);
        this.productRepository.save(entity);
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return this
                .productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    @Override
    public void update(Integer id, ProductDTO productDTO) {
        var entity = this
                .productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        this.mapperToEntity(productDTO, entity);
        this.productRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        var entity = this
                .productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        this.productRepository.delete(entity);
    }

    private void mapperToEntity(ProductDTO productDTO, Product product) {
        BeanUtils.copyProperties(productDTO, product, "id");
    }
}
