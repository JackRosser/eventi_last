package it.epicode.eventi_last.auth;

import it.epicode.eventi_last.user.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<Utente> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            appUserService.registerUser("admin", "adminpwd", Set.of(Role.ROLE_ADMIN));
        }

        // Creazione dell'utente user se non esiste
        Optional<Utente> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            appUserService.registerUser("user", "userpwd", Set.of(Role.ROLE_USER));
        }

        // Creazione dell'utente organizzatore
        Optional<Utente> organizer = appUserService.findByUsername("organizer");
        if (organizer.isEmpty()) {
            appUserService.registerUser("organizer", "organpwd", Set.of(Role.ROLE_ORGANIZER));
        }
    }
}
