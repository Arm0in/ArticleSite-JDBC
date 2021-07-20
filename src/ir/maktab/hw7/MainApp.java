package ir.maktab.hw7;

import ir.maktab.hw7.view.MainMenu;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();
        mainMenu.run(scanner);
    }

}
