package com.example.Live.Auctions.api;

import com.example.Live.Auctions.model.Admin;
import com.example.Live.Auctions.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
//@CrossOrigin
public class AdminApiController {

    // singleton
    @Autowired
    private AdminService adminService;

    //Admin currentAdmin = Admin.getAdminInstance();

    @GetMapping
    public Admin getCurrentAdmin() {
        return adminService.getAdmin();
    }




    /*private final AdminService adminService;

    @Autowired
    public AdminApiController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Save admin", description = "Add a new admin in the DB")
    @PostMapping(value = "/saveAdmin")
    public String saveAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return "Saved...";
    }

    @Operation(summary = "Get admin", description = "Get the admin from DB")
    @GetMapping(value = "/admin")
    public List<Admin> getAdmin() {
        return adminService.getAdmin();
    }

    @Operation(summary = "Update admin", description = "Update the details about the admin by id")
    @PostMapping(value = "/updateAdmin/{id}")
    public String updateAdmin(@PathVariable long id,@RequestBody Admin admin) {
        adminService.updateAdmin(id, admin);
        return "Admin updated...";
    }

    @Operation(summary = "Delete admin", description = "Delete the admin by id")
    @DeleteMapping(value = "/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable long id) {
        adminService.deleteAdmin(id);
        return "Delete admin with id = " + id;
    }*/
}
