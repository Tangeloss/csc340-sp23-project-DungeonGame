package main.java.Main;

import Creatures.Monster;
import main.java.Creatures.Player;
import com.deepl.api.DeepLException;

public class Combat {

    public Combat(Player player, Monster monster) throws DeepLException, InterruptedException {
        while (true) {
            main.java.Main.GameLogic.clearConsole();
            main.java.Main.GameLogic.printHeading(monster.name + "\nHP: " + monster.hp + "/" + monster.maxHp);
            main.java.Main.GameLogic.printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHp);
            System.out.println("Choose an action:");
            main.java.Main.GameLogic.printSeperator(20);
            System.out.println("(1) Attack\n(2) Use a potion\n(3) Run Away");
            int input = main.java.Main.GameLogic.readChoice("-> ", 3);
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
                    main.java.Main.GameLogic.clearConsole();
                    main.java.Main.GameLogic.printHeading("You defeated the " + monster.name + "!");
                    main.java.Main.GameLogic.anythingToContinue();
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
                main.java.Main.GameLogic.clearConsole();
                System.out.println("Hope that " + monster.name + " likes the look of your heels, " +
                        "because that's all it sees as you turn and run!");
                main.java.Main.GameLogic.anythingToContinue();
                break;
            }

            //check if the player is dead
            if (player.hp <= 0) {
                playerDied();
                break;
            }
        }
    }

    public static void printStatus ( int damageDealt, int damageTaken, String monsterName){
        main.java.Main.GameLogic.clearConsole();
        main.java.Main.GameLogic.printHeading("DAMAGE");
        System.out.println("You dealt " + damageDealt + " damage to the " + monsterName + ".");
        main.java.Main.GameLogic.printSeperator(15);
        System.out.println("The " + monsterName + " dealt " + damageTaken + " damage to you.");
        main.java.Main.GameLogic.anythingToContinue();
    }
    public static void printStatus(int damageTaken, String monsterName) {
        main.java.Main.GameLogic.clearConsole();
        main.java.Main.GameLogic.printHeading("DAMAGE");
        System.out.println("The " + monsterName + " dealt " + damageTaken + " damage to you.");
        main.java.Main.GameLogic.anythingToContinue();
    }

    public static void playerDied() throws DeepLException, InterruptedException {
        main.java.Main.Story.deathScreen();
        main.java.Main.GameLogic.isRunning = false;
    }

}