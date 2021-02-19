package com.allofus.admin.service;

import com.allofus.admin.model.Company;
import com.allofus.admin.repository.CompanyRepository;

@org.springframework.stereotype.Service
public class CompanyService extends Service<Company, CompanyRepository>{
    public CompanyService(CompanyRepository repositoy) {
        super(repositoy);
    }
}
