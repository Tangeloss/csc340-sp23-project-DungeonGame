package Creatures;

/**
 * A variant of Monster. Skeletons are easier to hit and kill than Orcs, but they have higher attack stats,
 * making them be able to hit harder than Orcs.
 */
public class Skeleton extends Creatures.Monster {

    /**
     * @param name passed to name the skeleton "skeleton"
     * @param maxHp passed to set the skeleton's max hp to 20
     */
    public Skeleton(String name, int maxHp) {
        super(name, 20);
    }

    /**
     * @return Can hit a little harder than other Monsters, generates a number between 15 and 25
     */
    @Override
    public int atk() {
        return (int)Math.floor((Math.random()* (25 - 15 + 1) + 15));
    }

    /**
     * @return Cannot defend themselves as well, generates a random number between 5 and 10
     */
    @Override
    public int def() {
        return (int)Math.floor((Math.random()* (10 - 5 + 1) + 5));
    }
}