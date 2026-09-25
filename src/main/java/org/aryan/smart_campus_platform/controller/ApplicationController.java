package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.request.ApplicationRequest;
import org.aryan.smart_campus_platform.dto.request.ApplicationStatusRequest;
import org.aryan.smart_campus_platform.dto.response.ApplicationResponse;
import org.aryan.smart_campus_platform.service.ApplicationService;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ApplicationResponse apply(
            @RequestBody @Valid ApplicationRequest request) {

        return service.apply(request);
    }

    @PutMapping("/{applicationId}/status")
    public ApplicationResponse updateStatus(
            @PathVariable int applicationId,
            @RequestBody @Valid ApplicationStatusRequest request) {

        return service.updateStatus(applicationId, request);
    }
}