package com.blackmania.facialreconition.rest.dto;



import java.io.Serializable;

public class RegisterDTO implements Serializable {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
