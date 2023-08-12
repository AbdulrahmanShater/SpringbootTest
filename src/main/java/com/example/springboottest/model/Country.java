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
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 60)
    private String name;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "country_id")
    private Set<City> cities;

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name='" + name + '\'' +

                '}';
    }
}
