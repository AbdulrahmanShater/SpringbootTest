package com.example.springboottest.repository;

import com.example.springboottest.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCountry_idAndName(Long id, String city);
}
