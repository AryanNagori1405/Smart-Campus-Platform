package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.request.CompanyRequest;
import org.aryan.smart_campus_platform.dto.response.CompanyResponse;
import org.aryan.smart_campus_platform.service.CompanyService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompany(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public CompanyResponse createCompany(
            @RequestBody @Valid CompanyRequest companyRequest) {
        return companyService.createCompany(companyRequest);
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(
            @PathVariable int id,
            @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(id, companyRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }
}
