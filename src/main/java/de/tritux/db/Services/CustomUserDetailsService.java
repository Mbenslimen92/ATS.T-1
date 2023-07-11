package de.tritux.db.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String Nom) throws UsernameNotFoundException {
        User user = repository.findByNom(Nom);
        return new org.springframework.security.core.userdetails.User(user.getNom(), user.getPassword(), new ArrayList<>());
    }
}
