package com.allofus.admin.adapters;


import com.allofus.admin.model.Company;
import com.allofus.commons.ws.request.CompanyRequest;
import com.allofus.commons.ws.response.CompanyResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CompanyMapper extends com.allofus.admin.adapters.Mapper<Company, CompanyRequest, CompanyResponse> {
    CompanyMapper INSTANCE = (CompanyMapper) Mappers.getMapper(CompanyMapper.class);
}
