package com.example.controller;

import com.example.service.DirtyReadService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dirty-read")
public class ProductController {
    private final DirtyReadService dirtyReadService;

    public ProductController(DirtyReadService dirtyReadService) {
        this.dirtyReadService = dirtyReadService;
    }
    @PutMapping("/update/{id}/{stock}")
    public void updateStock(@PathVariable Long id,@PathVariable int stock)
    {
        dirtyReadService.updateStocks(id,stock);
    }
    @GetMapping("/read/{id}")
    public int readStocks(@PathVariable Long id)
    {
        return dirtyReadService.readStockUnCommitted(id);

    }
}
