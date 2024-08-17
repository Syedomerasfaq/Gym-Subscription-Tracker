package com.example.gymtracker.Service.Impl;

import com.example.gymtracker.Domain.entities.OwnerDetails;
import com.example.gymtracker.Domain.entities.Owners;
import com.example.gymtracker.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    OwnerRepository ownerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Owners> ownersOptional = ownerRepository.findByUserName(username);
        if (ownersOptional.isPresent())
            return new OwnerDetails(ownersOptional.get());
        else
            throw new UsernameNotFoundException("Not found: " + username);
    }
}
