package Main;

import Creatures.Monster;
import Creatures.Player;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import static Main.GameLogic.language;
import static Main.GameLogic.translator;

public class Combat {

    public Combat(Player player, Monster monster) throws DeepLException, InterruptedException {
        while (true) {
            GameLogic.clearConsole();
            GameLogic.printHeading(monster.name + "\nHP: " + monster.hp + "/" + monster.maxHp);
            GameLogic.printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHp);
            TextResult result = translator.translateText("Choose an action:", null, language);
            System.out.println(result.getText());
            GameLogic.printSeperator(20);
            TextResult result1 = translator.translateText("(1) Attack\n(2) Use a potion\n(3) Run Away\n(4) Die", null, language);
            System.out.println(result1.getText());
            int input = GameLogic.readChoice("-> ", 4);
            //react accordingly based on user input
            if (input == 1) {
                //calculate damage and damage taken
                TextResult result2 = translator.translateText("You attack the " + monster.name, null, language);
                System.out.println(result2.getText());
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
                TextResult result3 = translator.translateText("Hope that " + monster.name + " likes the look of your heels, " +
                        "because that's all it sees as you turn and run!", null, language);
                System.out.println(result3.getText());
                GameLogic.anythingToContinue();
                break;
            } else if (input == 4) {
                player.hp = 0;
            }

            //check if the player is dead
            if (player.hp <= 0) {
                playerDied();
                break;
            }
        }
    }

    public static void printStatus ( int damageDealt, int damageTaken, String monsterName) throws DeepLException, InterruptedException {
        GameLogic.clearConsole();
        GameLogic.printHeading("DAMAGE");

        TextResult result = translator.translateText("You dealt " + damageDealt + " damage to the " + monsterName + ".", null, language);
        System.out.println(result.getText());
        GameLogic.printSeperator(15);
        TextResult result1 = translator.translateText("The " + monsterName + " dealt " + damageTaken + " damage to you.", null, language);
        System.out.println(result1.getText());
        GameLogic.anythingToContinue();
    }
    public static void printStatus(int damageTaken, String monsterName) throws DeepLException, InterruptedException {
        GameLogic.clearConsole();
        GameLogic.printHeading("DAMAGE");
        TextResult result = translator.translateText("The " + monsterName + " dealt " + damageTaken + " damage to you.", null, language);
        System.out.println(result.getText());
        GameLogic.anythingToContinue();
    }

    public static void playerDied() throws DeepLException, InterruptedException {
        Story.deathScreen();
        GameLogic.isRunning = false;
    }

}