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

/**
 * Where the majority of the game takes place. Manages the dungeon, the player's status, and navigation throughout
 * the dungeon. Allows the player to select directions and maintains story elements.
 */
public class GameLogic {

    static Scanner scanner = new Scanner(System.in);
    static Creatures.Player player;

    /**
     * All actions managed through this boolean. Tells the code whether or not the game needs to maintain running.
     * Can be set to false through the player dying or choosing to save and quit.
     */
    public static boolean isRunning;
    public static int place;
    static final int NUM_ROOMS = 22;
    public static Dungeon dungeon = Dungeon.createDungeon(NUM_ROOMS);

    /**
     * Main menu of the game, allows Player to navigate, see their stats, or save and quit.
     */
    public static void printMenu() throws DeepLException, InterruptedException {
        clearConsole();
        System.out.println(player.getDungeonLocation());
        printHeading("MENU");
        TextResult result = translator.translateText("Choose an action:", null, language);
        System.out.println(result.getText());

        printSeperator(20);
        TextResult result1 = translator.translateText("(1) Continue on your adventure", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("(2) Character Info", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText("(3) Save and Quit", null, language);
        System.out.println(result3.getText());

    }

    /**
     * @return if the game is running, allows the game to continue running while set to true.
     * @throws DeepLException
     * @throws InterruptedException
     */
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

    /**
     * @param prompt String prompt displayed to the player, gives status of choices
     * @param userChoices The number of choices a user gets to make for any given prompt
     * @return The user's choice with the displayed prompt.
     */
    public static int readChoice(String prompt, int userChoices) throws DeepLException, InterruptedException {
        int input = 0;

        do{
            System.out.print(prompt);
            try{
                input = Integer.parseInt(scanner.next());
                if (input < 0 || input > userChoices){
                    TextResult result = translator.translateText("Invalid choice, please enter an integer between 1 and "+ userChoices, null, language);
                    System.out.println(result.getText());
                }
            } catch (Exception e){
                input = -1;
                TextResult result = translator.translateText("Please enter an Integer!", null, language);
                System.out.println(result.getText());
            }


        } while(input < 1 || input > userChoices);

        return input;
    }

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * Allows the player to continue through the dungeon. If the dungeon has a branching path, will query for left or
     * right movement. If the dungeon has only one path it will allow for traversal forward. If the player is at the
     * beginning, will grant them a health potion and move them to room 1. If the player is in the final room, will
     * display the win screen and exit the game.
     */
    public static void continueJourney() throws DeepLException, InterruptedException {
        place = player.getDungeonLocation();
        clearConsole();
        if(player.getDungeonLocation() == 0){ //if the player is in the starting room
            TextResult result = translator.translateText("You stand at the entryway of the dank and dark dungeon. \nYou pat your pockets," +
                    " feeling the potion you decided to bring along.", null, language);
            System.out.println(result.getText());

            player.setNumPotions(1); //give a new character a potion
            place = 1;
            dungeon.getAdjList()[0].element().setPlayerHere(true);
            anythingToContinue();
        } else { //the player is in room 1 - 20
            if(dungeon.getAdjList()[place].size() == 2){
                TextResult result = translator.translateText("In front of you are two doors, one to the left and one to the right. Which " +
                        "direction would you like to move?", null, language);
                System.out.println(result.getText());
                TextResult result1 = translator.translateText("(1) Move to the door on the left", null, language);
                System.out.println(result1.getText());
                TextResult result2 = translator.translateText("(2) Move to the door on the right", null, language);
                System.out.println(result2.getText());

                int direction = readChoice("-> ", 2);
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

    /**
     * @param place takes in the current room identifier, which room the character is in or moving to.
     * @param direction which direction the character chose to go in the case of a branching path.
     * @throws DeepLException
     * @throws InterruptedException
     *
     * Will instantiate combat if the check for the room's identifier is True for both the player and the monster.
     * Creates a Combat instance if the player and a monster are in the same room.
     */
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

    /**
     * Prints the character's information. HP and current number of health potions are supplied in an easy to read
     * format.
     */
    public static void characterInfo() throws DeepLException, InterruptedException {
        clearConsole();
        printHeading("CHARACTER INFO");
        TextResult result = translator.translateText(player.name + "\tHP: " + player.getHp() + "/" + player.maxHp, null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText("Health Potions: " + player.getNumPotions(), null, language);
        System.out.println(result1.getText());

        printSeperator(20);

        anythingToContinue();
    }

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * creates an instance of a battle between a player and a monster. Generates a skeleton or an orc depending
     * on a simple coin flip algorithm. Passes the player and monster to the Combat class to resolve combat
     */
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

    /**
     * @param name takes in the player's name
     * @param currentHp takes in the player's current hp
     * @param numPotion takes in the player's current number of health potions
     * @param dungeonLocation takes in the location in the dungeon of the player.
     * @return the player object from this instance for saving to player's file.
     * @throws DeepLException
     * @throws InterruptedException
     *
     * takes in the statistics of a player. If they are a new player, the game will set them to the dungeon entrance
     * with base statistics. If they are a returning player, it will set them to whatever the last place they
     * saved and quit from.
     *
     * The dungeon will be built around the player, each instance of the dungeon is the exact same, and the player
     * will never have to encounter the same monster again.
     */
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

    /**
     * @return the name of the character, taken from the user and given to the player object.
     *
     * Allows the user to name their character.
     */
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
                TextResult result = translator.translateText("(1) Yes!\n(2) No, I want to change my name.", null, language);
                System.out.println(result.getText());

                int input = readChoice("-> ", 2);
                if (input == 1)
                    named = true;
            }
        } while (!named);

        return name;
    }

    /**
     * Clears the console of all text, makes for a better user experience.
     */
    public static void clearConsole(){
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    /**
     * @param n taken in for the number of hyphens to print.
     *
     * Allows for the creation of separators around certain lines of text from the printHeading method
     */
    public static void printSeperator(int n){
        for(int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    /**
     * @param title text to print the heading for a menu or instance in the game.
     */
    public static void printHeading(String title) throws DeepLException, InterruptedException {
        printSeperator(30);
        TextResult result = translator.translateText(title, null, language);
        System.out.println(result.getText());

        printSeperator(30);
    }

    /**
     * Method to stop the run of the game until a player enters a value to continue. Allows the player to read and
     * take in information before continuing.
     */
    public static void anythingToContinue() throws DeepLException, InterruptedException {
        TextResult result = translator.translateText("\nEnter anything to continue...", null, language);
        System.out.println(result.getText());

        scanner.next();
    }
}
