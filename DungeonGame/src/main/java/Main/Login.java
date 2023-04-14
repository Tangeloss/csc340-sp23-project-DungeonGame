package Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import java.util.*;
import java.io.*;

import static Main.GameLogic.language;
import static Main.GameLogic.translator;

public class Login {
<<<<<<< Updated upstream
    public static void LoginMenu() throws IOException, DeepLException, InterruptedException { // implement "GameLogic.clearConsole();" to make appearance better?
        TextResult result = translator.translateText("******** Login ********", null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText(" P - Player", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText(" D - Demo Reviewer", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText(" A - Admin", null, language);
        System.out.println(result3.getText());
        // Add non-login view of review doc? (R - View reviews)
        System.out.println("***********************");
        TextResult result4 = translator.translateText("Choice Login Type: ", null, language);
        System.out.println(result4.getText());
=======

    public static String authKey = "e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx"; //e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx
    public static String language = "en-US"; //default startup language is English
    //public static String name;
    public static Translator translator = new Translator(authKey);

    public static void LoginMenu() throws DeepLException, InterruptedException {
        translate();
        Main.GameLogic.clearConsole();
        TextResult result = translator.translateText("******** Login ********", null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText(" 1 - Player", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText(" 2 - Demo-Reviewer", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText(" 3 - Admin", null, language);
        System.out.println(result3.getText());
        TextResult result4 = translator.translateText(" 4 - View reviews", null, language);
        System.out.println(result4.getText());
        System.out.println("***********************");
        TextResult result5 = translator.translateText("Choice Login Type: ", null, language);
        System.out.println(result5.getText());
>>>>>>> Stashed changes

        Scanner input = new Scanner(System.in);
        char login = input.next().toUpperCase().charAt(0);

        String playUser = "";
        String playPass = "";
        String demoUser = "";
        String demoPass = "";
        String adminUser = "";
        String adminPass = "";

        // Menu Selections
        switch(login) {
            case 'P' -> {
                GameLogic.clearConsole();
<<<<<<< Updated upstream
                TextResult result5 = translator.translateText("Do you have an account? Yes(Y) No(N): ", null, language);
                System.out.println(result5.getText());
                char choice = input.next().toUpperCase().charAt(0);
                if (choice == 'Y') {
                    Login.playerLoginInfo(playUser, playPass);

                } else {
                    Login.printPlayerLoginFile();
                    TextResult result6 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                    System.out.println(result6.getText());
=======
                TextResult result6 = translator.translateText("Do you have an account? Yes(1) No(2): ", null, language);
                System.out.println(result6.getText());
                int choice = input.nextInt();
                if (choice == 1) {
                    Login.playerLoginInfo(playUser, playPass);

                } else {
                    createPlayerLoginFile(name3);
                    TextResult result7 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                    System.out.println(result7.getText());
>>>>>>> Stashed changes
                    System.out.println();
                    Login.playerLoginInfo(playUser, playPass);
                }
            }
            case 'D' -> {
                GameLogic.clearConsole();
                TextResult result7 = translator.translateText("Do you have an account? Yes(Y) No(N): ", null, language);
                System.out.println(result7.getText());
                char choice1 = input.next().toUpperCase().charAt(0);
                if (choice1 == 'Y') {
                    Login.demoLoginInfo(demoUser, demoPass);

                } else {
                    Login.printDemoLoginFile();
                    TextResult result8 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                    System.out.println(result8.getText());
                    System.out.println();
                    Login.demoLoginInfo(demoUser, demoPass);

                }
            }
            case 'A' -> {
                GameLogic.clearConsole();
                Login.adminLoginInfo(adminUser, adminPass);
            }
        }

    }

    // Method 1- Player login in
<<<<<<< Updated upstream
    public static void playerLoginInfo(String username, String password) throws DeepLException, InterruptedException, IOException {
=======
    public static void playerLoginInfo(String username, String password) throws DeepLException, InterruptedException {
>>>>>>> Stashed changes

        Scanner info = new Scanner (System.in);

        TextResult result = translator.translateText("Enter Username: ", null, language);
        System.out.println(result.getText());
        String playUser = info.nextLine();
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        TextResult result1 = translator.translateText("Enter Password: ", null, language);
        System.out.println(result1.getText());
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
<<<<<<< Updated upstream
                TextResult result2 = translator.translateText("Time to enter the world of mazes, monsters, and magic!", null, language);
                System.out.println(result2.getText());
                GameLogic.startGame();
=======
                TextResult result3 = translator.translateText("Time to enter the world of mazes, monsters, and magic!", null, language);
                System.out.println(result3.getText());
                GameLogic.startGame(name);
>>>>>>> Stashed changes
                System.out.println();

            } else {
                Scanner input = new Scanner(System.in);

                System.out.println();
<<<<<<< Updated upstream
                TextResult result3 = translator.translateText("Oh no! Your spell was not in the Great Spell-book!", null, language);
                System.out.println(result3.getText());
                TextResult result4 = translator.translateText("Do you want try again (1) or create a new entrance spell to start a new adventure (2)?: ", null, language);
                System.out.println(result4.getText());
=======
                TextResult result4 = translator.translateText("Oh no! Your spell was not in the Great Spell-book!", null, language);
                System.out.println(result4.getText());
                TextResult result5 = translator.translateText("Do you want try again (1) or create a new entrance spell to start a new adventure (2)?: ", null, language);
                System.out.println(result5.getText());
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                        printPlayerLoginFile();
                        TextResult result5 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                        System.out.println(result5.getText());
=======
                        createPlayerLoginFile(name2);

                        TextResult result6 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                        System.out.println(result6.getText());
>>>>>>> Stashed changes
                        System.out.println();
                        playerLoginInfo(username, password);
                        GameLogic.startGame();
                    }
                }
            }

<<<<<<< Updated upstream
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DeepLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Method 2- Printing new Player username and password to file method
    public static void printPlayerLoginFile() throws DeepLException, InterruptedException {
        java.io.File playerFile = new java.io.File("P.txt");
        try {
            if(!playerFile.exists()) {
                TextResult result = translator.translateText("New file created.", null, language);
                System.out.println(result.getText());
=======
        } catch (IOException | DeepLException | InterruptedException ex) {
            TextResult result7 = translator.translateText("Error Opening file", null, language);
            System.out.println(result7.getText());
        }
    }

    // Method 2- Printing new Player username and password and to file method
    public static String createPlayerLoginFile(String charname) throws DeepLException, InterruptedException {

        // Write formatted output to the file
        Scanner info = new Scanner(System.in);
        TextResult result = translator.translateText("Create Username: ", null, language);
        System.out.println(result.getText());
        String newUsername = info.nextLine();

        TextResult result1 = translator.translateText("Create Password: ", null, language);
        System.out.println(result1.getText());
        String newPassword = info.nextLine();

        charname = GameLogic.nameCharacter();
        GameLogic.clearConsole();

        File playerFile = new File("P.csv");
        try {
            if (!playerFile.exists()) {
                TextResult result3 = translator.translateText("New file created.", null, language);
                System.out.println(result3.getText());
>>>>>>> Stashed changes
                playerFile.createNewFile();
            }
            java.io.PrintWriter output = new java.io.PrintWriter(new FileWriter(playerFile, true));

<<<<<<< Updated upstream
            // Write formatted output to the file
            Scanner info = new Scanner (System.in);
            TextResult result = translator.translateText("Create Username: ", null, language);
            System.out.println(result.getText());
            String newUsername = info.nextLine();

            TextResult result1 = translator.translateText("Create Password: ", null, language);
            System.out.println(result1.getText());
            String newPassword = info.nextLine();

            output.println(newUsername);
            output.println(newPassword);
=======
            output.println(newUsername + "," + newPassword + "," + charname);
>>>>>>> Stashed changes

            output.close();
        } catch (IOException ex) {
<<<<<<< Updated upstream
            TextResult result2 = translator.translateText("Error Opening Output file", null, language);
            System.out.println(result2.getText());
        }
    }

    // Method 3- Demo Reviewer login method
    public static void demoLoginInfo(String username, String password) throws DeepLException, InterruptedException, IOException {

        Scanner info = new Scanner (System.in);

        TextResult result = translator.translateText("Enter Username: ", null, language);
        System.out.println(result.getText());
        String demoUser = info.nextLine();

        TextResult result1 = translator.translateText("Enter Password: ", null, language);
        System.out.println(result1.getText());
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
                GameLogic.usertag = "Demo";
                TextResult result2 = translator.translateText("Time to enter the world of mazes, monsters, and magic!", null, language);
                System.out.println(result2.getText());
                System.out.println();
                GameLogic.startGame();
                System.out.println();
                GameLogic.canReview();

            } else {
                Scanner input = new Scanner(System.in);

                System.out.println();
                TextResult result3 = translator.translateText("Oh no! Your spell was not in the Great Spell-book!", null, language);
                System.out.println(result3.getText());
                TextResult result4 = translator.translateText("Do you want try again (1) or create a new entrance spell to start a new adventure (2)?: ", null, language);
                System.out.println(result4.getText());
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
                        TextResult result5 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                        System.out.println(result5.getText());
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
        } catch (DeepLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Method 4- Printing new Demo reviewer username and password to file method
    public static void printDemoLoginFile() throws DeepLException, InterruptedException {
        java.io.File demoFile = new java.io.File("D.txt");
        try {
            if(!demoFile.exists()) {
                TextResult result = translator.translateText("New file created.", null, language);
                System.out.println(result.getText());
                demoFile.createNewFile();
            }
            java.io.PrintWriter output = new java.io.PrintWriter(new FileWriter(demoFile, true));

            // Write formatted output to the file
            Scanner info = new Scanner (System.in);
            TextResult result = translator.translateText("Create Username: ", null, language);
            System.out.println(result.getText());
            String newUsername = info.nextLine();

            TextResult result1 = translator.translateText("Create Password: ", null, language);
            System.out.println(result1.getText());
            String newPassword = info.nextLine();

            output.println(newUsername);
            output.println(newPassword);

            output.close();
        } catch (IOException ex) {
            TextResult result = translator.translateText("Error Opening Output file", null, language);
            System.out.println(result.getText());
        }
    }

    // Method 5- Admin Login method
    public static void adminLoginInfo(String username, String password) throws DeepLException, InterruptedException {
=======
            TextResult result4 = translator.translateText("Error Opening Output file", null, language);
            System.out.println(result4.getText());
        }
        return charname;
    }

    // Method 5- Admin Login method
    public static void adminLoginInfo(String user, String pass) throws DeepLException, InterruptedException {
>>>>>>> Stashed changes
        //
        Scanner info = new Scanner (System.in);

        TextResult result = translator.translateText("Enter Username: ", null, language);
        System.out.println(result.getText());
        String adminUser = info.nextLine();

        TextResult result1 = translator.translateText("Enter Password: ", null, language);
        System.out.println(result1.getText());
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
                TextResult result2 = translator.translateText("You have entered the admin's domain.", null, language);
                System.out.println(result2.getText());
                System.out.println();
                Admin.admin();

            } else {
                GameLogic.clearConsole(); // Change clearing spot?
<<<<<<< Updated upstream
                adminLoginInfo(username, password);
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
=======
                TextResult result3 = translator.translateText("Oh no! Your spell words did not open your path! Please recite them again.", null, language);
                System.out.println(result3.getText());
                adminLoginInfo(user, pass);
                System.out.println();
            }

        } catch (IOException | DeepLException | InterruptedException ex) {
            TextResult result3 = translator.translateText("Error Opening file", null, language);
            System.out.println(result3.getText());
>>>>>>> Stashed changes
        }
    }
}
