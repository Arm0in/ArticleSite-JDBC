package ir.maktab.hw7.view;

import ir.maktab.hw7.domain.Admin;
import ir.maktab.hw7.domain.User;
import ir.maktab.hw7.repository.AdminRepository;

import java.util.Scanner;

public class AdminMenu extends Menu {

    public AdminMenu() {
        super(new String[]{
                "Change user status",
                "Logout"
        });
    }

    public void run(Scanner scanner, Admin currentAdmin) {
        showMenu();
        String chosenItem = scanner.nextLine();
        switch (chosenItem) {
            case "1":
                changeUserStatus(currentAdmin, scanner);
                break;
            case "2":
                currentAdmin = null;
                new MainMenu().run(scanner);
            default:
                System.out.println("Wrong!");
                run(scanner, currentAdmin);
        }
    }

    private void changeUserStatus(Admin currentAdmin, Scanner scanner) {
        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();
        User user = new AdminRepository().getUserByName(userName);
        System.out.println(user);
        if (user.isStatus()) {
            System.out.println("do you want to block this user? (y/n)");
            char blockOrNot = scanner.nextLine().charAt(0);
            if (blockOrNot == 'y') {
                user.setStatus(false);
            } else if (blockOrNot == 'n') {
                System.out.println("Nothing changed!");
                run(scanner, currentAdmin);
            }
        } else {
            System.out.println("do you want to activate this user? (y/n)");
            char activeOrNot = scanner.nextLine().charAt(0);
            if (activeOrNot == 'y') {
                user.setStatus(true);
            } else if (activeOrNot == 'n') {
                System.out.println("Nothing changed!");
                run(scanner, currentAdmin);
            }
        }
        new AdminRepository().updateUserStatus(user);
    }
}
