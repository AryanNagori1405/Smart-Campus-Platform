package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.response.AccountResponse;
import org.aryan.smart_campus_platform.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse toResponse(Account account) {

        AccountResponse response = new AccountResponse();

        response.setId(account.getId());
        response.setEmail(account.getEmail());
        response.setRole(account.getRole());

        return response;
    }
}
