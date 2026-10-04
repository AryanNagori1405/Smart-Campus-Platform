package org.aryan.smart_campus_platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String testAdminAccess() {
        return "Admin access granted";
    }

    @GetMapping("/management-test")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECRUITER')")
    public String managementAccess() {
        return "Management access granted";
    }
}