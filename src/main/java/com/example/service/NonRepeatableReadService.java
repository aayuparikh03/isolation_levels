package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NonRepeatableReadService {

    private final ProductRepository productRepository;

    public NonRepeatableReadService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public String readStockTwice(Long id)
    {
        Product product=productRepository.findById(id).orElseThrow();
        int stockFirstRead= product.getStock();

        try{
            Thread.sleep(3);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        Product product1=productRepository.findById(id).orElseThrow();
        int stockSecondRead= product.getStock();
        System.out.println("First Read: " + stockFirstRead + ", Second Read: " + stockSecondRead);  return stockFirstRead +" "+stockSecondRead;
    }

    @Transactional
    public void updateStock(Long productId, int newStock) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setStock(newStock);
        productRepository.save(product);
    }
}
