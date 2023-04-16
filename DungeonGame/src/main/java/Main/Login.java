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
        //translate();
        /*
        Main.GameLogic.clearConsole();
        System.out.println("******** Login ********");
        System.out.println(" 1 - Player");
        System.out.println(" 2 - Demo-Reviewer");
        System.out.println(" 3 - Admin");
        System.out.println(" 4 - View reviews");
        System.out.println("***********************");
        System.out.print("Choice Login Type: ");
         */

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
                /*
                System.out.println("Do you have an account?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                System.out.print("-> ");
                 */

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
                    //System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");

                    TextResult result9 = translator.translateText("Magical spell for the Dungeon Entrance created! Let the adventure begin!", null, language);
                    System.out.println(result9.getText());

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

        //System.out.print("Enter Username: ");

        TextResult result = translator.translateText("Enter Username: ", null, language);
        System.out.print(result.getText());

        String playUser = info.nextLine();

        //System.out.print("Enter Password: ");

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
                //System.out.println("Time to enter the world of mazes, monsters, and magic!");

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

                //System.out.println("(1) Continue with old Character");
                //System.out.println("(2) Restart with new Character");
                //System.out.print("-> ");

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
                //System.out.println("Oh no! Your spell was not in the Great Spell-book!");
                //System.out.print("Do you want try again (1) or create a new entrance spell to start a new adventure (2)?: ");

                TextResult result5 = translator.translateText("Oh no! Your spell was not in the Great Spell-book!", null, language);
                System.out.println(result5.getText());
                TextResult result6 = translator.translateText("Do you want to try again (1) or create a new entrance spell to start a new adventure (2)?: ", null, language);
                System.out.print(result6.getText());

                int choice = input.nextInt();

                switch (choice) {
                    case 1 -> {
                        GameLogic.clearConsole(); // Change clearing spot?
                        System.out.println();
                        playerLoginInfo();

                    }

                    case 2 -> { // Wrong character info, enter new login info
                        GameLogic.clearConsole();
                        System.out.println();
                        String charName = createPlayerLoginFile();

                        //System.out.println("Magical spell for the Dungeon Entrance created! Let the adventure begin!");

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
            //System.out.println("Error Opening file");

            TextResult result8 = translator.translateText("Error Opening file", null, language);
            System.out.println(result8.getText());

        }
        return playUser;
    }

    // Method 2- Printing new Player username and password and to file method
    public static String createPlayerLoginFile() throws DeepLException, InterruptedException {

        // Write formatted output to the file
        Scanner info = new Scanner(System.in);
        //System.out.print("Create Username: ");

        TextResult result = translator.translateText("Create Username: ", null, language);
        System.out.print(result.getText());

        String newUsername = info.nextLine();

        //System.out.print("Create Password: ");

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
                //System.out.println("New file created.");

                TextResult result3 = translator.translateText("New file created.", null, language);
                System.out.println(result3.getText());

                playerFile.createNewFile();
            }
            PrintWriter output = new PrintWriter(new FileWriter(playerFile, true));

            output.println(newUsername + "," + newPassword + "," + charName + "," + HP + "," + potNum + "," + room);  //

            output.close();

        } catch (IOException ex) {
            //System.out.println("Error Opening Output file");

            TextResult result4 = translator.translateText("Error Opening file", null, language);
            System.out.println(result4.getText());

        }

        return charName;
    }

    // Method 5- Admin Login method
    public static void adminLoginInfo(String user, String pass) throws DeepLException, InterruptedException {
        //
        Scanner info = new Scanner(System.in);

        //System.out.print("Enter Username: ");

        TextResult result = translator.translateText("Enter Username: ", null, language);
        System.out.print(result.getText());

        String adminUser = info.nextLine();

        //System.out.print("Enter Password: ");

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
                //System.out.println("You have entered the admin's domain.");

                TextResult result2 = translator.translateText("You have entered the admin's domain.", null, language);
                System.out.println(result2.getText());

                System.out.println();
                Admin.admin();

            } else {
                GameLogic.clearConsole();
                //System.out.println("Oh no! Your spell words did not open your path! Please recite them again.");

                TextResult result3 = translator.translateText("Oh no! Your spell words did not open your path! Please recite them again.", null, language);
                System.out.println(result3.getText());

                adminLoginInfo(user, pass);
                System.out.println();
            }

        } catch (IOException | DeepLException | InterruptedException ex) {
            //System.out.println("Error Opening file");

            TextResult result3 = translator.translateText("Error Opening file", null, language);
            System.out.println(result3.getText());

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
            //System.out.println("Error Opening file");

            TextResult result = translator.translateText("Error Opening file", null, language);
            System.out.println(result.getText());

        }
        return playStats;
    }
}
