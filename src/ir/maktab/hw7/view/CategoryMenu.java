package ir.maktab.hw7.view;

import ir.maktab.hw7.repository.CategoryRepository;
import ir.maktab.hw7.domain.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryMenu extends Menu {

    private static String[] getCategories() {
        CategoryRepository categoryRepository = new CategoryRepository();
        String[] categories = new String[categoryRepository.getAllCategories().size() + 1];
        for (int i = 0; i < categoryRepository.getAllCategories().size(); i++) {
            categories[i] = categoryRepository.getAllCategories().get(i).getTitle();
        }
        categories[categoryRepository.getAllCategories().size()] = "Add new category";
        return categories;
    }


    public CategoryMenu() {
        super(getCategories());
    }


    public Category chooseCategory(Scanner scanner) {
        showMenu();
        ArrayList<Category> categories = new CategoryRepository().getAllCategories();
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
        CategoryRepository categoryRepository = new CategoryRepository();
        try {
            categoryRepository.addCategory(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Added successfully!");
        new CategoryMenu().chooseCategory(scanner);
    }
}
