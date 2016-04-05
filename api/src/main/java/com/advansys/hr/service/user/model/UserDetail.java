package com.advansys.hr.service.user.model;

import java.io.Serializable;
import java.util.Collection;

public class UserDetail implements Serializable {

    private String email;
    private String firstName;
    private String lastName;


    public UserDetail() { }

    public UserDetail(
            String email,
            String firstName,
            String lastName) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
