package com.thinne.frontend.models;

import com.thinne.backend.models.Company;
import com.thinne.backend.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyModel {
    @Autowired
    private CompanyRepository companyRepository;
    public Company findCompanyByEmail(String email){
        return companyRepository.findCompanyByEmail(email);
    }
}
