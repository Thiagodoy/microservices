package com.allofus.admin.service;

import com.allofus.admin.model.Company;
import com.allofus.admin.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class CompanyService extends Service<Company, CompanyRepository>{
    public CompanyService(@Qualifier("companyRepository") CompanyRepository companyRepository) {
        super(companyRepository);
    }
}
