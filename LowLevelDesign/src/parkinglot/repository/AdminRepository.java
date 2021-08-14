package parkinglot.repository;

import parkinglot.service.AdminService;

import java.util.*;

public class AdminRepository {
    public static Map<String, AdminService> adminMap = new HashMap<>();
    public static List<AdminService> adminServices = new ArrayList<>();

    private static volatile AdminRepository INSTANCE = null;

    private AdminRepository(){
    }

    public static AdminRepository getInstance(){
        if(INSTANCE == null){
            synchronized (ParkingLotRepository.class) {
                if(INSTANCE == null){
                    INSTANCE = new AdminRepository();
                }
            }
        }
        return INSTANCE;
    }

    public AdminService addAdmin(AdminService adminService) {
        adminMap.putIfAbsent(adminService.getId(), adminService);
        adminServices.add(adminService);
        return adminService;
    }

    public AdminService getAdminByEmail(String email) {
        Optional<AdminService> admin =
                adminServices.stream().filter(adm -> adm.getEmail().equalsIgnoreCase(email)).findFirst();

        return admin.isPresent() ? admin.get() : null;
    }

    public AdminService getAdminById(String id) {
        return adminMap.get(id);
    }
}
