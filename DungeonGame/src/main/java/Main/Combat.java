package Main;

import Creatures.Monster;
import Creatures.Player;
import com.deepl.api.DeepLException;

/**
 * Helper class that allows for the passing of a Player and Monster object. These two objects stay inside the class
 * until either one of them are reduced to 0 HP.
 */
public class Combat {

    /**
     * @param player Player object in combat
     * @param monster Monster object in combat
     * @throws DeepLException
     * @throws InterruptedException
     *
     * takes in a monster and player. Most of the combat is spent here while one of the creatures is still alive.
     */
    public Combat(Player player, Monster monster) throws DeepLException, InterruptedException {
        while (true) {
            GameLogic.clearConsole();
            GameLogic.printHeading(monster.name + "\nHP: " + monster.hp + "/" + monster.maxHp);
            GameLogic.printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHp);
            System.out.println("Choose an action:");
            GameLogic.printSeperator(20);
            System.out.println("(1) Attack\n(2) Use a potion\n(3) Run Away");
            int input = GameLogic.readChoice("-> ", 3);
            //react accordingly based on user input
            if (input == 1) {
                //calculate damage and damage taken
                System.out.println("You attack the " + monster.name);
                int damage = player.atk() - monster.def();
                int damageTaken = monster.atk() - player.def();
                //check for negative damage
                if (damage < 0)
                    damage = 0;
                if (damageTaken < 0)
                    damageTaken = 0;
                monster.hp -= damage;
                player.hp -= damageTaken;
                printStatus(damage, damageTaken, monster.name);
                //check if player's hp below 0
                if (monster.hp <= 0) {
                    GameLogic.clearConsole();
                    GameLogic.printHeading("You defeated the " + monster.name + "!");
                    GameLogic.anythingToContinue();
                    break;
                }
            } else if (input == 2) {
                player.drinkPot();
                int damageTaken = monster.atk() - player.def();
                //check for negative damage
                if (damageTaken < 0)
                    damageTaken = 0;
                player.hp -= damageTaken;
                printStatus(damageTaken, monster.name);
            } else if (input == 3) {
                GameLogic.clearConsole();
                System.out.println("Hope that " + monster.name + " likes the look of your heels, " +
                        "because that's all it sees as you turn and run!");
                GameLogic.anythingToContinue();
                break;
            }

            //check if the player is dead
            if (player.hp <= 0) {
                playerDied();
                break;
            }
        }
    }

    /**
     * @param damageDealt takes in the amount of damage dealt to a monster
     * @param damageTaken takes in the amount of damage dealt to the Player
     * @param monsterName takes in the monster's name for communicating to the user.
     *
     * Overloaded, allows the player to see how much damage is dealt to the player and monster when the attack action
     * is selected in the menu
     */
    public static void printStatus ( int damageDealt, int damageTaken, String monsterName){
        GameLogic.clearConsole();
        GameLogic.printHeading("DAMAGE");
        System.out.println("You dealt " + damageDealt + " damage to the " + monsterName + ".");
        GameLogic.printSeperator(15);
        System.out.println("The " + monsterName + " dealt " + damageTaken + " damage to you.");
        GameLogic.anythingToContinue();
    }

    /**
     * @param damageTaken takes in the amount of damage dealt to the Player.
     * @param monsterName takes in the monster's name for communicating to the user.
     *
     * Overloaded, allows the Player to see how much damage is dealt to them. Used when the player selects to drink
     * a potion.
     */
    public static void printStatus(int damageTaken, String monsterName) {
        GameLogic.clearConsole();
        GameLogic.printHeading("DAMAGE");
        System.out.println("The " + monsterName + " dealt " + damageTaken + " damage to you.");
        GameLogic.anythingToContinue();
    }

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * Boolean that says if the player has died or not. Set to True if hp is less than or equal to zero. False otherwise.
     */
    public static void playerDied() throws DeepLException, InterruptedException {
        Story.deathScreen();
        GameLogic.isRunning = false;
    }

}