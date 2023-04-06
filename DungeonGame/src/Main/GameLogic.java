package Main;
import Creatures.Monster;
import Creatures.Player;
import Dungeon.Dungeon;
import Dungeon.Room;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);
    static Player player;
    public static boolean isRunning;
    //Story Elements, dungeon location
    public static int place;
    public static String usertag;
    //Dungeon is created with graph logic
    public static Dungeon dungeon = Dungeon.createDungeon(22);

    //printing the main menu
    public static void printMenu(){
        clearConsole();
        System.out.println(player.getDungeonLocation());
        System.out.println("Description:");
        System.out.println(dungeon.getAdjList()[place].element().getDescription());
        dungeon.print();
        printHeading("MENU");
        System.out.println("Choose an action:");
        printSeperator(20);
        System.out.println("(1) Continue on your adventure");
        System.out.println("(2) Character Info");
        //TODO: if userTag is 'D', if userTag 'U'
        System.out.println("(3) Save and Quit");
    }

    public static boolean gameLoop(){
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

    //method to continue the game
    public static void continueJourney(){
        clearConsole();
        System.out.println(player.getDungeonLocation());
        if(player.getDungeonLocation() == 0){ //if the player is in the starting room
            System.out.println("You stand at the entryway of the dank and dark dungeon.");
            place = 1;
            dungeon.getAdjList()[0].element().setPlayerHere(true);
            anythingToContinue();
        } else if (player.getDungeonLocation() == 22) { //if the player is at the final room
            Story.winScreen();
            //TODO Add score calculation based on gold gained from monster battles
            isRunning = false;
        } else { //the player is in room 1 - 20
            //check if a monster is there, if so initiate combat
            boolean monsterHere = dungeon.getAdjList()[place - 1].element().isMonsterHere();
            boolean playerHere = dungeon.getAdjList()[place - 1].element().isPlayerHere();
            if (playerHere && monsterHere){
                //TODO createBattle(); REMEMBER TO HAVE THE NEW MONSTER BE GENERATED IN THAT METHOD!
            }
            if(dungeon.getAdjList()[place].size() == 2){
                System.out.println("In front of you are two doors, one to the left and one to the right. Which " +
                        "direction would you like to move?");
                System.out.println("(1) Move to the door on the left");
                System.out.println("(2) Move to the door on the right");
                int direction = readChoice("-> ", 2);
                System.out.println("You move into the door on your " + (direction == 1 ? "left" : "right"));
                if (direction == 1){
                    place = (dungeon.getAdjList()[place].get(0).getId() - 1);
                    dungeon.getAdjList()[place].get(0).setPlayerHere(true);
                } else {
                    //problem with getting the place at index 1...
                    place = (dungeon.getAdjList()[place].get(1).getId() - 1);
                    dungeon.getAdjList()[place].get(1).setPlayerHere(true);
                }
            } else {
                System.out.println("It looks from here there is only one way to go. You move to that room...");
                place = dungeon.getAdjList()[place - 1].get(0).getId();
                dungeon.getAdjList()[place - 1].get(0).setPlayerHere(true);
                anythingToContinue();

            }
        }
        player.setDungeonLocation(place);
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

    //creating random battle
    public static void createBattle(){
        clearConsole();
        printHeading("You've encountered an evil creature! You'll have to fight it!");
        anythingToContinue();
        //generate a monster, start combat with that monster
        new Combat(player, new Monster("John", 20));

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

    //start game
    public static Player startGame(String name){

        //print title screen and story
        clearConsole();
        Story.printIntro();
        anythingToContinue();

        if(name == "")
            nameCharacter();


        //create new player object with name
        player = new Player(name, 100);

        //setting the game to the running condition so the game loop can continue
        isRunning = true;

        //start main game loop
        gameLoop();

        return player;
    }

    public static String nameCharacter(){
        boolean named = false;
        String name;
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

        return name;
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
}

