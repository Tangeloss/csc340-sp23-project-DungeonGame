package Creatures;
public abstract class Creature {

    //Attributes all creatures have
    public String name;
    public int hp, maxHp;
    public abstract int atk();
    public abstract int def();

    //constructor for creature
    public Creature(String name, int maxHp){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }
}
