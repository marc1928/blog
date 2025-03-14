package com.materiaux_tech.clientservice.repository;

import com.materiaux_tech.clientservice.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfilRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByProfileId(Long profileId);
    Optional<UserProfile> findByEmail(String email);
}
