package com.materiaux_tech.clientservice.controller;

import com.materiaux_tech.clientservice.entity.UserProfile;
import com.materiaux_tech.clientservice.service.UserProfilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/user-profiles") // Versionner l'API
public class UserProfilController {
    private final UserProfilService userProfilService;


    public UserProfilController(UserProfilService userProfilService) {
        this.userProfilService = userProfilService;
    }

// ########################################################################################################
    // Création d'un profil utilisateur
    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile){
        log.info("Création d'un nouveau profil utilisateur: {}", userProfile);
        UserProfile userProfile1 = userProfilService.createUserProfil(userProfile);
        return ResponseEntity.ok(userProfile1);
    }
    // Récupérer un profil utilisateur par ID
    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long profileId){
        log.info("Récupération du profil utilisateur avec ID: {}", profileId);
        Optional<UserProfile> userProfile = userProfilService.getUserProfileById(profileId);

        return userProfile
                .map(ResponseEntity::ok) // Si présent, renvoie 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Sinon, 404 Not Found
    }

    // Supprimer un profil utilisateur
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteUserProfileById(@PathVariable Long profileId){
        log.info("Suppression du profil utilisateur avec ID: {}", profileId);
        userProfilService.deleteUserProfileById(profileId);
        return ResponseEntity.ok().build();
    }

    // Récupérer tous les profils utilisateur
    @GetMapping
    public ResponseEntity<Iterable<UserProfile>> getAllUserProfil(){
        log.info("Récupération de tous les profils utilisateur.");
        return ResponseEntity.ok(userProfilService.getAllUserProfil());
    }

    // Mettre à jour un profil utilisateur
    @PutMapping("/{profileId}")
    public ResponseEntity<UserProfile> updateUserProfil(@PathVariable Long profileId,@RequestBody UserProfile userProfile){
        log.info("Mise à jour du profil utilisateur ID: {}", profileId);
        UserProfile updatedProfile = userProfilService.updateUserProfil(profileId, userProfile)
                .orElseThrow(() -> new RuntimeException("UserProfile not found during update."));
        return ResponseEntity.ok(updatedProfile);
    }
}
