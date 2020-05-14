package com.blackmania.facialreconition.data.tabellen;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    private UUID id;

    private String name;

    public User() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
