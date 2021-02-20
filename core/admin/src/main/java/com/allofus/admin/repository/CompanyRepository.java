package com.allofus.admin.repository;

import com.allofus.admin.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company,Long>, JpaSpecificationExecutor<Company> {
}
