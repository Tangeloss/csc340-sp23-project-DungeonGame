package Users;

import Main.GameLogic;
import Main.Login;

import java.io.*;
import java.util.*;
public class Admin {
    public static void admin() { // implement "GameLogic.clearConsole();" to make appearance better?
        int choice;

        do {
            System.out.println("***********************");
            System.out.println(" 1 - Delete file");
            System.out.println(" 2 - Save file");
            System.out.println(" 3 - Load file");
            System.out.println(" 4 - Log out");
            System.out.println("***********************");
            System.out.print("Select function: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Delete files");
                    System.out.println();

                }

                case 2 -> {
                    System.out.println("Save files");
                    System.out.println();

                }

                case 3 -> {
                    System.out.println();
                    GameLogic.clearConsole();
                    System.out.println("Choose file- ");
                    System.out.println("Player Login Info File (1)");
                    System.out.println("Demo Login Info File (2)");
                    System.out.println("Admin Login Info File (3)");
                    System.out.print("File: ");

                    int file = input.nextInt();

                    switch (file) {
                        case 1 -> {
                            System.out.println();
                            System.out.println("File: 'P.txt'");
                            loadPlayerLoginFile();
                            System.out.println();
                        }

                        case 2 -> {
                            System.out.println();
                            System.out.println("File: 'D.txt'");
                            loadDemoLoginFile();
                            System.out.println();
                        }

                        case 3 -> {
                            System.out.println();
                            System.out.println("File: 'A.txt'");
                            loadAdminLoginFile();
                            System.out.println();
                        }
                    }
                }
            }
        } while (choice != 4);
        GameLogic.clearConsole();
        Login.LoginMenu();
        System.out.println(); //how to drag to bottom like craig has game play do
    }

    // Method 1 - load player login info
    public static void loadPlayerLoginFile() {
        try {
            Scanner read = new Scanner(new File("P.txt"));

            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method 2 - load demo login info
    public static void loadDemoLoginFile() {
        try {
            Scanner read = new Scanner(new File("D.txt"));

            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method 3 - load admin login info
    public static void loadAdminLoginFile() {
        try {
            Scanner read = new Scanner(new File("A.txt"));

            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
