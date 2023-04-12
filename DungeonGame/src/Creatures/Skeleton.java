package Creatures;

public class Skeleton extends Monster {


    public Skeleton(String name, int maxHp) {
        super(name, 20);
    }

    @Override
    public int atk() {
        return (int)Math.floor((Math.random()* (25 - 15 + 1) + 15));
    }

    @Override
    public int def() {
        return (int)Math.floor((Math.random()* (10 - 5 + 1) + 5));
    }
}
