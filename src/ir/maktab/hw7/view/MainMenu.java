package ir.maktab.hw7.view;

import ir.maktab.hw7.domain.Admin;
import ir.maktab.hw7.repository.AdminRepository;
import ir.maktab.hw7.repository.ArticleRepository;
import ir.maktab.hw7.repository.UserRepository;
import ir.maktab.hw7.domain.Article;
import ir.maktab.hw7.domain.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu() {
        super(new String[]{
                "Login",
                "Register",
                "View Published Articles",
                "Admin login",
                "Exit"
        });
    }

    public void run(Scanner scanner) {
        showMenu();
        String chosenItem = scanner.nextLine();
        switch (chosenItem) {
            case "1":
                login(scanner);
                break;
            case "2":
                register(scanner);
                run(scanner);
                break;
            case "3":
                viewPublished(scanner);
                run(scanner);
                break;
            case "4":
                adminLogin(scanner);
                break;
            case "5":
                break;
            default:
                System.out.println("Wrong!");
                run(scanner);
        }
    }

    private void login(Scanner scanner) {
        System.out.println("Please enter your Username: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter your Password: ");
        String passWord = scanner.nextLine();
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getUser(userName, passWord);
        System.out.println(user);
        if (user != null) {
            UserMenu userMenu = new UserMenu();
            userMenu.run(scanner, user);
        } else {
            login(scanner);
        }

    }

    private void register(Scanner scanner) {
        User user = new User();
        System.out.println("Please enter username: ");
        user.setUserName(scanner.nextLine());
        System.out.println("Please enter nationalcode: ");
        String nationalCode = scanner.nextLine();
        user.setNationalCode(nationalCode);
        user.setPassword(nationalCode);
        System.out.println("Please enter birthday yyyy-mm-dd: ");
        String str = scanner.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setBirthday(LocalDate.parse(str, dtf));
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user);
        System.out.println(user);
        System.out.println("Registered successfully!");
    }

    private void viewPublished(Scanner scanner) {
        ArrayList<Article> articles = new ArticleRepository().getPublishedArticles();
        ArrayList<Article> freeArticles = new ArrayList<Article>();
        ArrayList<Article> paidArticles = new ArrayList<Article>();
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getPrice().compareTo(BigDecimal.ZERO) == 0) {
                freeArticles.add(articles.get(i));
            } else {
                paidArticles.add(articles.get(i));
            }
        }
        System.out.println("1. Free articles \n2. Paid articles");
        String chosenItem = scanner.nextLine();
        switch (chosenItem) {
            case "1":
                for (Article a :
                        freeArticles) {
                    System.out.println(a);
                }
                break;
            case "2":
                for (Article a :
                        paidArticles) {
                    System.out.println(a);
                }
                break;
            default:
                System.out.println("wrong!");
                run(scanner);
        }
    }

    private void adminLogin(Scanner scanner) {
        System.out.println("Please enter your Username: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter your Password: ");
        String password = scanner.nextLine();
        AdminRepository adminRepository = new AdminRepository();
        Admin admin = adminRepository.getAdmin(userName, password);
        System.out.println("welcome " + admin.getUserName());
        if (admin != null) {
            new AdminMenu().run(scanner, admin);
        } else {
            System.out.println("Wrong!");
            adminLogin(scanner);
        }
    }

}
