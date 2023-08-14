package com.example.springboottest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AddressType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String name;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "address_type_id")
    private List<Address> addresses;


    @Override
    public String toString() {
        return "AddressType{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
