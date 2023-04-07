package com.boris.sss.repo;

import com.boris.sss.domain.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    CustomerModel findByEmail(String email);
}
