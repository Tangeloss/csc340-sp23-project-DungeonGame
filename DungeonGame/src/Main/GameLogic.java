package Main;
import Creatures.Player;

import java.util.Scanner;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);

    static Player player;

    public static boolean isRunning;

    //Story Elements, dungeon location
    public static int place = 0;


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
    public static void anythingToContinue(){
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }

    //start game
    public static void startGame(){
        boolean named = false;
        String name;
        //print title screen
        clearConsole();
        System.out.println("DUNGEON GAME");
        System.out.println("                                                                                                                                                                                                        \n" +
                "                                                                                                                                                                                                        \n" +
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
                "                                                                                                       /@@@@@@@@@@@@@@/                                                                                 \n" +
                "                                                                                                                                                                                                        \n" +
                "                                                                                                                                                                                                        \n" +
                "                                                                                                                                                                                                        ");
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

        //print the story's intro
        Story.printIntro();

        //create new player object with name
        player = new Player(name, 100);

        //setting the game to the running condition so the game loop can continue
        isRunning = true;

        //start main game loop
        gameLoop();
    }

    //method to continue the game
    public static void continueJourney(){

    }

    //printing character info
    public static void characterInfo(){
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println(player.name + "\tHP: " + player.getHp() + "/" + player.maxHp);
        System.out.println("Health Potions: " + player.getNumPotions());
        printSeperator(20);

        anythingToContinue();
    }

    //printing the main menu
    public static void printMenu(){
        clearConsole();
        printHeading("MENU");
        System.out.println("Choose an action:");
        printSeperator(20);
        System.out.println("(1) Continue on your adventure");
        System.out.println("(2) Character Info");
        System.out.println("(3) Save and Quit");
    }

    public static void gameLoop(){
        while(isRunning == true){
            printMenu();
            int input = readChoice("-> ", 3);
            if(input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else if (input == 3)
                isRunning = false;
        }
    }
}
