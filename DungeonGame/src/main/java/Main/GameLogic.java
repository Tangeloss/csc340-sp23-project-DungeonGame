package Main;

import Creatures.Monster;
import Creatures.Player;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);

    static Player player;

    public static String authKey = "e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx"; //Maybe a method to read auth key from a txt file?

    public static Translator translator = new Translator(authKey);

    public static boolean isRunning;

    //Story Elements, dungeon location
    public static int place = 0;

    public static String language = "en-US"; //default startup language is English

    public static String usertag;


    //method to get user input from console
    public static int readChoice(String prompt, int userChoices) throws DeepLException, InterruptedException {
        int input;

        do{
            System.out.print(prompt);
            try{
                input = Integer.parseInt(scanner.next());
                if (input < 0 || input > userChoices){
                    TextResult result = translator.translateText("Invalid choice, please enter an integer between 1 and " + userChoices, null, language);
                    System.out.println(result.getText());
                    //System.out.println("Invalid choice, please enter an integer between 1 and " + userChoices);
                }
            } catch (Exception e){
                input = -1;
                TextResult result = translator.translateText("Please enter an Integer!", null, language);
                System.out.println(result.getText());
                //System.out.println("Please enter an Integer!");
            }

        } while(input < 1 || input > userChoices);

        return input;
    }

    //method to clear the console
    public static void clearConsole(){
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    //method to print separator with length n
    public static void printSeperator(int n){
        for(int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    //Method to print a heading
    public static void printHeading(String title) throws DeepLException, InterruptedException {
        printSeperator(30);
        TextResult result = translator.translateText(title, null, language);
        System.out.println(result.getText());
        printSeperator(30);
    }

    //method to stop the game until the user enters something
    public static void anythingToContinue() throws InterruptedException, DeepLException{

        TextResult result = translator.translateText("\nEnter anything to continue...", null, language);
        System.out.println(result.getText());
        scanner.next();
    }

    //start game
    public static void startGame() throws DeepLException, InterruptedException {
        boolean named = false;
        String name;
        //print title screen
        clearConsole();
        System.out.println("DUNGEON GAME");
        System.out.println("                                                                                                                                                                                             \n" +
                "                                               .#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                                       ./@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*                                                \n" +
                "                                         %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                        *                   /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*                                         \n" +
                "                                    &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                        .&@@@% /@*                   .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/                                    \n" +
                "                                &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                           ,,  .@@@@@@                    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/                                \n" +
                "                             @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                                /@@@@@@@@@                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                             \n" +
                "                          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*                              #@@@@@@@@@@@@@                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&                          \n" +
                "                       &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                             #@@@@@&   ,&@@@@@                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                       \n" +
                "                     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/                            %@@@@@          @%                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%                     \n" +
                "                   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%                            @@@@@.                                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%                   \n" +
                "                 @@@%.        .%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                           #@@@@@,                               (@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(         *&@@*                 \n" +
                "               ,.                  .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                          @@@@@@@                              ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                   (                \n" +
                "                                      %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                       .@@@@@@@%                            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                      \n" +
                "                                        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&                    &@@@@@@@@@/                         @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#                                        \n" +
                "                                         @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.              @@@@@@@@@@@@@*   (%               (@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                                         \n" +
                "                                          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                          \n" +
                "                                          @@#         (@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&*        ,&@@                                          \n" +
                "                                                           @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%                                                           \n" +
                "                                                             %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.  %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                                              \n" +
                "                                                               @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.  @@@@@@@@@@@@@&@@@@@@@@@@@@@@@                                                               \n" +
                "                                                                @@@@@.              @@@@@@@@@@@@@@@@@@@@@   @@@@                  #@@@@@                                                                \n" +
                "                                                                &(                     ,@@@@@@@@@@@@@@@@@  &*                         @,                                                                \n" +
                "                                                                                          @@@@@@@@@@@@@@                                                                                                \n" +
                "                                                                                           (@@@@@@@@@@@%                                                                                                \n" +
                "                                                                                            (@@@@@@@@@@                                                                                                 \n" +
                "                                                                                             @@@@@@@@@                                                                                                  \n" +
                "                                                                                             @@@@@@@@.                                                                                                  \n" +
                "                                                                                             @@@@@@@,                                                                                                   \n" +
                "                                                                                            @@@@@@@/                                                                                                    \n" +
                "                                                                                           /@@@@@@#                                                                                                     \n" +
                "                                                                                           @@@@@@@                                                                                                      \n" +
                "                                                                                           @@@@@@#                                                                                                      \n" +
                "                                                                                           %@@@@@&                               @&                                                                     \n" +
                "                                                                                            @@@@@@                            @@@@                                                                      \n" +
                "                                                                                             @@@@@@.                      /(/,.@@                                                                       \n" +
                "                                                                                              %@@@@@@                        @@%                                                                        \n" +
                "                                                                                                %@@@@@@(                  /@@&                                                                          \n" +
                "                                                                                                   @@@@@@@@&*        *&@@@@                                                                             \n" +
                "                                                                                                       /@@@@@@@@@@@@@@/                                                                                 ");
        translate();
        anythingToContinue();

        //getting player name
        do{
            clearConsole();
            printHeading("What is your name?");
            name = scanner.nextLine();
            if(name.isBlank()) {
            }
            else {
                clearConsole();
                printHeading("Your name is " + name + ".\nIs that correct?");
                TextResult result = translator.translateText("(1) Yes!\n(2) No, I want to change my name.", null, language);
                System.out.println(result.getText());
                int input = readChoice("-> ", 2);
                if (input == 1)
                    named = true;
            }
        } while (!named);

        //create new player object with name
        player = new Player(name, 100);

        //setting the game to the running condition so the game loop can continue
        isRunning = true;

        //start main game loop
        gameLoop();
    }

    //method to continue the game
    public static void continueJourney() throws DeepLException, InterruptedException {
        createBattle();
    }

    //printing character info
    public static void characterInfo() throws DeepLException, InterruptedException {
        clearConsole();
        printHeading("CHARACTER INFO");
        TextResult result = translator.translateText(player.name + "\tHP: " + player.getHp() + "/" + player.maxHp, null, language);
        System.out.println(result.getText());
        TextResult result2 = translator.translateText("Health Potions: " + player.getNumPotions(), null, language);
        System.out.println(result2.getText());
        printSeperator(20);

        anythingToContinue();
    }

    //creating random battle
    public static void createBattle() throws DeepLException, InterruptedException {
        clearConsole();
        printHeading("You've encountered an evil creature! You'll have to fight it!");
        anythingToContinue();
        //creating new enemy
        new Combat(player, new Monster("John", 20));

    }

    //printing the main menu
    public static void printMenu() throws DeepLException, InterruptedException {

        clearConsole();
        printHeading("MENU");
        TextResult result1 = translator.translateText("Choose an action:", null, language);
        System.out.println(result1.getText());
        printSeperator(20);
        TextResult result2 = translator.translateText("(1) Continue on your adventure", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText("(2) Character Info", null, language);
        System.out.println(result3.getText());
        TextResult result4 = translator.translateText("(3) Save and Quit", null, language);
        System.out.println(result4.getText());
        //TODO: if userTag is 'D', if userTag 'U'
    }

    public static void printLangMenu() throws DeepLException, InterruptedException {
        clearConsole();
        printHeading("MENU");
        System.out.println("Choose a Language:");
        //printSeperator(20);
        System.out.println("(1) EN");
        System.out.println("(2) ES");
        System.out.println("(3) DE");
        System.out.println("(4) IT");
        System.out.println("(5) KO");
        System.out.println("(6) FR");

        //translate();
    }

    public static boolean gameLoop() throws DeepLException, InterruptedException {
        while(isRunning){
            printLangMenu();
            printMenu();
            int input = readChoice("-> ", 3);
            if(input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else if (input == 3)
                isRunning = false;

        }
        return isRunning;
    }

    public static void canReview() throws DeepLException, InterruptedException {
        //usertag = "Demo"; //This can be replaced with the actual "Demo reviewer" tag selected at login
        if(!isRunning && usertag.equals("Demo")){
            writeReview();
        }
    }

    public static void writeReview() throws DeepLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        TextResult result = translator.translateText("Thank you for playing! Please write your review:", null, language);
        System.out.println(result.getText());
        String review = scanner.nextLine();
        String fileName = "review.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(review.getBytes());
            fileOutputStream.close();
            TextResult result2 = translator.translateText("Your review has been saved to " + fileName, null, language);
            System.out.println(result2.getText());
        } catch (IOException e) {
            TextResult result3 = translator.translateText("An error occurred while writing the review to " + fileName, null, language);
            System.out.println(result3.getText());
            e.printStackTrace();
        }
        scanner.close();
    }

    public static String translate() throws DeepLException, InterruptedException {
        printLangMenu();
            int input = readChoice("-> ", 6);
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

}

