package com.javadevjournal.security;

import com.javadevjournal.domain.CustomerModel;
import com.javadevjournal.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final CustomerModel customerModel = customerRepository.findByEmail(s);
        if(customerModel == null){throw  new UsernameNotFoundException("email " + s + " was not found");}
        UserDetails user = User.withUsername(customerModel.getEmail()).password(customerModel.getPassword()).authorities("USER").build();
        return user;
    }
}
