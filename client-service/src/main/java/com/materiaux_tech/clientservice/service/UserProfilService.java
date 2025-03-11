package com.materiaux_tech.clientservice.service;

import com.materiaux_tech.clientservice.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserProfilService {
    UserProfile createUserProfil(UserProfile userProfile);
    List<UserProfile> getAllUserProfil();
    Optional<UserProfile> getUserProfileById(Long profileId);
    void deleteUserProfileById(Long profileId);
    Optional<UserProfile> updateUserProfil(Long profileId, UserProfile userProfile);
}
