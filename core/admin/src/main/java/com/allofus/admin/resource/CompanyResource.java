package com.allofus.admin.resource;

import com.allofus.admin.adapters.CompanyMapper;
import com.allofus.admin.adapters.Mapper;
import com.allofus.admin.model.Company;
import com.allofus.admin.repository.CompanyRepository;
import com.allofus.admin.service.Service;
import com.allofus.commons.ws.request.CompanyRequest;
import com.allofus.commons.ws.response.CompanyResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyResource extends Resource<CompanyRequest, CompanyResponse, Service<Company, CompanyRepository>,CompanyMapper> {

    public CompanyResource(CompanyMapper mapper, Service service) {
        super(mapper, service);
    }
}
