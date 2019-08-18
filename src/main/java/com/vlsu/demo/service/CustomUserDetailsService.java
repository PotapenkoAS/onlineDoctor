package com.vlsu.demo.service;


import com.vlsu.demo.model.CustomUserDetails;
import com.vlsu.demo.model.entity.User;
import com.vlsu.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository ur;

    @Autowired
    public CustomUserDetailsService(UserRepository ur) {
        this.ur = ur;
    }


    @Override// метод автоматически вызывается при логине springSecurity и сохраняет полченный UserDetails объект в сессии пользователя
    public UserDetails loadUserByUsername(String username) {
        User user;
        try {
            if ((user = ur.findByLogin(username)) != null) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
                return new CustomUserDetails(user.getLogin(), "{noop}" + user.getPassword(), true, true, true
                        , true,authorities , user.getUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UsernameNotFoundException(username);
    }
}

