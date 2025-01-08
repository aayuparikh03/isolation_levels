package com.example.controller;

import com.example.service.NonRepeatableReadService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/non-repeatable-read")
public class ProductController {
   private final NonRepeatableReadService nonRepeatableReadService;

    public ProductController(NonRepeatableReadService nonRepeatableReadService) {
        this.nonRepeatableReadService = nonRepeatableReadService;
    }
    @GetMapping("/read/{id}")
    public String readStockTwice(@PathVariable Long id)
    {
        return nonRepeatableReadService.readStockTwice(id);
    }
    @PostMapping("/update/{productId}/{newStock}")
    public void updateStock(@PathVariable Long productId, @PathVariable int newStock) {
        nonRepeatableReadService.updateStock(productId, newStock);
    }

}
