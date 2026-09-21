package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.CompanyRequest;
import org.aryan.smart_campus_platform.dto.response.CompanyResponse;
import org.aryan.smart_campus_platform.entity.Company;
import org.aryan.smart_campus_platform.exception.CompanyNotFoundException;
import org.aryan.smart_campus_platform.mapper.CompanyMapper;
import org.aryan.smart_campus_platform.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponse)
                .toList();
    }

    public CompanyResponse getCompanyById(int id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(
                        "Company not found with id: " + id
                ));

        return companyMapper.toResponse(company);
    }

    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        Company company = companyMapper.toEntity(companyRequest);
        companyRepository.save(company);
        return companyMapper.toResponse(company);
    }

    public CompanyResponse updateCompany(int id, CompanyRequest companyRequest) {

        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(
                        "Company not found with id: " + id
                ));

        existingCompany.setName(companyRequest.getName());
        existingCompany.setEmail(companyRequest.getEmail());
        existingCompany.setDescription(companyRequest.getDescription());
        existingCompany.setWebsite(companyRequest.getWebsite());
        existingCompany.setLocation(companyRequest.getLocation());

        companyRepository.save(existingCompany);

        return companyMapper.toResponse(existingCompany);
    }

    public void deleteCompany(int id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(
                        "Company not found with id: " + id
                ));

        companyRepository.delete(company);
    }
}
