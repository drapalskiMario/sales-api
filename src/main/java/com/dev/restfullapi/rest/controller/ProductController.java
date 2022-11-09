package com.dev.restfullapi.rest.controller;

import com.dev.restfullapi.domain.entity.Product;
import com.dev.restfullapi.rest.dto.input.ProductDTO;
import com.dev.restfullapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ProductDTO productDTO) {
        this.productService.create(productDTO);
    }

    @GetMapping
    public List<Product> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
       return this.productService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid ProductDTO productDTO) {
       this.productService.update(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id) {
       this.productService.delete(id);
    }

}
