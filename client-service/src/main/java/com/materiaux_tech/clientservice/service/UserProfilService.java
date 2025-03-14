package com.materiaux_tech.clientservice.service;

import com.materiaux_tech.clientservice.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserProfilService {
    UserProfile createUserProfil(UserProfile userProfile);
    List<UserProfile> getAllProfil();
    Optional<UserProfile> getProfileById(Long profileId);
    void deleteProfileById(Long profileId);
    Optional<UserProfile> updateProfil(Long profileId, UserProfile userProfile);
}
