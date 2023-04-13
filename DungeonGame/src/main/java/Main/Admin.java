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
public class Admin {
    public static void admin() throws DeepLException, InterruptedException { // implement "GameLogic.clearConsole();" to make appearance better?
        int choice = 0;

        do {
            System.out.println("***********************");
            System.out.println(" 1 - Load Account Info");
            System.out.println(" 2 - Edit Account Info");
            System.out.println(" 3 - Delete Account Info");
            System.out.println(" 4 - Log out");
            System.out.println("***********************");
            System.out.print("Select function: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    GameLogic.clearConsole();
                    System.out.println("Choose file- ");
                    System.out.println("(1) Player Account Info File");
                    System.out.println("(2) Admin Account Info File");
                    System.out.print("File: ");

                    int file = input.nextInt();

                    switch (file) {
                        case 1 -> {
                            System.out.println();
                            System.out.println("File: 'P.csv'");
                            loadPlayerFile();
                            System.out.println();
                        }

                        case 2 -> {
                            System.out.println();
                            System.out.println("File: 'A.csv'");
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
        System.out.println(); //how to drag to bottom like craig has game play do
    }

    // Method 1 - load player login info
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


    // Method 2 - load admin login info
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

    // Method 3 - delete accounts from the PlayerFile
    public static void deletePlayerFile() {
        System.out.println("(1) Delete a single account");
        System.out.println("(2) Delete all accounts");
        System.out.print("Select action: ");

        Scanner input = new Scanner(System.in);
        int choice1 = input.nextInt();
        int i = 1;

        switch (choice1) {
            case 1 -> {
                try {
                    Scanner read = new Scanner(new File("P.csv"));

                    System.out.println();
                    System.out.println("Account: ");
                    while (read.hasNextLine()) {
                        System.out.println(i++ + ") " + read.nextLine());
                    }
                    read.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println();
                System.out.print("Choose Account number: ");
                int acctNum = input.nextInt();

                removeAccounts("P.csv", acctNum);
                System.out.println();
                System.out.println("Account " + acctNum + " deleted.");
                System.out.println();
            }

            case 2 -> {
                Path path = Paths.get("P.csv");

                try {

                    Files.deleteIfExists(path);
                } catch (IOException e) {

                    e.printStackTrace();
                }
                System.out.println("All Player accounts deleted.");
            }
        }
    }

    // Method 3.1 - delete accounts base code
    public static void removeAccounts(String filepath, int acct) {
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
            System.out.println("Error Opening file");
        }
    }

    // Method 4 - edit and save Player File
    public static void editPLayerFile() {
        Scanner input = new Scanner(System.in);

        int i = 1;

        try {
            Scanner read = new Scanner(new File("P.csv"));

            System.out.println();
            System.out.println("Account: ");
            while (read.hasNextLine()) {
                System.out.println(i++ + ") " + read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.print("Choose Account number: ");
        int acctNum = input.nextInt();
        System.out.println();

        editAccounts("P.csv", acctNum);
    }

    // Method 4.1 - edit and save each line of Player File
    public static void editAccounts(String filepath, int acct) {
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
                    System.out.println("Edit complete.");
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
            System.out.println("Error Opening file");
        }
    }

    // Method 4.2 - edit each element of Player File
    public static String editLine(String currentLine) {
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


        System.out.println("(1) Username: " + user);
        System.out.println("(2) Password: " + pass);
        System.out.println("(3) Character Name: " + name);
        System.out.println("(4) HP Amount: " + HP);
        System.out.println("(5) Number of Potions: " + potNum);
        System.out.println("(6) Position in Dungeon: " + room);
        System.out.println();
        System.out.print("Select variable to edit: ");

        Scanner scan = new Scanner(System.in);
        int choice3 = scan.nextInt();

        System.out.println();

        switch (choice3) {
            case 1 -> {
                System.out.print("Enter new Username: ");
                String newUser = input1.nextLine();
                currentLine = newUser + "," + pass + "," + name + "," + HP + "," + potNum + "," + room;
            }

            case 2 -> {
                System.out.print("Enter new Password: ");
                String newPass = input1.nextLine();
                currentLine = user + "," + newPass + "," + name + "," + HP + "," + potNum + "," + room;
            }

            case 3 -> {
                System.out.print("Enter new Character Name: ");
                String newName = input1.nextLine();
                currentLine = user + "," + pass + "," + newName + "," + HP + "," + potNum + "," + room;
            }

            case 4 -> {
                System.out.print("Enter new HP number: ");
                String newHP = input1.nextLine();
                currentLine = user + "," + pass + "," + name + "," + newHP + "," + potNum + "," + room;
            }

            case 5 -> {
                System.out.print("Enter new Potion Number: ");
                String newPotNum = input1.nextLine();
                currentLine = user + "," + pass + "," + name + "," + HP + "," + newPotNum + "," + room;
            }

            case 6 -> {
                System.out.print("Enter new Room Position: ");
                String newRoom = input1.nextLine();
                currentLine = user + "," + pass + "," + name + "," + HP + "," + potNum + "," + newRoom;
            }
        }
        return currentLine;
    }
}
