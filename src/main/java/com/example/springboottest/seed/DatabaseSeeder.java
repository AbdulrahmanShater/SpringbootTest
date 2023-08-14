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

        AddressType homeType = new AddressType();
        homeType.setName("Home");
        addressTypeRepository.save(homeType);

        AddressType workType = new AddressType();
        workType.setName("Work");
        addressTypeRepository.save(workType);


        AddressType pob = new AddressType();
        pob.setName("POB");
        addressTypeRepository.save(pob);


        Country uae = new Country();
        uae.setName("UAE");
        countryRepository.save(uae);

        Country syria = new Country();
        syria.setName("Syria");
        countryRepository.save(syria);

        City abuDhabi = new City();
        abuDhabi.setName("Abu Dhabi");
        abuDhabi.setCountry(uae);
        cityRepository.save(abuDhabi);


        City dubai = new City();
        dubai.setName("Dubai");
        dubai.setCountry(uae);
        cityRepository.save(dubai);

        City aleppo = new City();
        aleppo.setName("Aleppo");
        aleppo.setCountry(syria);
        cityRepository.save(aleppo);


    }
}