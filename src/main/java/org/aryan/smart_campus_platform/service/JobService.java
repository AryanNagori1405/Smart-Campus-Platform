package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.JobRequest;
import org.aryan.smart_campus_platform.dto.response.JobResponse;
import org.aryan.smart_campus_platform.entity.Company;
import org.aryan.smart_campus_platform.entity.Job;
import org.aryan.smart_campus_platform.exception.CompanyNotFoundException;
import org.aryan.smart_campus_platform.exception.JobNotFoundException;
import org.aryan.smart_campus_platform.mapper.JobMapper;
import org.aryan.smart_campus_platform.repository.CompanyRepository;
import org.aryan.smart_campus_platform.repository.JobRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository,
                      CompanyRepository companyRepository,
                      JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.jobMapper = jobMapper;
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toResponse)
                .toList();
    }

    public JobResponse getJobById(int id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(
                        "Job not found with id: " + id
                ));

        return jobMapper.toResponse(job);
    }

    public List<JobResponse> getJobsByCompany(int companyId) {

        companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(
                        "Company not found with id: " + companyId
                ));

        return jobRepository.findByCompanyId(companyId)
                .stream()
                .map(jobMapper::toResponse)
                .toList();
    }

    public JobResponse createJob(
            int companyId,
            JobRequest jobRequest) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(
                        "Company not found with id: " + companyId
                ));

        Job job = jobMapper.toEntity(jobRequest);
        job.setCompany(company);
        Job savedJob = jobRepository.save(job);

        return jobMapper.toResponse(savedJob);
    }

    public JobResponse updateJob(int id, JobRequest jobRequest) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(
                        "Job not found with id: " + id
                ));

        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setLocation(jobRequest.getLocation());
        job.setEmploymentType(jobRequest.getEmploymentType());
        job.setSalary(jobRequest.getSalary());

        Job updatedJob = jobRepository.save(job);

        return jobMapper.toResponse(updatedJob);
    }

    public void deleteJob(int id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(
                        "Job not found with id: " + id
                ));

        jobRepository.delete(job);
    }
}
