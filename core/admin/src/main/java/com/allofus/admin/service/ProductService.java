package com.allofus.admin.service;

import com.allofus.admin.model.Company;
import com.allofus.admin.model.Product;
import com.allofus.admin.repository.CompanyRepository;
import com.allofus.admin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class ProductService extends Service<Product, ProductRepository>{
    public ProductService(@Qualifier("productRepository") ProductRepository productRepository ) {
        super(productRepository);
    }
}
