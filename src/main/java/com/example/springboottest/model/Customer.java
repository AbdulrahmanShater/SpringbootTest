package com.example.springboottest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column()
    private String phoneNumber;
    @Column()
    private String email;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private Set<Address> addresses;


    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + ", addresses=" + addresses + '}';
    }
}
