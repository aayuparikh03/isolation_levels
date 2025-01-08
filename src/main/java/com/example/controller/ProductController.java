package com.example.controller;

import com.example.entity.Product;
import com.example.service.PhantomReadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phantom-read")
public class ProductController {
    private final PhantomReadService phantomReadService;

    public ProductController(PhantomReadService phantomReadService) {
        this.phantomReadService = phantomReadService;
    }

    @GetMapping("/query")
    public List<Product> queryProductsTwice() {
        return phantomReadService.queryProductsTwice();
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
        phantomReadService.addProduct(product);
    }
}
