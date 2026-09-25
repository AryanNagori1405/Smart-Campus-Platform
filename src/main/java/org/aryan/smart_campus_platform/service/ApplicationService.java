package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.ApplicationRequest;
import org.aryan.smart_campus_platform.dto.request.ApplicationStatusRequest;
import org.aryan.smart_campus_platform.dto.response.ApplicationResponse;
import org.aryan.smart_campus_platform.entity.Application;
import org.aryan.smart_campus_platform.entity.ApplicationStatus;
import org.aryan.smart_campus_platform.entity.Job;
import org.aryan.smart_campus_platform.entity.Student;
import org.aryan.smart_campus_platform.exception.*;
import org.aryan.smart_campus_platform.mapper.ApplicationMapper;
import org.aryan.smart_campus_platform.repository.ApplicationRepository;
import org.aryan.smart_campus_platform.repository.JobRepository;
import org.aryan.smart_campus_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            JobRepository jobRepository,
            ApplicationMapper applicationMapper) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
        this.applicationMapper = applicationMapper;
    }

    public ApplicationResponse apply(ApplicationRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + request.getStudentId()
                ));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new JobNotFoundException(
                        "Job not found with id: " + request.getJobId()
                ));

        boolean alreadyApplied =
                applicationRepository.existsByStudentIdAndJobId(
                        student.getId(),
                        job.getId()
                );

        if (alreadyApplied) {
            throw new ApplicationAlreadyExistsException(
                    "Student has already applied to this job"
            );
        }

        Application application = new Application();

        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setAppliedAt(LocalDateTime.now());

        Application savedApplication =
                applicationRepository.save(application);

        return applicationMapper.toResponse(savedApplication);
    }

    private boolean isValidTransition(
            ApplicationStatus current,
            ApplicationStatus next) {

        return switch (current) {
            case APPLIED -> next == ApplicationStatus.SHORTLISTED
                    || next == ApplicationStatus.REJECTED;
            case SHORTLISTED -> next == ApplicationStatus.SELECTED
                    || next == ApplicationStatus.REJECTED;
            default -> false;
        };
    }

    public ApplicationResponse updateStatus(
            int applicationId,
            ApplicationStatusRequest request) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        "Application not found with id: " + applicationId
                ));

        ApplicationStatus currentStatus = application.getStatus();
        ApplicationStatus nextStatus = request.getStatus();

        if (!isValidTransition(currentStatus, nextStatus)) {
            throw new InvalidApplicationStatusTransitionException(
                    "Cannot change application status from "
                            + currentStatus
                            + " to "
                            + nextStatus
            );
        }

        application.setStatus(nextStatus);

        Application updatedApplication =
                applicationRepository.save(application);

        return applicationMapper.toResponse(updatedApplication);
    }
}