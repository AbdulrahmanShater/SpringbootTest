package com.example.springboottest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_phoneNumber", columnNames = {"phoneNumber"}),
        @UniqueConstraint(name = "uk_email", columnNames = {"email"})
})
public class Customer {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;

    public void AddAddress(Address address) {
        addresses.add(address);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + ", addresses=" + addresses + '}';
    }
}
