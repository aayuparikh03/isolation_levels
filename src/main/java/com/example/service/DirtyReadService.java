package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirtyReadService {

    private final ProductRepository productRepository;

    public DirtyReadService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateStocks(Long prodId,int newStock)
    {
        Product product=productRepository.findById(prodId).orElseThrow();
        product.setStock(newStock);
        productRepository.save(product);

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public int readStockUnCommitted(Long prodId)
    {
        Product product=productRepository.findById(prodId).orElseThrow();
        return product.getStock();
    }
}
