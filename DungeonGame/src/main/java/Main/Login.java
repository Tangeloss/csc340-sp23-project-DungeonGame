package Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import Creatures.Player;
import java.util.*;
import java.io.*;

/**
 * Login class that manages who is logging in and what they are logging in as, all methods help to identify
 * player and user as their appropriate role.
 */
public class Login {

    /**
     * The player object taken back from the game in order to save data.
     */
    static Creatures.Player loginPlayer;
    public static String authKey = "e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx"; //e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx
    public static String language = "en-US"; //default startup language is English
    public static Translator translator = new Translator(authKey);

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * Allows for user to select their role in terms of the game's functions.
     * Select player if they are a customer
     * select Demo-Reviewer if they are a reviewer
     * select Admin if they are an Admin
     * select View reviews if they'd like to view the review.txt file.
     */
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
        System.out.print(result5.getText());

        Scanner input = new Scanner(System.in);
        int login = input.nextInt();

        String adminUser = "";
        String adminPass = "";

        // Menu Selections
        switch (login) {
            case 1 -> {
                GameLogic.clearConsole();

                TextResult result6 = translator.translateText("Do you have an account?", null, language);
                System.out.println(result6.getText());
                TextResult result7 = translator.translateText("(1) Yes", null, language);
                System.out.println(result7.getText());
                TextResult result8 = translator.translateText("(2) No", null, language);
                System.out.println(result8.getText());
                System.out.print("-> ");

                int choice = input.nextInt();
                if (choice == 1) {
                    System.out.println();
                    Login.playerLoginInfo();

                } else {
                    String charName = createPlayerLoginFile();
                    TextResult result9 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                    System.out.println(result9.getText());

                    System.out.println();
                    loginPlayer = GameLogic.startGame(charName, 100, 0, -1);

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


    /**
     * @return playUser, which is the player that is currently logging in. Allows for further manipulation of their
     * account and maintenance of identity with regards to their save file.
     * @throws DeepLException
     * @throws InterruptedException
     */
    public static String playerLoginInfo() throws DeepLException, InterruptedException {

        Scanner info = new Scanner(System.in);
        TextResult result = translator.translateText("Enter Username: ", null, language);
        System.out.print(result.getText());

        String playUser = info.nextLine();
        TextResult result1 = translator.translateText("Enter Password: ", null, language);
        System.out.print(result1.getText());

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
                System.out.println();
                TextResult result2 = translator.translateText("Time to enter the world of mazes, monsters, and magic!", null, language);
                System.out.println(result2.getText());
                System.out.println();

                String[] playStats = new String[4];
                playStats = readStats(playUser);

                String name = playStats[0];
                int hp = Integer.parseInt(playStats[1]);
                int potNum = Integer.parseInt(playStats[2]);
                int room = Integer.parseInt(playStats[3]);

                TextResult result3 = translator.translateText("(1) Continue with old Character", null, language);
                System.out.println(result3.getText());
                TextResult result4 = translator.translateText("(2) Restart with new Character", null, language);
                System.out.println(result4.getText());
                System.out.print("-> ");

                Scanner scan = new Scanner(System.in);
                int action = scan.nextInt();

                switch (action) {
                    case 1 -> {
                        loginPlayer = GameLogic.startGame(name, hp, potNum, room);
                        Save.saveStats("P.csv", loginPlayer);
                    }

                    case 2 -> {
                        String charName = GameLogic.nameCharacter();
                        Save.saveCharName("P.csv", playUser, charName);
                        loginPlayer = GameLogic.startGame(charName, 100, 0, -1);
                        Save.saveStats("P.csv", loginPlayer);
                    }
                }
            } else {
                Scanner input = new Scanner(System.in);

                System.out.println();
                TextResult result5 = translator.translateText("Oh no! Your spell was not in the Great Spell-book!", null, language);
                System.out.println(result5.getText());
                TextResult result6 = translator.translateText("Do you want to try again (1) or create a new entrance spell to start a new adventure (2)?: ", null, language);
                System.out.println(result6.getText());
                System.out.print("-> ");

                int choice = input.nextInt();

                switch (choice) {
                    case 1 -> {
                        GameLogic.clearConsole();
                        System.out.println();
                        playerLoginInfo();

                    }

                    case 2 -> { // Wrong character info, enter new login info
                        GameLogic.clearConsole();
                        System.out.println();
                        String charName = createPlayerLoginFile();
                        TextResult result7 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                        System.out.println(result7.getText());

                        System.out.println();
                        loginPlayer = GameLogic.startGame(charName, 100, 0, -1);
                        //SAVE
                        Save.saveStats("P.csv", loginPlayer);
                    }
                }
            }

        } catch (IOException | DeepLException | InterruptedException ex) {
            TextResult result8 = translator.translateText("Error Opening file", null, language);
            System.out.println(result8.getText());

        }
        return playUser;
    }

    /**
     * @return the name of the new player's character.
     * Allows the player to create a new login file and begin the game from a fresh file.
     */
    public static String createPlayerLoginFile() throws DeepLException, InterruptedException {

        // Write formatted output to the file
        Scanner info = new Scanner(System.in);
        TextResult result = translator.translateText("Create Username: ", null, language);
        System.out.print(result.getText());

        String newUsername = info.nextLine();
        TextResult result1 = translator.translateText("Create Password: ", null, language);
        System.out.print(result1.getText());

        String newPassword = info.nextLine();
        String charName = GameLogic.nameCharacter();
        GameLogic.clearConsole();

        String HP = String.valueOf(100);
        String potNum = String.valueOf(0);
        String room = String.valueOf(-1);

        File playerFile = new File("P.csv");
        try {
            if (!playerFile.exists()) {
                TextResult result3 = translator.translateText("New file created.", null, language);
                System.out.println(result3.getText());

                playerFile.createNewFile();
            }
            PrintWriter output = new PrintWriter(new FileWriter(playerFile, true));

            output.println(newUsername + "," + newPassword + "," + charName + "," + HP + "," + potNum + "," + room);  //

            output.close();

        } catch (IOException ex) {
            TextResult result4 = translator.translateText("Error Opening file", null, language);
            System.out.println(result4.getText());

        }

        return charName;
    }

    /**
     * @param user Takes in the Admin's Username
     * @param pass Takes in the Admin's password
     *
     * The passed variables are compared to user input to check if they match and are allowed to log in.
     */
    public static void adminLoginInfo(String user, String pass) throws DeepLException, InterruptedException {
        Scanner info = new Scanner(System.in);
        TextResult result = translator.translateText("Enter Username: ", null, language);
        System.out.print(result.getText());
        String adminUser = info.nextLine();
        TextResult result1 = translator.translateText("Enter Password: ", null, language);
        System.out.print(result1.getText());

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
                GameLogic.clearConsole();
                TextResult result2 = translator.translateText("You have entered the admin's domain.", null, language);
                System.out.println(result2.getText());
                System.out.println();
                Admin.admin();

            } else {
                GameLogic.clearConsole();
                TextResult result3 = translator.translateText("Oh no! Your spell words did not open your path! Please recite them again.", null, language);
                System.out.println(result3.getText());
                adminLoginInfo(user, pass);
                System.out.println();
            }

        } catch (IOException | DeepLException | InterruptedException ex) {
            TextResult result3 = translator.translateText("Error Opening file", null, language);
            System.out.println(result3.getText());

        }
    }

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * Allows the user to select translation language. Six languages are available: English, Spanish, German, Italian,
     * Korean and French.
     */
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

    /**
     * @return the selected language from the user.
     */
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

    /**
     * @param username takes in the username of the current player
     * @return completed read of all player's statistics, assigning them to an array to be printed to the file.
     */
    public static String[] readStats(String username) throws DeepLException, InterruptedException {
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
            TextResult result = translator.translateText("Error Opening file", null, language);
            System.out.println(result.getText());

        }
        return playStats;
    }
}