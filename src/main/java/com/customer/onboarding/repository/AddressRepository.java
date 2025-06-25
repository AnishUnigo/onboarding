package com.customer.onboarding.repository;

import com.customer.onboarding.model.Address;
import com.customer.onboarding.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findById(Long userId);

}
