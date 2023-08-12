package com.example.kalvadtest.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "cityId")
    private Set<Address> addresses;

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name='" + name + '\'' + ", country=" + country +

                '}';
    }
}
