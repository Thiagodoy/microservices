package com.allofus.admin.adapters;


import com.allofus.admin.model.Product;
import com.allofus.commons.ws.request.ProductRequest;
import com.allofus.commons.ws.response.ProductResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProductMapper extends com.allofus.admin.adapters.Mapper<Product, ProductRequest, ProductResponse> {
    ProductMapper INSTANCE = (ProductMapper) Mappers.getMapper(ProductMapper.class);
}
