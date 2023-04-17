package Creatures;

/**
 * Monster comes in one of two varieties, Skeleton and Orc.
 * By abstracting the Monster class we can generate two different subclasses of monsters with different statistics.
 */
public abstract class Monster extends Creatures.Creature {

    /**
     * @param name passed to set the monster's name using the superclass' methods
     * @param maxHp passed to set the monster's max hp using the superclass' methods
     */
    public Monster(String name, int maxHp){
        super(name, maxHp);
    }

    public abstract int atk();
    public abstract int def();

}
