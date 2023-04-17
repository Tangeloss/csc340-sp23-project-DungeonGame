package Creatures;

import Main.GameLogic;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import static Main.Login.language;
import static Main.Login.translator;

/**
 * Player is the creature the user controls, has a lot more methods to help track status and location
 */
public class Player extends Creatures.Creature {

    private int numPotions;
    private int dungeonLocation;

    /**
     * @param name Names the character based on passed values
     * @param maxHp sets max Hp, all players have a base 100 hp
     */
    public Player(String name, int maxHp){
        super(name, 100);
    }


    /**
     * @return Player hits harder than the Monsters, generates a random value between 15 and 25
     */
    @Override
    public int atk(){
        return (int)Math.floor((Math.random()* (25 - 15 + 1) + 15));
    }

    /**
     * @return Player is a little bit harder to hit as well, generates a random value between 15 and 25
     */
    @Override
    public int def(){

        return (int)Math.floor(Math.random()* (25 - 15 + 1) + 5);
    }

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * Allows player to drink a potion and set themselves back to full health.
     * If a Player tries to drink a potion while none are in the inventory, will fail and pass their turn.
     */
    public void drinkPot() throws DeepLException, InterruptedException {
        GameLogic.clearConsole();
        if(numPotions >= 1) {
            setNumPotions(numPotions - 1);
            setHpMax();
            TextResult result = translator.translateText("Tastes like grandma's chicken soup mixed with a pack of Hubba Bubba.", null, language);
            System.out.println(result.getText());
        } else {
            TextResult result1 = translator.translateText("You reach into your pack but... Looks like you'll have to rely " +
                    "on your wit and charm for this one. (0 Potions in inventory)", null, language);
            System.out.println(result1.getText());
        }
        GameLogic.anythingToContinue();
    }

    /**
     * @return returns number of potions currently in the player's inventory
     */
    public int getNumPotions() {

        return numPotions;
    }

    /**
     * @param numPotions sets the number of potions available in the player's inventory
     */
    public void setNumPotions(int numPotions) {

        this.numPotions = numPotions;
    }

    /**
     * @return returns the amount of hp the Player is currently at
     */
    public int getHp() {

        return hp;
    }

    /**
     * sets the maximum hp of a Player
     */
    public void setHpMax() {

        this.hp = maxHp;
    }

    /**
     * @param dungeonLocation passed to change where the player is in the dungeon at any given point.
     */
    public void setDungeonLocation(int dungeonLocation){

        this.dungeonLocation = dungeonLocation;
    }

    /**
     * @return Finds where the player is and returns it with an integer value.
     * Rooms tracked by that value can further inform conditionals.
     */
    public int getDungeonLocation() {

        return dungeonLocation;
    }
}
