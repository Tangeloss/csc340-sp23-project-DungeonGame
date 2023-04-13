package Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import java.util.*;
import java.io.*;

import static Main.GameLogic.language;
import static Main.GameLogic.translator;

public class Login {

    public static void LoginMenu() throws DeepLException, InterruptedException {
        //print.translator
        Main.GameLogic.clearConsole();
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
        String adminUser = "";
        String adminPass = "";
        String name3 = "";

        // Menu Selections
        switch(login) {
            case 1 -> {
                GameLogic.clearConsole();
                System.out.print("Do you have an account? Yes(1) No(2): ");
                int choice = input.nextInt();
                if (choice == 1) {
                    Login.playerLoginInfo(playUser, playPass);

                } else {
                    createPlayerLoginFile(name3);
                    System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                    System.out.println();
                    playerLoginInfo(playUser, playPass);
                }
            }
            case 2 -> {
                GameLogic.startGame(name3);
                DemoReviewer.writeReview();
            }
            case 3 -> {
                GameLogic.clearConsole();
                adminLoginInfo(adminUser, adminPass);
            }
            case 4 -> {
                DemoReviewer.loadReviews();
            }
        }

    }

    // Method 1- Player login in
    public static void playerLoginInfo(String username, String password) {

        Scanner info = new Scanner (System.in);
        String name = "";
        String name2 = "";

        System.out.print("Enter Username: ");
        String playUser = info.nextLine();

        System.out.print("Enter Password: ");
        String playPass = info.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("P.csv"))) {
            String line = "";
            String splitBy = ",";
            String[] loginArr;
            boolean isFound = false;

            while ((line = br.readLine()) != null) {
                loginArr = line.split(splitBy);

                username = loginArr[0];
                password = loginArr[1];

                if (playUser.equals(username) && playPass.equals(password)) {
                    isFound = true;
                }

            }
            br.close();

            if (isFound) {
                System.out.println("Time to enter the world of mazes, monsters, and magic!");
                GameLogic.startGame(name);
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
                        GameLogic.startGame(name);
                    }

                    case 2 -> {
                        GameLogic.clearConsole(); // Change clearing spot?
                        System.out.println();
                        createPlayerLoginFile(name2);

                        System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                        System.out.println();
                        playerLoginInfo(username, password);
                        GameLogic.startGame(name);
                    }
                }
            }

        } catch (IOException | DeepLException | InterruptedException ex) {
            System.out.println("Error Opening file");
        }
    }

    // Method 2- Printing new Player username and password and to file method
    public static String createPlayerLoginFile(String charName) {

        // Write formatted output to the file
        Scanner info = new Scanner (System.in);
        System.out.print("Create Username: ");
        String newUsername = info.nextLine();

        System.out.print("Create Password: ");
        String newPassword = info.nextLine();

        charName = GameLogic.nameCharacter();
        GameLogic.clearConsole();

        File playerFile = new File("P.csv");
        try {
            if(!playerFile.exists()) {
                System.out.println("New file created.");
                playerFile.createNewFile();
            }
            PrintWriter output = new PrintWriter(new FileWriter(playerFile, true));

            output.println(newUsername + "," + newPassword + "," + charName);

            output.close();

        } catch (IOException ex) {
            System.out.println("Error Opening Output file");
        }
        return charName;
    }

    // Method 5- Admin Login method
    public static void adminLoginInfo(String user, String pass) {
        //
        Scanner info = new Scanner (System.in);

        System.out.print("Enter Username: ");
        String adminUser = info.nextLine();

        System.out.print("Enter Password: ");
        String adminPass = info.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("A.csv"))) {
            String line = "";
            String splitBy = ",";
            String[] loginArr;
            boolean isFound = false;

            while ((line = br.readLine()) != null) {
                loginArr = line.split(splitBy);

                user = loginArr[0];
                pass = loginArr[1];

                if (adminUser.equals(user) && adminPass.equals(pass)) {
                    isFound = true;
                }

            }
            br.close();

            if (isFound) {
                GameLogic.clearConsole(); // Change clearing spot?
                System.out.println("You have entered the admin's domain.");
                System.out.println();
                Admin.admin();

            } else {
                GameLogic.clearConsole(); // Change clearing spot?
                System.out.println("Oh no! Your spell words did not open your path! Please recite them again.");
                adminLoginInfo(user, pass);
                System.out.println();
            }

        } catch (IOException | DeepLException | InterruptedException ex) {
            System.out.println("Error Opening file");
        }
    }
}
