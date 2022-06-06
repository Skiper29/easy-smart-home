package com.lpnu.easysmarthome.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN,EMPLOYEE;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}