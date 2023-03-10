package Main;

public class Game {
    public static void main(String[] args) {

        new Game();
    }

    public Game(){

        GameLogic.printHeading("Testing helper methods!");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        int input = GameLogic.readInt("Enter 1, 2, or 3: ", 3);
        System.out.println("You chose number " + input);

    }
}

