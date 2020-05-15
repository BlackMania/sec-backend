package com.blackmania.facialreconition.data.tabellen;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    private UUID id;

    private String name;

    private String file_location;

    public User() {
    }

    public User(String name, String file_location) {
        this.name = name;
        this.file_location = file_location;
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

    @Column
    public String getFile_location() {
        return file_location;
    }

    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }
}
