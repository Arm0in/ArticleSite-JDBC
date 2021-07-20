package ir.maktab.hw1.view;

import ir.maktab.hw1.model.Article;
import ir.maktab.hw1.model.User;
import ir.maktab.hw1.controller.ArticleController;
import ir.maktab.hw1.controller.UserController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu() {
        super(new String[]{
                "Login",
                "Register",
                "View Published Articles",
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
                viewPublished();
                run(scanner);
                break;
            case "4":
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
        UserController userController = new UserController();
        User user = userController.getUser(userName, passWord);
        System.out.println(user);
        if(user != null){
            UserMenu userMenu = new UserMenu();
            userMenu.run(scanner, user);
        }else {
            System.out.println("Wrong!");
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
        UserController userController = new UserController();
        userController.addUser(user);
        System.out.println(user);
        System.out.println("Registered successfully!");
    }

    private void viewPublished() {
        ArticleController articleController = new ArticleController();
        for (Article article :
                articleController.getPublishedArticles()) {
            System.out.println(article);
        }
    }



}
