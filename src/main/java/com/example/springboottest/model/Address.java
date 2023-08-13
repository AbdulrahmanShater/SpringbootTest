package com.example.springboottest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uk_customer_address_line",columnNames = {"customer_id", "addressLine"}))
public class Address {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    private City city;

    @Column(length = 100,nullable = false)
    private String addressLine;

    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_type_id",nullable = false)
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
