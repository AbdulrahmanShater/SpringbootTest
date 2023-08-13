package com.example.springboottest.repository;

import com.example.springboottest.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    int deleteByIdAndCustomer_id(Long addressId, Long id);
}
