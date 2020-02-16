package thang2;

import DAO.UserDAO;
import Service.PermissionService;
import Service.UserService;
import java.util.Scanner;
import model.User;

public class MainClass {

    public static String loginUser = "";
    public static String idUserLogged = "";
    private PermissionService permissionService = new PermissionService();

    public void Menu(int n) {
        System.out.println("1: them tai khoan");
        System.out.println("2: Sua tai khoan");
        System.out.println("3: Xoa tai khoan");
        System.out.println("4: Dang xuat");
        n = new Scanner(System.in).nextInt();
        switch (n) {
            case 1: {
                insertUser();

            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
            case 4: {
                permissionService.logout();
                break;
            }
        }
    }

    private void insertUser() {
        UserService insert = new UserService();
        User u = new User();
        if (permissionService.getRolesLoggedInUser().equalsIgnoreCase("user")) {
            System.out.println("khong co quyen");
        } else {
            u.nhapUser();
            insert.insertUser(u);
        }
    }

    public boolean login() {
        System.out.println("Nhap vao ten: ");
        return permissionService.login(new Scanner(System.in).nextLine());
    }

    public static void main(String[] args) {
        MainClass running = new MainClass();
        int n = 0;
        do {
            if (MainClass.loginUser.equals("")) {
                if (!running.login()) {
                    continue;
                } else {

                }
            }
            running.Menu(n);

        } while (n != 4);

    }
}
