package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.request.JobRequest;
import org.aryan.smart_campus_platform.dto.response.JobResponse;
import org.aryan.smart_campus_platform.service.JobService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import java.util.List;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<JobResponse> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/jobs/{id}")
    public JobResponse getJob(@PathVariable int id) {
        return jobService.getJobById(id);
    }

    @GetMapping("/companies/{companyId}/jobs")
    public List<JobResponse> getJobsByCompany(@PathVariable int companyId) {
        return jobService.getJobsByCompany(companyId);
    }

    @PostMapping("/companies/{companyId}/jobs")
    public JobResponse createJob(
            @PathVariable int companyId,
            @RequestBody @Valid JobRequest jobRequest) {

        return jobService.createJob(companyId, jobRequest);
    }

    @PutMapping("/jobs/{id}")
    public JobResponse updateJob(
            @PathVariable int id,
            @RequestBody @Valid JobRequest jobRequest) {

        return jobService.updateJob(id, jobRequest);
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
    }
}
