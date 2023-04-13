package main.java.Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import java.io.*;
import java.util.*;

import static main.java.Main.GameLogic.language;
import static main.java.Main.GameLogic.translator;

public class Admin {
    public static void admin() throws IOException, DeepLException, InterruptedException { // implement "GameLogic.clearConsole();" to make appearance better?
        int choice;

        do {
            System.out.println("***********************");
            TextResult result = translator.translateText(" 1 - Delete file", null, language);
            System.out.println(result.getText());
            TextResult result1 = translator.translateText(" 2 - Save file", null, language);
            System.out.println(result1.getText());
            TextResult result2 = translator.translateText(" 3 - Load file", null, language);
            System.out.println(result2.getText());
            TextResult result3 = translator.translateText(" 4 - Log out", null, language);
            System.out.println(result3.getText());
            System.out.println("***********************");
            TextResult result4 = translator.translateText("Select function: ", null, language);
            System.out.println(result4.getText());

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    TextResult result5 = translator.translateText("Delete files", null, language);
                    System.out.println(result5.getText());
                    System.out.println();

                }

                case 2 -> {
                    TextResult result6 = translator.translateText("Save files", null, language);
                    System.out.println(result6.getText());
                    System.out.println();

                }

                case 3 -> {
                    System.out.println();
                    GameLogic.clearConsole();
                    TextResult result7 = translator.translateText("Choose file- ", null, language);
                    System.out.println(result7.getText());
                    TextResult result8 = translator.translateText("Player Login Info File (1)", null, language);
                    System.out.println(result8.getText());
                    TextResult result9 = translator.translateText("Demo Login Info File (2)", null, language);
                    System.out.println(result9.getText());
                    TextResult result10 = translator.translateText("Admin Login Info File (3)", null, language);
                    System.out.println(result10.getText());
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
        main.java.Main.Login.LoginMenu();
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
