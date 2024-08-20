package com.example.Live.Auctions.service.impl;

//import com.example.Live.Auctions.dao.AdminDao;
import com.example.Live.Auctions.model.Admin;
import com.example.Live.Auctions.service.AdminServiceInterface;

//@Service
public class AdminService implements AdminServiceInterface {

    // singleton




   /* private final AdminDao adminDao;

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public void addAdmin(Admin admin) {
        adminDao.save(admin);
    }*/



    // singleton
    public Admin getAdmin() {
        return Admin.getAdminInstance();
    }




    /*public void updateAdmin(long id, Admin admin) {
        Admin adminToUpdate = adminDao.findById(id).get();
        adminToUpdate.setFirstName(admin.getFirstName());
        adminToUpdate.setLastName(admin.getLastName());
        adminToUpdate.setUsername(admin.getUsername());
        adminToUpdate.setPassword(admin.getPassword());
        adminDao.save(adminToUpdate);
    }

    public void deleteAdmin(long id) {
        Admin admin = adminDao.findById(id).get();
        adminDao.delete(admin);
    }*/
}
