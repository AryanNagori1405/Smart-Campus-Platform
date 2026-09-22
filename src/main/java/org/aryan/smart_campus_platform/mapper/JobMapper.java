package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.request.JobRequest;
import org.aryan.smart_campus_platform.dto.response.JobResponse;
import org.aryan.smart_campus_platform.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public Job toEntity(JobRequest jobRequest) {
        Job job = new Job();

        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setLocation(jobRequest.getLocation());
        job.setEmploymentType(jobRequest.getEmploymentType());
        job.setSalary(jobRequest.getSalary());

        return job;
    }

    public JobResponse toResponse(Job job) {
        JobResponse jobResponse = new JobResponse();

        jobResponse.setId(job.getId());
        jobResponse.setTitle(job.getTitle());
        jobResponse.setDescription(job.getDescription());
        jobResponse.setLocation(job.getLocation());
        jobResponse.setEmploymentType(job.getEmploymentType());
        jobResponse.setSalary(job.getSalary());
        jobResponse.setCompanyId(job.getCompany().getId());

        return jobResponse;
    }
}
