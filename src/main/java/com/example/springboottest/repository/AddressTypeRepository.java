package com.example.springboottest.repository;

import com.example.springboottest.model.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType, Long> {


    Optional<AddressType> findByNameIgnoreCase(String addressType);
}
