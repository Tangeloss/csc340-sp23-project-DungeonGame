package Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import Creatures.Player;
import java.util.*;
import java.io.*;

public class Login {

    static Creatures.Player loginPlayer;
    public static String authKey = "e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx"; //e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx
    public static String language = "en-US"; //default startup language is English
    public static Translator translator = new Translator(authKey);

    public static void LoginMenu() throws DeepLException, InterruptedException {
        translate();
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
        //String playPass = "";
        String adminUser = "";
        String adminPass = "";

        // Menu Selections
        switch (login) {
            case 1 -> {
                GameLogic.clearConsole();
                System.out.print("Do you have an account? Yes(1) No(2): ");
                int choice = input.nextInt();
                if (choice == 1) {
                    Login.playerLoginInfo();

                } else { //HERERERERERE!!!!!!!!!!!!!!!!!!!!!!!!!
                    String charName = createPlayerLoginFile();
                    System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                    System.out.println();
                    loginPlayer = GameLogic.startGame(charName, 100, 0, -1);
                    //SAVE GAME
                    Save.saveStats("P.csv", loginPlayer);
                }
            }
            case 2 -> {
                GameLogic.startGame(GameLogic.nameCharacter(), 100, 0, -1);
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
    public static String playerLoginInfo() throws DeepLException, InterruptedException {

        Scanner info = new Scanner(System.in);

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

                String username = loginArr[0];
                String password = loginArr[1];

                if (playUser.equals(username) && playPass.equals(password)) {
                    isFound = true;
                }

            }
            br.close();

            if (isFound) {
                System.out.println("Time to enter the world of mazes, monsters, and magic!");
                String[] playStats = new String[4];

                playStats = readStats(playUser);

                String name = playStats[0];
                int hp = Integer.parseInt(playStats[1]);
                int potNum = Integer.parseInt(playStats[2]);
                int room = Integer.parseInt(playStats[3]);

                System.out.println("(1) Continue with old Character");
                System.out.println("(2) Restart with new Character");
                System.out.print("-> ");

                Scanner scan = new Scanner(System.in);
                int action = scan.nextInt();

                switch (action) {
                    case 1 -> {
                        loginPlayer = GameLogic.startGame(name, hp, potNum, room);
                        Save.saveStats("P.csv", loginPlayer);
                    }

                    case 2 -> {
                        //String charName = createCharName("P.csv", loginPlayer, playUser, playPass);
                        String charName = GameLogic.nameCharacter();
                        loginPlayer = GameLogic.startGame(charName, 100, 0, -1);
                        Save.saveStats("P.csv", loginPlayer);
                    }
                }
            } else {
                Scanner input = new Scanner(System.in);

                System.out.println();
                System.out.println("Oh no! Your spell was not in the Great Spell-book!");
                System.out.print("Do you want try again (1) or create a new entrance spell to start a new adventure (2)?: ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1 -> {
                        GameLogic.clearConsole(); // Change clearing spot?
                        System.out.println();
                        playerLoginInfo();
                        /*
                        String[] playStats = new String[4];
                        playStats = readStats(playUser);

                        String name = playStats[0];
                        int hp = Integer.parseInt(playStats[1]);
                        int potNum = Integer.parseInt(playStats[2]);
                        int room = Integer.parseInt(playStats[3]);

                        loginPlayer = GameLogic.startGame(name, hp, potNum, room);
                        Save.saveStats("P.csv", loginPlayer);

                         */
                    }

                    case 2 -> { // Wrong character info, enter new login info
                        GameLogic.clearConsole();
                        System.out.println();
                        String charName = createPlayerLoginFile();

                        System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");
                        System.out.println();
                        loginPlayer = GameLogic.startGame(charName, 100, 0, -1);
                        //SAVE
                        Save.saveStats("P.csv", loginPlayer);
                    }
                }
            }

        } catch (IOException | DeepLException | InterruptedException ex) {
            System.out.println("Error Opening file");
        }
        return playUser;
    }

