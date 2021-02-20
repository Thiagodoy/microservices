package com.allofus.admin.resource;

import com.allofus.admin.adapters.CompanyMapper;
import com.allofus.admin.adapters.ProductMapper;
import com.allofus.admin.service.CompanyService;
import com.allofus.admin.service.ProductService;
import com.allofus.commons.ws.request.CompanyRequest;
import com.allofus.commons.ws.request.ProductRequest;
import com.allofus.commons.ws.response.CompanyResponse;
import com.allofus.commons.ws.response.ProductResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductResource extends Resource<ProductRequest, ProductResponse, ProductService, ProductMapper> {
    public ProductResource(ProductMapper mapper, ProductService service) {
        super(mapper, service);
    }
}
