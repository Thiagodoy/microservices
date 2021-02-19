package com.allofus.admin.adapters;


import com.allofus.admin.model.Company;
import com.allofus.admin.model.User;
import com.allofus.commons.ws.request.CompanyRequest;
import com.allofus.commons.ws.request.UserRequest;
import com.allofus.commons.ws.response.CompanyResponse;
import com.allofus.commons.ws.response.UserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper extends com.allofus.admin.adapters.Mapper<User, UserRequest, UserResponse> {
    UserMapper INSTANCE = (UserMapper) Mappers.getMapper(UserMapper.class);
}
