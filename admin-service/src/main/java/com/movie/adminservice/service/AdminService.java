package com.movie.adminservice.service;

import com.movie.adminservice.entity.Admin;
import com.movie.adminservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() { return adminRepository.findAll(); }
    public Optional<Admin> getAdminById(Long id) { return adminRepository.findById(id); }

    public Admin createAdmin(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        admin.setCreatedAt(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin updated) {
        return adminRepository.findById(id).map(a -> {
            a.setFullName(updated.getFullName());
            a.setRole(updated.getRole());
            a.setActive(updated.isActive());
            return adminRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Admin not found: " + id));
    }

    public void deleteAdmin(Long id) { adminRepository.deleteById(id); }

    public Admin login(String email, String password) {
        return adminRepository.findByEmail(email).map(admin -> {
            if (admin.getPassword().equals(password) && admin.isActive()) {
                admin.setLastLogin(LocalDateTime.now());
                return adminRepository.save(admin);
            }
            throw new RuntimeException("Invalid credentials");
        }).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    // Data seeder - creates default admin on startup
    public void seedDefaultAdmin() {
        if (!adminRepository.existsByEmail("admin@cinema.com")) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setEmail("admin@cinema.com");
            admin.setPassword("admin123");
            admin.setFullName("System Administrator");
            admin.setRole("SUPER_ADMIN");
            adminRepository.save(admin);
            System.out.println("Default admin created: admin@cinema.com / admin123");
        }
    }
}
