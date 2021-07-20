package ir.maktab.hw1.view;

import ir.maktab.hw1.controller.CategoryController;
import ir.maktab.hw1.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CategoryMenu extends Menu {

    private static String[] getCategories() {
        CategoryController categoryController = new CategoryController();
        String[] categories = new String[categoryController.getAllCategories().size() + 1];
        for (int i = 0; i < categoryController.getAllCategories().size(); i++) {
            categories[i] = categoryController.getAllCategories().get(i).getTitle();
        }
        categories[categoryController.getAllCategories().size()] = "Add new category";
        return categories;
    }


    public CategoryMenu() {
        super(getCategories());
    }


    public Category chooseCategory(Scanner scanner) {
        showMenu();
        ArrayList<Category> categories = new CategoryController().getAllCategories();
        System.out.println("Choose a category: ");
        String chosenItem = scanner.nextLine();
        if (Integer.parseInt(chosenItem) == getCategories().length) {
            createNewCategory(scanner);
        } else {
            return categories.get(Integer.parseInt(chosenItem) - 1);
        }
        chooseCategory(scanner);
        return null;
    }

    public void createNewCategory(Scanner scanner) {
        Category category = new Category();
        System.out.println("Please enter title: ");
        category.setTitle(scanner.nextLine());
        System.out.println("Please enter description: ");
        category.setDescription(scanner.nextLine());
        CategoryController categoryController = new CategoryController();
        try {
            categoryController.addCategory(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Added successfully!");
        new CategoryMenu().chooseCategory(scanner);
    }
}
