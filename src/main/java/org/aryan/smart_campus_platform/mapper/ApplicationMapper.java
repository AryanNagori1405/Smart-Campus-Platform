package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.response.ApplicationResponse;
import org.aryan.smart_campus_platform.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {



    public ApplicationResponse toResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();

        response.setId(application.getId());
        response.setStudentId(application.getStudent().getId());
        response.setJobId(application.getJob().getId());
        response.setStatus(application.getStatus());
        response.setAppliedAt(application.getAppliedAt());

        return response;
    }
}
