package Main;
import Creatures.Player;

import java.util.Scanner;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);

    static Player player;

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
    public static void printSpace(int n){
        for(int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    //Method to print a heading
    public static void printHeading(String title){
        printSpace(30);
        System.out.println(title);
        printSpace(30);
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
            name = scanner.next();
            clearConsole();
            printHeading("Your name is " + name + ".\nIs that correct?");
            System.out.println("(1) Yes!\n(2) No, I want to change my name.");
            int input = readChoice("-> ", 2);
            if(input == 1)
                named = true;
        } while (!named);

        //create new player object with name
        player = new Player(name, 100);

        //start main game loop
        //gameLoop();
    }

}
