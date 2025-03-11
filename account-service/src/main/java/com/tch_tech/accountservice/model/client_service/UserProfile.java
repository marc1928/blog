package com.tch_tech.accountservice.model.client_service;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class UserProfile {

    private Long profileId;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;


    private Address address;

    private List<VerificationDocument> verificationDocuments;

}
