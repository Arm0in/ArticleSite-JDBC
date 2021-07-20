package ir.maktab.hw7.view;

import ir.maktab.hw7.repository.ArticleRepository;
import ir.maktab.hw7.repository.UserRepository;
import ir.maktab.hw7.domain.Article;
import ir.maktab.hw7.domain.Category;
import ir.maktab.hw7.domain.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class UserMenu extends Menu {

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
                MainMenu mainMenu = new MainMenu();
                mainMenu.run(scanner);
                return;
            default:
                System.out.println("Wrong! (user menu)");
                run(scanner, currentUser);
        }
    }

    private void viewArticles(User currentUser) {
        ArticleRepository articleRepository = new ArticleRepository();
        for (Article article :
                articleRepository.getUserArticles(currentUser)) {
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
        System.out.println("Please set a price: ");
        editedArticle.setPrice(new BigDecimal(scanner.nextLine()));
        new ArticleRepository().update(currentUser, articleId, editedArticle);
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
        System.out.println("Please set a price: ");
        article.setPrice(new BigDecimal(scanner.nextLine()));
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
        new ArticleRepository().save(currentUser, article);
        System.out.println("Added successfully!");
        run(scanner, currentUser);
    }

    private void changePublish(User currentUser, Scanner scanner) {
        System.out.println("Enter article Id: ");
        int articleId = Integer.parseInt(scanner.nextLine());
        Article article = new ArticleRepository().getArticleById(currentUser, articleId);
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
        new ArticleRepository().update(currentUser, articleId, article);
    }

    private void changePassword(User currentUser, Scanner scanner) {
        System.out.println("Enter your new password");
        String newPassword = scanner.nextLine();
        currentUser.setPassword(newPassword);
        new UserRepository().update(currentUser);
        System.out.println("Password Updated successfully!");
        run(scanner, currentUser);
    }
}
