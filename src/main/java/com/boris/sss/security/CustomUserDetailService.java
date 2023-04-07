package com.boris.sss.security;

import com.boris.sss.domain.CustomerModel;
import com.boris.sss.repo.CustomerRepository;
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
        System.out.println("loadUserByUsername - entering, s is " + s);
        final CustomerModel customerModel = customerRepository.findByEmail(s);
        if(customerModel == null){throw new UsernameNotFoundException("email " + s + " was not found");}
        UserDetails user = User.withUsername(customerModel.getEmail()).password(customerModel.getPassword()).authorities("USER").build();
        return user;
    }
}
