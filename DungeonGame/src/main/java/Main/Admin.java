package Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import static Main.Login.language;
import static Main.Login.translator;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Admin is allowed to do many things that a Customer or Demo-reviewer are not. They are allowed to manipulate
 * account information from the backend and delete account information of users.
 */
public class Admin {

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * Admin menu, from here admin can choose to load, edit or delete account information.
     * Also given the option ot end their current session, closing the program.
     */
    public static void admin() throws DeepLException, InterruptedException {
        int choice = 0;

        do {
            System.out.println("***********************");
            TextResult result = translator.translateText(" 1 - Load Account Info", null, language);
            System.out.println(result.getText());
            TextResult result1 = translator.translateText(" 2 - Edit Account Info", null, language);
            System.out.println(result1.getText());
            TextResult result2 = translator.translateText(" 3 - Delete Account Info", null, language);
            System.out.println(result2.getText());
            TextResult result3 = translator.translateText(" 4 - Log out", null, language);
            System.out.println(result3.getText());
            System.out.println("***********************");
            TextResult result4 = translator.translateText("Select function: ", null, language);
            System.out.print(result4.getText());

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    GameLogic.clearConsole();
                    TextResult result5 = translator.translateText("Choose file- ", null, language);
                    System.out.println(result5.getText());
                    TextResult result6 = translator.translateText("(1) Player Account Info File", null, language);
                    System.out.println(result6.getText());
                    TextResult result7 = translator.translateText("(2) Admin Account Info File", null, language);
                    System.out.println(result7.getText());
                    TextResult result8 = translator.translateText("File: ", null, language);
                    System.out.print(result8.getText());

                    int file = input.nextInt();

                    switch (file) {
                        case 1 -> {
                            System.out.println();
                            TextResult result9 = translator.translateText("File: 'P.csv'", null, language);
                            System.out.println(result9.getText());

                            loadPlayerFile();
                            System.out.println();
                        }

                        case 2 -> {
                            System.out.println();
                            TextResult result10 = translator.translateText("File: 'A.csv'", null, language);
                            System.out.println(result10.getText());

                            loadAdminLoginFile();
                            System.out.println();
                        }
                    }
                }

                case 2 -> {
                    GameLogic.clearConsole();
                    editPLayerFile();

                }

                case 3 -> {
                    GameLogic.clearConsole();
                    deletePlayerFile();
                }
            }
        } while (choice != 4);
        GameLogic.clearConsole();
        Login.LoginMenu();
        System.out.println();
    }

    /**
     * Allows admin to load player information for further editing or deletion.
     */
    public static void loadPlayerFile() {
        try {
            Scanner read = new Scanner(new File("P.csv"));

            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows admin to load the admin's login to manipulate username or password.
     */
    public static void loadAdminLoginFile() {
        try {
            Scanner read = new Scanner(new File("A.csv"));

            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows admin to delete player's accounts, forcing them to reset their progress and make a new account.
     * Allows admin to either delete all player's accounts or characters or singular ones.
     */
    public static void deletePlayerFile() throws DeepLException, InterruptedException {
        TextResult result = translator.translateText("(1) Delete a single account", null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText("(2) Delete all accounts", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("Select action: ", null, language);
        System.out.print(result2.getText());

        Scanner input = new Scanner(System.in);
        int choice1 = input.nextInt();
        int i = 1;

        switch (choice1) {
            case 1 -> {
                try {
                    Scanner read = new Scanner(new File("P.csv"));

                    System.out.println();
                    TextResult result3 = translator.translateText("Account: ", null, language);
                    System.out.println(result3.getText());

                    while (read.hasNextLine()) {
                        System.out.println(i++ + ") " + read.nextLine());
                    }
                    read.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println();
                TextResult result4 = translator.translateText("Choose Account number: ", null, language);
                System.out.print(result4.getText());

                int acctNum = input.nextInt();

                removeAccounts("P.csv", acctNum);
                System.out.println();
                TextResult result5 = translator.translateText("Account " + acctNum + " deleted.", null, language);
                System.out.println(result5.getText());

                System.out.println();
            }

            case 2 -> {
                Path path = Paths.get("P.csv");

                try {

                    Files.deleteIfExists(path);
                } catch (IOException e) {

                    e.printStackTrace();
                }
                TextResult result6 = translator.translateText("All Player accounts deleted.", null, language);
                System.out.println(result6.getText());

            }
        }
    }

    /**
     * @param filepath takes in where the account file currently is in the local machine's memory.
     * @param acct The account that admin wants to delete.
     */
    public static void removeAccounts(String filepath, int acct) throws DeepLException, InterruptedException {
        File oldFile = new File(filepath);
        File newFile = new File("temp.csv");

        int line = 0;
        String currentLine = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            PrintWriter output = new PrintWriter(new FileWriter("temp.csv", true));

            while ((currentLine = br.readLine()) != null) {
                line++;

                if (acct != line) {
                    output.println(currentLine);
                }
            }
            output.close();
            br.close();

            oldFile.delete();
            File file = new File(filepath);
            newFile.renameTo(file);

        } catch (IOException ex) {
            TextResult result = translator.translateText("Error Opening file", null, language);
            System.out.println(result.getText());

        }
    }

    /**
     * Allows admin to edit usernames and passwords of users. Passes the selected profile to editAccounts for
     * further manipulation
     */
    public static void editPLayerFile() throws DeepLException, InterruptedException {
        Scanner input = new Scanner(System.in);

        int i = 1;

        try {
            Scanner read = new Scanner(new File("P.csv"));

            System.out.println();
            TextResult result = translator.translateText("Account: ", null, language);
            System.out.println(result.getText());

            while (read.hasNextLine()) {
                System.out.println(i++ + ") " + read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
        TextResult result = translator.translateText("Choose Account number: ", null, language);
        System.out.print(result.getText());

        int acctNum = input.nextInt();
        System.out.println();

        editAccounts("P.csv", acctNum);
    }

    /**
     * @param filepath the file name of the selected file
     * @param acct the current account that admin would want to modify
     *
     * accepts the file to modify and allows admin to modify the player's login credentials
     */
    public static void editAccounts(String filepath, int acct) throws DeepLException, InterruptedException {
        File oldFile = new File(filepath);
        File newFile = new File("temp.csv");

        int line = 0;
        String currentLine = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            PrintWriter output = new PrintWriter(new FileWriter("temp.csv", true));

            while ((currentLine = br.readLine()) != null) {
                line++;

                if (acct == line) {
                    currentLine = editLine(currentLine);
                    System.out.println();
                    TextResult result = translator.translateText("Edit complete.", null, language);
                    System.out.println(result.getText());

                    System.out.println();
                }
                output.println(currentLine);
            }
            output.close();
            br.close();

            oldFile.delete();
            File file = new File(filepath);
            newFile.renameTo(file);

        } catch (IOException ex) {
            TextResult result = translator.translateText("Error Opening file", null, language);
            System.out.println(result.getText());

        }
    }

    /**
     * @param currentLine passes the current account information that admin would like to modify. allows for
     *                    modification of all of the user's statistics
     * @return the modified contents and statistics of the file.
     */
    public static String editLine(String currentLine) throws DeepLException, InterruptedException {
        Scanner input1 = new Scanner(System.in);

        String splitBy = ",";
        String[] acctArr;

        acctArr = currentLine.split(splitBy);

        String user = acctArr[0];
        String pass = acctArr[1];
        String name = acctArr[2];
        String HP = acctArr[3];
        String potNum = acctArr[4];
        String room = acctArr[5];

        TextResult result = translator.translateText("(1) Username: " + user, null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText("(2) Password: " + pass, null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("(3) Character Name: " + name, null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText("(4) HP Amount: " + HP, null, language);
        System.out.println(result3.getText());
        TextResult result4 = translator.translateText("(5) Number of Potions: " + potNum, null, language);
        System.out.println(result4.getText());
        TextResult result5 = translator.translateText("(6) Position in Dungeon: " + room, null, language);
        System.out.println(result5.getText());
        TextResult result6 = translator.translateText("Select variable to edit: ", null, language);
        System.out.print(result6.getText());

        Scanner scan = new Scanner(System.in);
        int choice3 = scan.nextInt();

        System.out.println();

        switch (choice3) {
            case 1 -> {
                TextResult result7 = translator.translateText("Enter new Username: ", null, language);
                System.out.print(result7.getText());
                String newUser = input1.nextLine();
                currentLine = newUser + "," + pass + "," + name + "," + HP + "," + potNum + "," + room;
            }

            case 2 -> {
                TextResult result8 = translator.translateText("Enter new Password: ", null, language);
                System.out.print(result8.getText());
                String newPass = input1.nextLine();
                currentLine = user + "," + newPass + "," + name + "," + HP + "," + potNum + "," + room;
            }

            case 3 -> {
                TextResult result9 = translator.translateText("Enter new Character Name: ", null, language);
                System.out.print(result9.getText());
                String newName = input1.nextLine();
                currentLine = user + "," + pass + "," + newName + "," + HP + "," + potNum + "," + room;
            }

            case 4 -> {
                TextResult result10 = translator.translateText("Enter new HP number: ", null, language);
                System.out.print(result10.getText());
                String newHP = input1.nextLine();
                currentLine = user + "," + pass + "," + name + "," + newHP + "," + potNum + "," + room;
            }

            case 5 -> {
                TextResult result11 = translator.translateText("Enter new Potion Number: ", null, language);
                System.out.print(result11.getText());

                String newPotNum = input1.nextLine();
                currentLine = user + "," + pass + "," + name + "," + HP + "," + newPotNum + "," + room;
            }

            case 6 -> {
                TextResult result12 = translator.translateText("Enter new Room Position: ", null, language);
                System.out.print(result12.getText());
                String newRoom = input1.nextLine();
                currentLine = user + "," + pass + "," + name + "," + HP + "," + potNum + "," + newRoom;
            }
        }
        return currentLine;
    }
}