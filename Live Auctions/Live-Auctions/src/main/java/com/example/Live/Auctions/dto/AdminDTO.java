package com.example.Live.Auctions.dto;

public class AdminDTO extends UserDTO {

    private String firstName;
    private String lastName;

    public AdminDTO(String username, String password) {
        super(username, password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
