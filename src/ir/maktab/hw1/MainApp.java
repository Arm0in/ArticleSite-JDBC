package ir.maktab.hw1;

import ir.maktab.hw1.view.MainMenu;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();
        mainMenu.run(scanner);
    }

}
