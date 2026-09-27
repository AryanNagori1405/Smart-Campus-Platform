package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.request.AccountRequest;
import org.aryan.smart_campus_platform.dto.response.AccountResponse;
import org.aryan.smart_campus_platform.service.AccountService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public AccountResponse register(
            @RequestBody @Valid AccountRequest request) {
        return accountService.register(request);
    }
}
