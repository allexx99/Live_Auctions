package com.example.Live.Auctions.model;

import com.example.Live.Auctions.dto.AdminDTO;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity*/
public final class Admin extends User {

    // singleton
    private static Admin adminInstance = null;

    private String firstName = "Alexx";
    private String lastName = "Ndrew";

    private Admin() {}

    public static Admin getAdminInstance() {
        if(adminInstance == null) {
            adminInstance = new Admin();
        }
        return adminInstance;
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

    public AdminDTO convertToDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO("","");
        adminDTO.setId(admin.getId());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        return adminDTO;
    }


    /*@NotBlank
    @Column
    private String firstName;

    @NotBlank
    @Column
    private String lastName;

    public Admin(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }*/

}
