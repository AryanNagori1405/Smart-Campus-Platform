package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.AccountRequest;
import org.aryan.smart_campus_platform.dto.request.LoginRequest;
import org.aryan.smart_campus_platform.dto.response.AccountResponse;
import org.aryan.smart_campus_platform.dto.response.LoginResponse;
import org.aryan.smart_campus_platform.entity.Account;
import org.aryan.smart_campus_platform.exception.AccountNotFoundException;
import org.aryan.smart_campus_platform.exception.EmailAlreadyExistsException;
import org.aryan.smart_campus_platform.mapper.AccountMapper;
import org.aryan.smart_campus_platform.repository.AccountRepository;

import org.aryan.smart_campus_platform.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AccountService(
            AccountRepository accountRepository,
            AccountMapper accountMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AccountResponse register(AccountRequest request) {
        boolean emailExists  = accountRepository.existsByEmail(request.getEmail());

        if (emailExists ) {
            throw new EmailAlreadyExistsException(
                    "Account already exists with email: " + request.getEmail()
            );
        }

        Account account = new Account();

        account.setEmail(request.getEmail());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(request.getRole());

        Account savedAccount = accountRepository.save(account);

        return accountMapper.toResponse(savedAccount);
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        String token = jwtService.generateToken(request.getEmail());

        LoginResponse response = new LoginResponse();

        response.setToken(token);

        return response;
    }
}
