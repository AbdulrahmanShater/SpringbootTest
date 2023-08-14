package com.example.springboottest.seed;

import com.example.springboottest.model.AddressType;
import com.example.springboottest.model.City;
import com.example.springboottest.model.Country;
import com.example.springboottest.repository.AddressTypeRepository;
import com.example.springboottest.repository.CityRepository;
import com.example.springboottest.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    final
    AddressTypeRepository addressTypeRepository;
    final
    CityRepository cityRepository;
    final
    CountryRepository countryRepository;

    public DatabaseSeeder(AddressTypeRepository addressTypeRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.addressTypeRepository = addressTypeRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) {

        AddressType addressType = new AddressType();
        addressType.setName("Home");
        addressTypeRepository.save(addressType);

        Country country = new Country();
        country.setName("UAE");
        countryRepository.save(country);

        City city = new City();
        city.setName("Abu Dhabi");
        city.setCountry(country);
        cityRepository.save(city);
    }
}