    // Method 2- Printing new Player username and password and to file method
    public static String createPlayerLoginFile() {

        // Write formatted output to the file
        Scanner info = new Scanner(System.in);
        System.out.print("Create Username: ");
        String newUsername = info.nextLine();

        System.out.print("Create Password: ");
        String newPassword = info.nextLine();

        String charName = GameLogic.nameCharacter();
        GameLogic.clearConsole();

        String HP = String.valueOf(100);
        String potNum = String.valueOf(0);
        String room = String.valueOf(-1);

        File playerFile = new File("P.csv");
        try {
            if (!playerFile.exists()) {
                System.out.println("New file created.");
                playerFile.createNewFile();
            }
            PrintWriter output = new PrintWriter(new FileWriter(playerFile, true));

            output.println(newUsername + "," + newPassword + "," + charName + "," + HP + "," + potNum + "," + room);  //

            output.close();

        } catch (IOException ex) {
            System.out.println("Error Opening Output file");
        }

        return charName;
    }

    // Method 5- Admin Login method
    public static void adminLoginInfo(String user, String pass) {
        //
        Scanner info = new Scanner(System.in);

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

    // Method 6 - translation menu
    public static void printLangMenu() throws DeepLException, InterruptedException {
        GameLogic.clearConsole();
        GameLogic.printHeading("MENU");
        System.out.println("Choose a Language:");
        //printSeperator(20);
        System.out.println("(1) EN");
        System.out.println("(2) ES");
        System.out.println("(3) DE");
        System.out.println("(4) IT");
        System.out.println("(5) KO");
        System.out.println("(6) FR");

    }

    public static String translate() throws DeepLException, InterruptedException {
        printLangMenu();
        int input = GameLogic.readChoice("-> ", 6);
        switch (input) {
            case 1 -> language = "en-US";
            case 2 -> language = "es";
            case 3 -> language = "de";
            case 4 -> language = "it";
            case 5 -> language = "ko";
            case 6 -> language = "fr";
            default -> System.out.println("Invalid input, please try again.");
        }
        return language;
    }

    public static String[] readStats(String username) {
        String[] playStats = new String[4];

        try (BufferedReader br = new BufferedReader(new FileReader("P.csv"))) {
            String line = "";
            String splitBy = ",";
            String[] loginArr;

            while ((line = br.readLine()) != null) {
                loginArr = line.split(splitBy);

                String user = loginArr[0];
                String pass = loginArr[1];
                String name = loginArr[2];
                String hp = loginArr[3];
                String potNum = loginArr[4];
                String room = loginArr[5];

                if(username.equals(user)) {
                    playStats = new String[]{name, hp, potNum, room};
                }
            }
            br.close();

        } catch (IOException ex) {
            System.out.println("Error Opening file");
        }
        return playStats;
    }

    /*
    public static String createCharName(String filepath, Player player, String user, String pass) {

        String charName = GameLogic.nameCharacter();
        GameLogic.clearConsole();



        File oldFile = new File(filepath);
        File newFile = new File("temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            PrintWriter output = new PrintWriter(new FileWriter("temp.csv", true));
            String splitBy = ",";
            String[] loginArr;
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                loginArr = currentLine.split(splitBy);

                String username = loginArr[0];
                String password = loginArr[1];

                if (user.equals(username) && pass.equals(password)) {
                    currentLine = editName(player, currentLine);
                }

                output.println(currentLine);
            }
            output.close();
            br.close();

            oldFile.delete();
            File file = new File(filepath);
            newFile.renameTo(file);

        } catch (IOException ex) {
            System.out.println("Error Opening file");
        }

        return charName;
    }

    public static String editName(Player player, String currentLine) {

        String splitBy = ",";
        String[] statArr;

        statArr = currentLine.split(splitBy);

        String user = statArr[0];
        String pass = statArr[1];
        String name = statArr[2];
        String HP = String.valueOf(100);
        String potNum = String.valueOf(0);
        String room = String.valueOf(-1);

        currentLine = user + "," + pass + "," + name + "," + HP + "," + potNum + "," + room;

        return currentLine;
    }

     */
}
