package ir.maktab.hw1.view;

import ir.maktab.hw1.controller.ArticleController;
import ir.maktab.hw1.controller.UserController;
import ir.maktab.hw1.model.Article;
import ir.maktab.hw1.model.Category;
import ir.maktab.hw1.model.User;

import java.time.LocalDate;
import java.util.Scanner;

public class UserMenu extends Menu {
    ArticleController articleController = new ArticleController();

    public UserMenu() {
        super(new String[]{
                "View your articles",
                "Edit your articles",
                "Add new article",
                "Publish or Unpublish your article",
                "Change your password",
                "Logout"
        });
    }

    public void run(Scanner scanner, User currentUser) {
        showMenu();
        String chosenItem = scanner.nextLine();
        switch (chosenItem) {
            case "1":
                viewArticles(currentUser);
                run(scanner, currentUser);
                break;
            case "2":
                editArticle(currentUser, scanner);
                break;
            case "3":
                addArticle(currentUser, scanner);
                break;
            case "4":
                changePublish(currentUser, scanner);
                break;
            case "5":
                changePassword(currentUser, scanner);
                break;
            case "6":
                currentUser = null;
                new MainMenu().run(scanner);
            default:
                System.out.println("Wrong!");
                run(scanner, currentUser);
        }
    }

    private void viewArticles(User currentUser) {
        for (Article article :
                articleController.getUserArticles(currentUser)) {
            System.out.println(article);
        }
    }

    private void editArticle(User currentUser, Scanner scanner) {
        Article editedArticle = new Article();
        System.out.println("Enter article Id: ");
        int articleId = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter new title: ");
        editedArticle.setTitle(scanner.nextLine());
        System.out.println("Please enter new brief: ");
        editedArticle.setBrief(scanner.nextLine());
        System.out.println("Please enter new content: ");
        editedArticle.setContent(scanner.nextLine());
        new ArticleController().updateArticle(currentUser, articleId, editedArticle);
        System.out.println("Edited successfully!");
        run(scanner, currentUser);
    }

    private void addArticle(User currentUser, Scanner scanner) {
        Article article = new Article();
        CategoryMenu categoryMenu = new CategoryMenu();
        Category category = categoryMenu.chooseCategory(scanner);
        System.out.println("Please enter title: ");
        article.setTitle(scanner.nextLine());
        System.out.println("Please enter brief: ");
        article.setBrief(scanner.nextLine());
        System.out.println("Please enter content: ");
        article.setContent(scanner.nextLine());
        article.setCreateDate(LocalDate.now());
        System.out.println("Do you want to publish the article now? (y/n)");
        char publishOrNot = scanner.nextLine().charAt(0);
        if (publishOrNot == 'y') {
            article.setPublished(true);
            article.setPublishDate(LocalDate.now());
        } else if (publishOrNot == 'n') {
            article.setPublished(false);
            article.setPublishDate(null);
        }
        article.setLastUpdateDate(LocalDate.now());
        article.setUser(currentUser);
        article.setCategory(category);
        article.setTags(new TagMenu().chooseTags(scanner));
        System.out.println(article);
        new ArticleController().addArticle(currentUser, article);
        System.out.println("Added successfully!");
        run(scanner, currentUser);
    }

    private void changePublish(User currentUser, Scanner scanner) {
        System.out.println("Enter article Id: ");
        int articleId = Integer.parseInt(scanner.nextLine());
        Article article = new ArticleController().getArticleById(currentUser, articleId);
        System.out.println(article);
        if (article.isPublished()) {
            System.out.println("do you want to unpublish your article? (y/n)");
            char publishOrNot = scanner.nextLine().charAt(0);
            if (publishOrNot == 'y') {
                article.setPublished(false);
                article.setPublishDate(null);
            } else if (publishOrNot == 'n') {
                System.out.println("Nothing changed!");
                run(scanner, currentUser);
            }
        } else {
            System.out.println("do you want to publish your article? (y/n)");
            char publishOrNot = scanner.nextLine().charAt(0);
            if (publishOrNot == 'y') {
                article.setPublished(true);
                article.setPublishDate(LocalDate.now());
            } else if (publishOrNot == 'n') {
                System.out.println("Nothing changed!");
                run(scanner, currentUser);
            }
        }
        new ArticleController().updateArticle(currentUser, articleId, article);
    }

    private void changePassword(User currentUser, Scanner scanner) {
        System.out.println("Enter your new password");
        String newPassword = scanner.nextLine();
        new UserController().updatePass(currentUser, newPassword);
        System.out.println("Password Updated successfully!");
        run(scanner, currentUser);
    }
}
