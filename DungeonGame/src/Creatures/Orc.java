package Creatures;

public class Orc extends Monster{


    public Orc(String name, int maxHp) {
        super(name, 50);
    }

    @Override
    public int atk() {
        return (int)Math.floor(Math.random()* (20 - 10 + 1) + 10);
    }

    @Override
    public int def() {
        return (int)Math.floor((Math.random()* (15 - 10 + 1) + 10));
    }
}
