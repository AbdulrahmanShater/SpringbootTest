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
    @JoinColumn(name = "city_id")
    private Set<Address> addresses;

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name='" + name + '\'' + ", country=" + country +

                '}';
    }
}
