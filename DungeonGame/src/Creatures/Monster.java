package Creatures;

public class Monster extends Creature{

    public Monster(String name, int maxHp){
        super(name, 100);
    }

    @Override
    public int atk() {
        return 0;
    }

    @Override
    public int def() {
        return 0;
    }
}
