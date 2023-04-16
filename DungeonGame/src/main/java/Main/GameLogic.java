package Main;
import Creatures.Monster;
import Creatures.Orc;
import Creatures.Player;
import Creatures.Skeleton;
import Dungeon.Dungeon;
import Dungeon.Room;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import static Main.Login.language;
import static Main.Login.translator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);
    static Creatures.Player player;
    public static boolean isRunning;
    //Story Elements, dungeon location
    public static int place;
    static final int NUM_ROOMS = 22;
    public static Dungeon dungeon = Dungeon.createDungeon(NUM_ROOMS);

    //printing the main menu
    public static void printMenu() throws DeepLException, InterruptedException {
        clearConsole();
        System.out.println(player.getDungeonLocation());
        printHeading("MENU");
        //System.out.println("Choose an action:");

        TextResult result = translator.translateText("Choose an action:", null, language);
        System.out.println(result.getText());

        printSeperator(20);
        //System.out.println("(1) Continue on your adventure");
        //System.out.println("(2) Character Info");
        //System.out.println("(3) Save and Quit");

        TextResult result1 = translator.translateText("(1) Continue on your adventure", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("(2) Character Info", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText("(3) Save and Quit", null, language);
        System.out.println(result3.getText());

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

    //method to get user input from console
    public static int readChoice(String prompt, int userChoices) throws DeepLException, InterruptedException {
        int input = 0;

        do{
            System.out.print(prompt);
            try{
                input = Integer.parseInt(scanner.next());
                if (input < 0 || input > userChoices){
                    //System.out.println("Invalid choice, please enter an integer between 1 and " + userChoices);

                    TextResult result = translator.translateText("Invalid choice, please enter an integer between 1 and "+ userChoices, null, language);
                    System.out.println(result.getText());
                }
            } catch (Exception e){
                input = -1;
                //System.out.println("Please enter an Integer!");

                TextResult result = translator.translateText("Please enter an Integer!", null, language);
                System.out.println(result.getText());
            }


        } while(input < 1 || input > userChoices);

        return input;
    }

    //method to continue the game
    public static void continueJourney() throws DeepLException, InterruptedException {
        place = player.getDungeonLocation();
        clearConsole();
        if(player.getDungeonLocation() == 0){ //if the player is in the starting room
            //System.out.println("You stand at the entryway of the dank and dark dungeon. \nYou pat your pockets," +
                    //" feeling the potion you decided to bring along.");

            TextResult result = translator.translateText("You stand at the entryway of the dank and dark dungeon. \nYou pat your pockets," +
                    " feeling the potion you decided to bring along.", null, language);

            System.out.println(result.getText());

            player.setNumPotions(1); //give a new character a potion
            place = 1;
            dungeon.getAdjList()[0].element().setPlayerHere(true);
            anythingToContinue();
        } else { //the player is in room 1 - 20
            if(dungeon.getAdjList()[place].size() == 2){
                //System.out.println("In front of you are two doors, one to the left and one to the right. Which " +
                        //"direction would you like to move?");

                TextResult result = translator.translateText("In front of you are two doors, one to the left and one to the right. Which " +
                        "direction would you like to move?", null, language);
                System.out.println(result.getText());

                //System.out.println("(1) Move to the door on the left");
                //System.out.println("(2) Move to the door on the right");

                TextResult result1 = translator.translateText("(1) Move to the door on the left", null, language);
                System.out.println(result1.getText());
                TextResult result2 = translator.translateText("(2) Move to the door on the right", null, language);
                System.out.println(result2.getText());

                int direction = readChoice("-> ", 2);
                //System.out.println("You move into the door on your " + (direction == 1 ? "left" : "right"));

                TextResult result3 = translator.translateText("You move into the door on your " + (direction == 1 ? "left" : "right"), null, language);
                System.out.println(result3.getText());

                if (direction == 1){
                    //This also has a problem, going to room 2 sets true to room 4 in all instances.
                    dungeon.getAdjList()[place].get(0).setPlayerHere(true);
                    checkForCombat(place, 0);
                    place = (dungeon.getAdjList()[place].get(0).getId());
                } else {
                    dungeon.getAdjList()[place].get(1).setPlayerHere(true);
                    checkForCombat(place, 1);
                    place = (dungeon.getAdjList()[place].get(1).getId());
                }
            } else {
                //System.out.println("It looks from here there is only one way to go. You move to that room...");

                TextResult result = translator.translateText("It looks from here there is only one way to go. You move to that room...", null, language);
                System.out.println(result.getText());

                dungeon.getAdjList()[place].get(0).setPlayerHere(true);
                anythingToContinue();
                checkForCombat(place, 0);
                place = dungeon.getAdjList()[place].get(0).getId();

            }
        }
        player.setDungeonLocation(place);

        if (player.getDungeonLocation() == NUM_ROOMS-1) { //if the player is at the final room
            Story.winScreen();
            isRunning = false;
        }
    }

    public static void checkForCombat(int place, int direction) throws DeepLException, InterruptedException {
        if (direction == 0){
            boolean playerHere = dungeon.getAdjList()[place].get(0).isPlayerHere();
            boolean monsterHere = dungeon.getAdjList()[place].get(0).isMonsterHere();
            if (playerHere && monsterHere) {
                createBattle();
            }
        } else {
            boolean playerHere = dungeon.getAdjList()[place].get(1).isPlayerHere();
            boolean monsterHere = dungeon.getAdjList()[place].get(1).isMonsterHere();
            if (playerHere && monsterHere) {
                createBattle();
            }
        }
    }

    //printing character info
    public static void characterInfo() throws DeepLException, InterruptedException {
        clearConsole();
        printHeading("CHARACTER INFO");
        //System.out.println(player.name + "\tHP: " + player.getHp() + "/" + player.maxHp);
        //System.out.println("Health Potions: " + player.getNumPotions());

        TextResult result = translator.translateText(player.name + "\tHP: " + player.getHp() + "/" + player.maxHp, null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText("Health Potions: " + player.getNumPotions(), null, language);
        System.out.println(result1.getText());

        printSeperator(20);

        anythingToContinue();
    }

    //creating random battle
    public static void createBattle() throws DeepLException, InterruptedException {
        clearConsole();
        printHeading("You've encountered an evil creature! You'll have to fight it!");
        anythingToContinue();
        Random rn = new Random();
        int monsterChoice = rn.nextInt(2);
        if (monsterChoice == 1)
            new Combat(player, new Skeleton("Skeleton", 20));
        else
            new Combat(player, new Orc("Orc", 50));
    }

    public static void writeReview() throws DeepLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Thank you for playing! Please write your review:");

        TextResult result = translator.translateText("Thank you for playing! Please write your review:", null, language);
        System.out.println(result.getText());

        String review = scanner.nextLine();
        String fileName = "review.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(review.getBytes());
            fileOutputStream.close();
            //System.out.println("Your review has been saved to " + fileName);

            TextResult result1 = translator.translateText("Your review has been saved to " + fileName, null, language);
            System.out.println(result1.getText());

        } catch (IOException e) {
            //System.out.println("An error occurred while writing the review to " + fileName);

            TextResult result2 = translator.translateText("An error occurred while writing the review to " + fileName, null, language);
            System.out.println(result2.getText());

            e.printStackTrace();
        }
        scanner.close();
    }

    //start game
    public static Player startGame(String name, int currentHp, int numPotion, int dungeonLocation) throws DeepLException, InterruptedException {

        //print title screen and story
        clearConsole();
        Main.Story.printIntro();
        anythingToContinue();

        player = new Player(name, 100);

        if (dungeonLocation == -1) {
            player.setDungeonLocation(0);
            player.setNumPotions(0);
            player.setName(name);
        } else {
            player.setDungeonLocation(dungeonLocation);
            player.setNumPotions(numPotion);
            player.setName(name);
            player.setHp(currentHp);
        }


        //setting the game to the running condition so the game loop can continue
        isRunning = true;

        //start main game loop
        gameLoop();

        return player;
    }

    public static String nameCharacter() throws DeepLException, InterruptedException {
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
                //System.out.println("(1) Yes!\n(2) No, I want to change my name.");

                TextResult result = translator.translateText("(1) Yes!\n(2) No, I want to change my name.", null, language);
                System.out.println(result.getText());

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
    public static void printHeading(String title) throws DeepLException, InterruptedException {
        printSeperator(30);
        //System.out.println(title);

        TextResult result = translator.translateText(title, null, language);
        System.out.println(result.getText());

        printSeperator(30);
    }

    //method to stop the game until the user enters something
    public static void anythingToContinue() throws DeepLException, InterruptedException {
        //System.out.println("\nEnter anything to continue...");

        TextResult result = translator.translateText("\nEnter anything to continue...", null, language);
        System.out.println(result.getText());

        scanner.next();
    }
}

