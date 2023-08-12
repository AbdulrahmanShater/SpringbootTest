package com.example.springboottest.config;

import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.model.Address;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
        TypeMap<Address, AddressDTO> typeMap = modelMapper.createTypeMap(Address.class,AddressDTO.class);
        typeMap.addMappings(mapper -> {
            mapper.map(address ->  address.getAddressType().getName(), AddressDTO::setAddressType);
            mapper.map(address ->  address.getCity().getName(), AddressDTO::setCity);
            mapper.map(address ->  address.getCity().getCountry().getName(), AddressDTO::setCountry);
            mapper.map(Address::getAddressLine, AddressDTO::setAddressLine);
        });
        return modelMapper;
    }
}
