package Main;

import java.util.*;
import java.io.*;
public class Login {
    public static void LoginMenu() { // implement "GameLogic.clearConsole();" to make appearance better?
        System.out.println("******** Login ********");
        System.out.println(" 1 - Player");
        System.out.println(" 2 - Demo-Reviewer");
        System.out.println(" 3 - Admin");
        System.out.println(" 4 - View reviews");
        System.out.println("***********************");
        System.out.print("Choice Login Type: ");

        Scanner input = new Scanner(System.in);
        int login = input.nextInt();

        String playUser = "";
        String playPass = "";
        String demoUser = "";
        String demoPass = "";
        String adminUser = "";
        String adminPass = "";

        // Menu Selections
        switch(login) {
            case 1 -> {
                GameLogic.clearConsole();
                System.out.print("Do you have an account? Yes(1) No(2): ");
                int choice = input.nextInt();
                if (choice == 1) {
                    Login.playerLoginInfo(playUser, playPass);

                } else {
                    Login.printPlayerLoginFile();
                    System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                    System.out.println();
                    Login.playerLoginInfo(playUser, playPass);
                }
            }
            case 2 -> {
                GameLogic.clearConsole();
                System.out.print("Do you have an account? Yes(1) No(2): ");
                int choice1 = input.nextInt();
                if (choice1 == 1) {
                    Login.demoLoginInfo(demoUser, demoPass);

                } else {
                    Login.printDemoLoginFile();
                    System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                    System.out.println();
                    Login.demoLoginInfo(demoUser, demoPass);

                }
            }
            case 3 -> {
                GameLogic.clearConsole();
                Login.adminLoginInfo(adminUser, adminPass);
            }
            case 4 -> {
                DemoReviewer.loadReviews();
            }
        }

    }

    // Method 1- Player login in
    public static void playerLoginInfo(String username, String password) {

        Scanner info = new Scanner (System.in);

        System.out.print("Enter Username: ");
        String playUser = info.nextLine();

        System.out.print("Enter Password: ");
        String playPass = info.nextLine();

        try {
            Scanner read = new Scanner(new File("P.txt"));
            boolean isFound = false;

            while (read.hasNextLine()) {
                username = read.nextLine();
                password = read.nextLine();

                if (playUser.equals(username) && playPass.equals(password)) {
                    isFound = true;
                }
            }
            read.close();

            if (isFound) {
                System.out.println("Time to enter the world of mazes, monsters, and magic!");
                GameLogic.startGame();
                System.out.println();

            } else {
                Scanner input = new Scanner(System.in);

                System.out.println();
                System.out.println("Oh no! Your spell was not in the Great Spell-book!");
                System.out.print("Do you want try again (1) or create a new entrance spell to start a new adventure (2)?: ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1-> {
                        GameLogic.clearConsole(); // Change clearing spot?
                        System.out.println();
                        playerLoginInfo(username, password);
                        GameLogic.startGame();
                    }

                    case 2 -> {
                        GameLogic.clearConsole(); // Change clearing spot?
                        System.out.println();
                        printPlayerLoginFile();
                        System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                        System.out.println();
                        playerLoginInfo(username, password);
                        GameLogic.startGame();
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method 2- Printing new Player username and password to file method
    public static void printPlayerLoginFile() {
        java.io.File playerFile = new java.io.File("P.txt");
        try {
            if(!playerFile.exists()) {
                System.out.println("New file created.");
                playerFile.createNewFile();
            }
            java.io.PrintWriter output = new java.io.PrintWriter(new FileWriter(playerFile, true));

            // Write formatted output to the file
            Scanner info = new Scanner (System.in);
            System.out.print("Create Username: ");
            String newUsername = info.nextLine();

            System.out.print("Create Password: ");
            String newPassword = info.nextLine();

            output.println(newUsername);
            output.println(newPassword);

            output.close();
        } catch (IOException ex) {
            System.out.println("Error Opening Output file");
        }
    }

    // Method 3- Demo Reviewer login method
    public static void demoLoginInfo(String username, String password) {

        Scanner info = new Scanner (System.in);

        System.out.print("Enter Username: ");
        String demoUser = info.nextLine();

        System.out.print("Enter Password: ");
        String demoPass = info.nextLine();

        try {
            Scanner read = new Scanner(new File("D.txt"));
            boolean isFound = false;

            while (read.hasNextLine()) {
                username = read.nextLine();
                password = read.nextLine();

                if (demoUser.equals(username) && demoPass.equals(password)) {
                    isFound = true;
                }
            }
            read.close();

            if (isFound) {
                System.out.println("Time to enter the world of mazes, monsters, and magic!");
                System.out.println();
                GameLogic.startGame();
                System.out.println();
                GameLogic.canReview();

            } else {
                Scanner input = new Scanner(System.in);

                System.out.println();
                System.out.println("Oh no! Your spell was not in the Great Spell-book!");
                System.out.print("Do you want try again (1) or create a new entrance spell to start a new adventure (2)?: ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1-> {
                        GameLogic.clearConsole(); // Change clearing spot?
                        System.out.println();
                        demoLoginInfo(username, password);
                        GameLogic.startGame();
                        System.out.println();
                        GameLogic.canReview();
                    }

                    case 2 -> {
                        GameLogic.clearConsole(); // Change clearing spot?
                        System.out.println();
                        printDemoLoginFile();
                        System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                        System.out.println();
                        demoLoginInfo(username, password);
                        GameLogic.startGame();
                        System.out.println();
                        GameLogic.canReview();
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method 4- Printing new Demo reviewer username and password to file method
    public static void printDemoLoginFile() {
        java.io.File demoFile = new java.io.File("D.txt");
        try {
            if(!demoFile.exists()) {
                System.out.println("New file created.");
                demoFile.createNewFile();
            }
            java.io.PrintWriter output = new java.io.PrintWriter(new FileWriter(demoFile, true));

            // Write formatted output to the file
            Scanner info = new Scanner (System.in);
            System.out.print("Create Username: ");
            String newUsername = info.nextLine();

            System.out.print("Create Password: ");
            String newPassword = info.nextLine();

            output.println(newUsername);
            output.println(newPassword);

            output.close();
        } catch (IOException ex) {
            System.out.println("Error Opening Output file");
        }
    }

    // Method 5- Admin Login method
    public static void adminLoginInfo(String username, String password) {
        //
        Scanner info = new Scanner (System.in);

        System.out.print("Enter Username: ");
        String adminUser = info.nextLine();

        System.out.print("Enter Password: ");
        String adminPass = info.nextLine();

        try {
            Scanner read = new Scanner(new File("A.txt"));
            boolean isFound = false;

            while (read.hasNextLine()) {
                username = read.nextLine();
                password = read.nextLine();

                if (adminUser.equals(username) && adminPass.equals(password)) {
                    isFound = true;
                }
            }
            read.close();

            if (isFound) {
                GameLogic.clearConsole(); // Change clearing spot?
                System.out.println("You have entered the admin's domain.");
                System.out.println();
                Admin.admin();

            } else {
                GameLogic.clearConsole(); // Change clearing spot?
                System.out.println("Oh no! Your spell words did not open your path! Please recite them again.");
                adminLoginInfo(username, password);
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
