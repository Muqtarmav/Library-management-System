package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.Role;
import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.datas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class  CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("user not found with username" + userName));

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getEmail(), mapRolesToAuthorities(user.getRoleList()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roleList) {
        return roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }




}

//    @Override
//    public UserDetails loadUserByUsername(String firstNameOrEmail) throws UsernameNotFoundException {
//        User user = userRepository.findByFirstNameOrEmail((firstNameOrEmail), firstNameOrEmail).orElseThrow(() -> new UsernameNotFoundException("user not found with this firstName or email" + firstNameOrEmail));
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getFirstName(), mapRolesToAuthorities(user.getRoleList()));
//    }
//        private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roleList){
//            return roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        }
//
//    }
//}
//
//}