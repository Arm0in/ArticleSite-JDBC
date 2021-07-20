package ir.maktab.hw7.view;

import ir.maktab.hw7.repository.TagRepository;
import ir.maktab.hw7.domain.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TagMenu extends Menu {

    private static String[] getTags() {
        TagRepository tagRepository = new TagRepository();
        String[] tags = new String[tagRepository.getAllTags().size() + 2];
        for (int i = 0; i < tagRepository.getAllTags().size(); i++) {
            tags[i] = tagRepository.getAllTags().get(i).getTitle();
        }
        tags[tagRepository.getAllTags().size()] = "Create new tag";
        tags[tagRepository.getAllTags().size() + 1] = "Add tags";
        return tags;
    }

    public TagMenu() {
        super(getTags());
    }

    public ArrayList<Tag> chooseTags(Scanner scanner) {
        showMenu();
        ArrayList<Tag> tags = new TagRepository().getAllTags();
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
            new TagRepository().addTag(tag);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Added successfully!");
        new TagMenu().chooseTags(scanner);
    }

}
