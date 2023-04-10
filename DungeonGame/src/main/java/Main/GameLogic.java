package Main;
import Creatures.Monster;
import Creatures.Player;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);

    static Player player;

    public static final String authKey = "e6f59a4c-7dc6-d0a5-c864-765876c0b9a0:fx";

    static Translator translator = new Translator(authKey);;

    public static boolean isRunning;

    //Story Elements, dungeon location
    public static int place = 0;

    public static String usertag;


    //method to get user input from console
    public static int readChoice(String prompt, int userChoices){
        int input = 0;

        do{
            System.out.print(prompt);
            try{
                input = Integer.parseInt(scanner.next());
                if (input < 0 || input > userChoices){
                    System.out.println("Invalid choice, please enter an integer between 1 and " + userChoices);
                }
            } catch (Exception e){
                input = -1;
                System.out.println("Please enter an Integer!");
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
    public static void printHeading(String title){
        printSeperator(30);
        System.out.println(title);
        printSeperator(30);
    }

    //method to stop the game until the user enters something
    public static void anythingToContinue() throws InterruptedException, DeepLException{
        TextResult result = translator.translateText("\nEnter anything to continue...", null, "ko");
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
        anythingToContinue();

        //getting player name
        do{
            clearConsole();
            printHeading("What is your name?");
            name = scanner.nextLine();
            if(name.isBlank())
                continue;
            else {
                clearConsole();
                printHeading("Your name is " + name + ".\nIs that correct?");
                System.out.println("(1) Yes!\n(2) No, I want to change my name.");
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
        System.out.println(player.name + "\tHP: " + player.getHp() + "/" + player.maxHp);
        System.out.println("Health Potions: " + player.getNumPotions());
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
    public static void printMenu(){
        clearConsole();
        printHeading("MENU");
        System.out.println("Choose an action:");
        printSeperator(20);
        System.out.println("(1) Continue on your adventure");
        System.out.println("(2) Character Info");
        //TODO: if userTag is 'D', if userTag 'U'
        System.out.println("(3) Save and Quit");
    }

    public static boolean gameLoop() throws DeepLException, InterruptedException {
        while(isRunning){
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

    public static void canReview(){
        usertag = "Demo"; //This can be replaced with the actual "Demo reviewer" tag selected at login
        if(!isRunning && usertag.equals("Demo")){
            writeReview();
        }
    }


    public static void writeReview() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Thank you for playing! Please write your review:");
        String review = scanner.nextLine();
        String fileName = "review.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(review.getBytes());
            fileOutputStream.close();
            System.out.println("Your review has been saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the review to " + fileName);
            e.printStackTrace();
        }
        scanner.close();
    }
}

