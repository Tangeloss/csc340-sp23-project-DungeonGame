package Creatures;

public class Player extends Creature{

    private boolean isAlive;
    private int numPotions;

    public Player(String name, int maxHp){
        super(name, 100);
    }

    @Override
    public int atk(){

        return 0;
    }
    @Override
    public int def(){

        return 0;
    }

    public void drinkPot(){
        setNumPotions(numPotions-1);
        setHpMax();
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
}
