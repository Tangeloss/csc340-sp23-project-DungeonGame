package Creatures;

import Main.GameLogic;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import static Main.GameLogic.language;
import static Main.GameLogic.translator;

public class Player extends Creatures.Creature {

    //TODO private int gold;
    private int numPotions;
    private int dungeonLocation;
    public Player(String name, int maxHp){
        super(name, 100);
    }


    @Override
    public int atk(){
        return (int)Math.floor((Math.random()* (25 - 15 + 1) + 15));
    }
    @Override
    public int def(){

        return (int)Math.floor(Math.random()* (25 - 15 + 1) + 5);
    }

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

    public int getNumPotions() {
        return numPotions;
    }

    public void setNumPotions(int numPotions) {
        this.numPotions = numPotions;
    }

    public int getHp() {
        return hp;
    }

    public void setHpMax() {
        this.hp = maxHp;
    }

    public void setDungeonLocation(int dungeonLocation){
        this.dungeonLocation = dungeonLocation;
    }
    public int getDungeonLocation() {
        return dungeonLocation;
    }
}
