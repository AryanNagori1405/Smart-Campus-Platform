package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.request.CompanyRequest;
import org.aryan.smart_campus_platform.dto.response.CompanyResponse;
import org.aryan.smart_campus_platform.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();

        company.setName(companyRequest.getName());
        company.setEmail(companyRequest.getEmail());
        company.setDescription(companyRequest.getDescription());
        company.setWebsite(companyRequest.getWebsite());
        company.setLocation(companyRequest.getLocation());

        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setId(company.getId());
        companyResponse.setName(company.getName());
        companyResponse.setEmail(company.getEmail());
        companyResponse.setDescription(company.getDescription());
        companyResponse.setWebsite(company.getWebsite());
        companyResponse.setLocation(company.getLocation());

        return companyResponse;
    }
}
