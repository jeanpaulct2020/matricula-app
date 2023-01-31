package com.jeanct.matriculaapp.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Clase seguridad 4
@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String jwtToken;
}
