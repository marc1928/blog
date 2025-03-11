package com.tch_tech.accountservice.dto;

import lombok.*;

@Data
public class DtoRequest {
    private  String username;
    private String roleName;
    private String privilegeName;
}
