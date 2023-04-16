package Creatures;

public abstract class Monster extends Creatures.Creature {

    public Monster(String name, int maxHp){
        super(name, maxHp);
    }

    public abstract int atk();
    public abstract int def();

}