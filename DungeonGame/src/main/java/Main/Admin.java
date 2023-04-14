package Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import java.io.*;
import java.util.*;

import static Main.GameLogic.language;
import static Main.GameLogic.translator;

public class Admin {
    public static void admin() throws IOException, DeepLException, InterruptedException { // implement "GameLogic.clearConsole();" to make appearance better?
        int choice;

        do {
            System.out.println("***********************");
<<<<<<< Updated upstream
            TextResult result = translator.translateText(" 1 - Delete file", null, language);
            System.out.println(result.getText());
            TextResult result1 = translator.translateText(" 2 - Save file", null, language);
            System.out.println(result1.getText());
            TextResult result2 = translator.translateText(" 3 - Load file", null, language);
=======
            TextResult result = translator.translateText(" 1 - Load Account Info", null, language);
            System.out.println(result.getText());
            TextResult result1 = translator.translateText(" 2 - Edit Account Info", null, language);
            System.out.println(result1.getText());
            TextResult result2 = translator.translateText(" 3 - Delete Account Info", null, language);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    TextResult result7 = translator.translateText("Choose file- ", null, language);
                    System.out.println(result7.getText());
                    TextResult result8 = translator.translateText("Player Login Info File (1)", null, language);
                    System.out.println(result8.getText());
                    TextResult result9 = translator.translateText("Demo Login Info File (2)", null, language);
                    System.out.println(result9.getText());
                    TextResult result10 = translator.translateText("Admin Login Info File (3)", null, language);
                    System.out.println(result10.getText());
                    System.out.print("File: ");
=======
                    TextResult result5 = translator.translateText("Choose file- ", null, language);
                    System.out.println(result5.getText());
                    TextResult result6 = translator.translateText("(1) Player Account Info File", null, language);
                    System.out.println(result6.getText());
                    TextResult result7 = translator.translateText("(2) Admin Account Info File", null, language);
                    System.out.println(result7.getText());
                    TextResult result8 = translator.translateText("File: ", null, language);
                    System.out.println(result8.getText());
>>>>>>> Stashed changes

                    int file = input.nextInt();

                    switch (file) {
                        case 1 -> {
                            System.out.println();
<<<<<<< Updated upstream
                            System.out.println("File: 'P.txt'");
                            loadPlayerLoginFile();
=======
                            TextResult result9 = translator.translateText("File: 'P.csv'", null, language);
                            System.out.println(result9.getText());
                            loadPlayerFile();
>>>>>>> Stashed changes
                            System.out.println();
                        }

                        case 2 -> {
                            System.out.println();
<<<<<<< Updated upstream
                            System.out.println("File: 'D.txt'");
                            loadDemoLoginFile();
                            System.out.println();
                        }

                        case 3 -> {
                            System.out.println();
                            System.out.println("File: 'A.txt'");
=======
                            TextResult result10 = translator.translateText("File: 'A.csv'", null, language);
                            System.out.println(result10.getText());
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======

    // Method 3 - delete accounts from the PlayerFile
    public static void deletePlayerFile() throws DeepLException, InterruptedException {
        TextResult result = translator.translateText("(1) Delete a single account", null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText("(2) Delete all accounts", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("Select action: ", null, language);
        System.out.println(result2.getText());

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
                System.out.println(result4.getText());
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

    // Method 3.1 - delete accounts base code
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

    // Method 4 - edit and save Player File
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
        System.out.println(result.getText());
        int acctNum = input.nextInt();
        System.out.println();

        editAccounts("P.csv", acctNum);
    }

    // Method 4.1 - edit and save each line of Player File
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

    // Method 4.2 - edit each element of Player File
    public static String editLine(String currentLine) throws DeepLException, InterruptedException {
        Scanner input1 = new Scanner(System.in);

        String splitBy = ",";
        String[] acctArr;

        acctArr = currentLine.split(splitBy);

        String user = acctArr[0];
        String pass = acctArr[1];
        String name = acctArr[2];

        TextResult result = translator.translateText("(1) Username: " + user, null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText("(2) Password: " + pass, null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("(3) Character Name: " + name, null, language);
        System.out.println(result2.getText());
        System.out.println();
        TextResult result3 = translator.translateText("Select variable to edit: ", null, language);
        System.out.println(result3.getText());

        Scanner scan = new Scanner(System.in);
        int choice3 = scan.nextInt();

        System.out.println();

        switch (choice3) {
            case 1 -> {
                TextResult result4 = translator.translateText("Enter new Username: ", null, language);
                System.out.println(result4.getText());
                String newUser = input1.nextLine();
                currentLine = newUser + "," + pass + "," + name;
            }

            case 2 -> {
                TextResult result5 = translator.translateText("Enter new Password: ", null, language);
                System.out.println(result5.getText());
                String newPass = input1.nextLine();
                currentLine = user + "," + newPass + "," + name;
            }

            case 3 -> {
                TextResult result6 = translator.translateText("Enter new Character Name: ", null, language);
                System.out.println(result6.getText());
                String newName = input1.nextLine();
                currentLine = user + "," + pass + "," + newName;
            }
        }
        return currentLine;
    }
>>>>>>> Stashed changes
}
