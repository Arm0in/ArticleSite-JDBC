package ir.maktab.hw1.view;

import ir.maktab.hw1.controller.CategoryController;
import ir.maktab.hw1.controller.TagController;
import ir.maktab.hw1.model.Category;
import ir.maktab.hw1.model.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TagMenu extends Menu {

    private static String[] getTags() {
        TagController tagController = new TagController();
        String[] tags = new String[tagController.getAllTags().size() + 2];
        for (int i = 0; i < tagController.getAllTags().size(); i++) {
            tags[i] = tagController.getAllTags().get(i).getTitle();
        }
        tags[tagController.getAllTags().size()] = "Create new tag";
        tags[tagController.getAllTags().size() + 1] = "Add tags";
        return tags;
    }

    public TagMenu() {
        super(getTags());
    }

    public ArrayList<Tag> chooseTags(Scanner scanner) {
        showMenu();
        ArrayList<Tag> tags = new TagController().getAllTags();
        ArrayList<Tag> chosenTags = new ArrayList<Tag>();
        System.out.println("Choose tags: ");
        String chosenItem = scanner.nextLine();
        if (Integer.parseInt(chosenItem) == getTags().length - 1) {
            createNewTag(scanner);
        } else if (Integer.parseInt(chosenItem) == getTags().length) {
            return chosenTags;
        } else {
            chosenTags.add(tags.get(Integer.parseInt(chosenItem) - 1));
        }
        chooseTags(scanner);
        return null;
    }

    public void createNewTag(Scanner scanner) {
        Tag tag = new Tag();
        System.out.println("Please enter title: ");
        tag.setTitle(scanner.nextLine());
        try {
            new TagController().addTag(tag);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Added successfully!");
        new TagMenu().chooseTags(scanner);
    }

}
