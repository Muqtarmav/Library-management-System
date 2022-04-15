package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetail extends User implements UserDetails {

    public CustomUserDetail(Long id, String firstName, String email, String username, String age) {
        super(id, firstName, email, username, age);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleList()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE" + role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return  super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
