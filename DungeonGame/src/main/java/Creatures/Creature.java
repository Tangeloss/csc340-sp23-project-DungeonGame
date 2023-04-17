package Creatures;

/**
 * An abstract class which is extended by Monster, and Player. All creatures have these in common.
 */
public abstract class Creature {

    //Attributes all creatures have
    public String name;
    public int hp, maxHp;
    public abstract int atk();
    public abstract int def();

    /**
     * @param name taken in to name the creature based on input or generation
     * @param maxHp sets the maxHP the creature in question can have, useful for measuring damage or status
     */
    public Creature(String name, int maxHp){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    /**
     * @return name of the creature
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets name of creature
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return current hp of a creature
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp passed to alter the current hp of a creature
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
}

