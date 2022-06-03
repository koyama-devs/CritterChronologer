package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 500)
    private String notes;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL) //DETACH, MERGE, PERSIST, REFRESH, REMOVE
    private List<Pet> pets = new ArrayList<>();
}
