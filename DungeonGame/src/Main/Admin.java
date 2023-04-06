package Main;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Admin {
    public static void admin() { // implement "GameLogic.clearConsole();" to make appearance better?
        int choice = 0;

        do {
            System.out.println("***********************");
            System.out.println(" 1 - Delete file");
            System.out.println(" 2 - Edit/Save file");
            System.out.println(" 3 - Load file");
            System.out.println(" 4 - Log out");
            System.out.println("***********************");
            System.out.print("Select function: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println();
                    GameLogic.clearConsole();
                    System.out.println("Choose file- ");
                    System.out.println("Player Login Info File (1)");
                    System.out.println("Demo Login Info File (2)");
                    System.out.print("File: ");

                    int file = input.nextInt();

                    switch (file) {
                        case 1 -> {
                            System.out.println();
                            System.out.println("File: 'P.txt'");
                            deletePlayerLoginFile();
                            System.out.println();
                        }

                        case 2 -> {
                            System.out.println();
                            System.out.println("File: 'D.txt'");
                            deleteDemoLoginFile();
                            System.out.println();
                        }
                    }

                }

                case 2 -> {
                    System.out.println("Edit/Save files");
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

    // Method 1 - delete player login file
    public static void deletePlayerLoginFile() {
        // File playFile = new File ("P.txt");
        Path path = Paths.get("P.txt");

        try {

            Files.deleteIfExists(path);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Method 2 - delete demo login file
    public static void deleteDemoLoginFile() {
        Path path = Paths.get("D.txt");

        try {

            Files.deleteIfExists(path);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Method 3 - load player login info
    public static void loadPlayerLoginFile() {
        File playFile = new File("P.txt");

        if (playFile.exists()) {
            try {
                Scanner read = new Scanner(playFile);
                while (read.hasNextLine()) {
                    System.out.println(read.nextLine());
                }
                read.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not Exist.");
        }
    }


    // Method 2 - load demo login info
    public static void loadDemoLoginFile() {
        File demoFile = new File("D.txt");

        if (demoFile.exists()) {
            try {
                Scanner read = new Scanner(demoFile);
                while (read.hasNextLine()) {
                    System.out.println(read.nextLine());
                }
                read.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not Exist.");
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

    public static void writePlayerProfileFile() throws IOException {
        //File playProfileFile = new File("playProfile.csv");
        //FileWriter fw = new FileWriter(playProfileFile);
        //fw.append("Name");
        //fw.append(",");
        //fw.append("HP");
        //fw.append(",");
        //fw.append("Health Potions");
        //fw.append("\n");
       //String =

        //List<String> rows = Arrays.asList(GameLogic.player.name, GameLogic.player);

    }
}


