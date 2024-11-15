package com.thinne.backend.repositories;

import com.thinne.backend.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company save(Company company);
    Company findByCompName(String compName);
    Company findCompanyByEmail(String email);
}
