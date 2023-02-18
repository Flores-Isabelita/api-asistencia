package com.ib.asistencia.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
