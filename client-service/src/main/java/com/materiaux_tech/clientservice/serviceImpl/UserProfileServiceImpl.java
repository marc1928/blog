package com.materiaux_tech.clientservice.serviceImpl;

import com.materiaux_tech.clientservice.entity.UserProfile;
import com.materiaux_tech.clientservice.repository.UserProfilRepository;
import com.materiaux_tech.clientservice.service.UserProfilService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserProfileServiceImpl implements UserProfilService {
    private final UserProfilRepository userProfilRepository;

    public UserProfileServiceImpl(UserProfilRepository userProfilRepository) {
        this.userProfilRepository = userProfilRepository;
    }

// ##########################################################################################

    @Override
    public UserProfile createUserProfil(UserProfile userProfile) {
        if (userProfilRepository.findByEmail(userProfile.getEmail()).isPresent()){
            throw new RuntimeException("User already exists");
        }else {
            return userProfilRepository.save(userProfile);
        }
    }

    @Override
    public List<UserProfile> getAllUserProfil() {
        return userProfilRepository.findAll();
    }

    @Override
    public Optional<UserProfile> getUserProfileById(Long profileId) {
        return userProfilRepository.findUserProfileById(profileId);
    }

    @Override
    public void deleteUserProfileById(Long profileId) {
        userProfilRepository.deleteById(profileId);
    }

    @Override
    public Optional<UserProfile> updateUserProfil(Long profileId, UserProfile userProfile) {
        return userProfilRepository.findById(profileId).map(
                existingUserProfil -> {
                    existingUserProfil.setFirstName(userProfile.getFirstName());
                    existingUserProfil.setLastName(userProfile.getLastName());
                    existingUserProfil.setEmail(userProfile.getEmail());
                    existingUserProfil.setPhoneNumber(userProfile.getPhoneNumber());
                    existingUserProfil.setAddress(userProfile.getAddress());
                    return userProfilRepository.save(existingUserProfil);
                }
        );
    }
}
