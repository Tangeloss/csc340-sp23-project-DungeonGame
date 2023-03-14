package Creatures;

public class Player extends Creature{

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


}
