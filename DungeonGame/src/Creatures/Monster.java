package Creatures;

public abstract class Monster extends Creature{

    public Monster(String name, int maxHp){
        super(name, maxHp);
    }

    public abstract int atk();
    public abstract int def();

    /*
    //enemy specific attack and defend statistics
    @Override
    public int atk() {
        return (int)Math.floor(Math.random()* (25 - 15 + 1) + 15);
    }

    @Override
    public int def() {
        return (int)Math.floor(Math.random()* (25 - 15 + 1) + 5);
    }

     */
}
