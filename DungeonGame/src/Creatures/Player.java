package Creatures;

import Main.GameLogic;

public class Player extends Creature{

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

    public void drinkPot(){
        GameLogic.clearConsole();
        if(numPotions >= 1) {
            setNumPotions(numPotions - 1);
            setHpMax();
            System.out.println("Tastes like grandma's chicken soup mixed with a pack of Hubba Bubba.");
        } else {
            System.out.println("You reach into your pack but... Looks like you'll have to rely " +
                    "on your wit and charm for this one. (0 Potions in inventory)");
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

    //TODO public int getGold() & setGold()
}
