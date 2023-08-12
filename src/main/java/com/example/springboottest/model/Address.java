package com.example.kalvadtest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @Column(length = 100)
    private String addressLine;

    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_type_id")
    private AddressType addressType;



    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city=" + city +
                ", address_Type=" + addressType +
                ", addressLine='" + addressLine + '\'' +
                '}';
    }
}
