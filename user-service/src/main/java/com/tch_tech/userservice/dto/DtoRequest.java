package com.tch_tech.userservice.dto;

import lombok.*;

@Data
public class DtoRequest {
    private  String username;
    private String roleName;
    private String privilegeName;
}
