package Creatures;

/**
 * One of two variants of Monsters. Orc possesses higher defense but lower attack. Harder to get through than
 * the skeleton.
 */
public class Orc extends Creatures.Monster {

    /**
     * @param name Names the creature an Orc
     * @param maxHp Passes 50 hp to set the maximum
     */
    public Orc(String name, int maxHp) {
        super(name, 50);
    }

    /**
     * @return overrides superclass to return a random number between 10 and 20 for attack
     */
    @Override
    public int atk() {
        return (int)Math.floor(Math.random()* (20 - 10 + 1) + 10);
    }

    /**
     * @return overrides superclass to return a random number between 10 and 15 for defense
     */
    @Override
    public int def() {
        return (int)Math.floor((Math.random()* (15 - 10 + 1) + 10));
    }
}
