package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.AccountRequest;
import org.aryan.smart_campus_platform.dto.response.AccountResponse;
import org.aryan.smart_campus_platform.entity.Account;
import org.aryan.smart_campus_platform.exception.EmailAlreadyExistsException;
import org.aryan.smart_campus_platform.mapper.AccountMapper;
import org.aryan.smart_campus_platform.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;


    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
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
}
