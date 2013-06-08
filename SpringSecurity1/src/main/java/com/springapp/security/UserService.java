package com.springapp.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @PreAuthorize("hasRole('admin')")
    public Collection<? extends GrantedAuthority> getAuthorities(UserDetails userDetails) {
        return userDetails.getAuthorities();
    }
}
