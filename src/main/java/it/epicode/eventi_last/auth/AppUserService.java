package it.epicode.eventi_last.auth;

import it.epicode.eventi_last.user.Utente;
import it.epicode.eventi_last.user.UtenteRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private UtenteRepo utenteRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Utente registerUser(String username, String password, Set<Role> roles) {
        if (utenteRepo.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }

        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPassword(passwordEncoder.encode(password));
        utente.setRoles(roles);

        return utenteRepo.save(utente);
    }

    public Optional<Utente> findByUsername(String username) {
        return utenteRepo.findByUsername(username);
    }

    public String authenticateUser(String username, String password)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }

    public Utente loadUserByUsername(String username)  {
        return utenteRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));
    }
}
