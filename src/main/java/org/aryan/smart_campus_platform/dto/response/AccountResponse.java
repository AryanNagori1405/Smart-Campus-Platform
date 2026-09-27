package org.aryan.smart_campus_platform.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.aryan.smart_campus_platform.entity.Role;

@JsonPropertyOrder({"id", "email", "role"})
public class AccountResponse {

    private int id;
    private String email;
    private Role role;

    public AccountResponse() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
