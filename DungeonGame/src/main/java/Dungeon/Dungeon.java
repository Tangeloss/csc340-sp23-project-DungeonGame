package Dungeon;

import Creatures.Player;

import java.util.LinkedList;

/**
 * Dungeon is generated every time the game begins, even if the player is returning to a character they already
 * started prior. Dungeon is a Graph maintained by adjacency lists, tracking where a Player can move to, and where
 * Monster rooms need to be populated.
 *
 * Dungeon is made up of Room objects, which hold different identifiers to help track Player and Monster locations.
 */
public class Dungeon {

    private static int numRooms;
    private static LinkedList<Room>[] adjList;

    /**
     * list of rooms where Monsters need to be placed.
     */
    private static int[] monsterRooms = {3, 4, 8, 11, 13, 15, 17};

    /**
     * @param numRooms passed to create that many vertices (or rooms) in the dungeon.
     */
    public Dungeon(int numRooms) {

        this.numRooms = numRooms;
        adjList = new LinkedList[numRooms];
        for (int i = 0; i < numRooms; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * @param dungeon Passed to modify the adjacency list of the dungeon's vertices
     * @param source Room where a path wants to be drawn from
     * @param destination Room where a path wants to be drawn to.
     */
    public static void addPath(Dungeon dungeon, Room source, Room destination) {

        dungeon.adjList[source.getId()].add(destination);

    }

    /*
    public static void print() {
        for (int i = 0; i < numRooms; i++) {
            if (!adjList[i].isEmpty()) {
                System.out.print(i + " -> ");
                for (int j = 0; j < adjList[i].size(); j++) {
                    System.out.print(adjList[i].get(j) + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

     */

    /**
     * @return returns the adjacency list of the dungeon, contains information about what rooms
     * are pointing at other rooms.
     */
    public static LinkedList<Room>[] getAdjList() {

        return (LinkedList<Room>[]) adjList;
    }

    /**
     * @param numRooms Passed to inform the number of rooms needed to place in the dungeon
     * @return The completed dungeon for the Player to use as their map.
     *
     * This creates a dungeon. It first creates a number of rooms equal to the number of rooms in the dungeon.
     * It then constructs a new dungeon, and populates an array of rooms equal to the number of rooms. From there,
     * it uses the monsterRooms array to set the rooms monsters are in. After that, it builds paths using a
     * static dungeon map.
     */
    public static Dungeon createDungeon(int numRooms){

        //array of rooms to assign paths from room 0 to room 20
        Room roomArray[];
        roomArray = new Room[numRooms];

        //create new dungeon object to hold rooms
        Dungeon dungeon = new Dungeon(numRooms);

        //generates every room in the dungeon
        for(int i = 0; i < numRooms; i++) {
            roomArray[i] = new Room(i, "Wow! Another Room!", false, false);
        }

        //populating dungeon with monsters
        for (int i = 0; i < monsterRooms.length; i++){
            int monsterIndex = monsterRooms[i];
            roomArray[monsterIndex].setMonsterHere(true);
        }

        //builds paths in the dungeon and populate with monsters
        buildPaths(dungeon, roomArray);

        return dungeon;
    }

    /**
     * @param dungeon Passed to modify the adjacency list
     * @param roomArray Builds the to and from paths of the entire dungeon using predetermined paths.
     */
    public static void buildPaths(Dungeon dungeon, Room roomArray[]){
        addPath(dungeon, roomArray[0], roomArray[1]);
        addPath(dungeon, roomArray[1], roomArray[2]);
        addPath(dungeon, roomArray[1], roomArray[3]);
        addPath(dungeon, roomArray[2], roomArray[4]);
        addPath(dungeon, roomArray[2], roomArray[5]);
        addPath(dungeon, roomArray[3], roomArray[6]);
        addPath(dungeon, roomArray[4], roomArray[7]);
        addPath(dungeon, roomArray[5], roomArray[8]);
        addPath(dungeon, roomArray[6], roomArray[8]);
        addPath(dungeon, roomArray[6], roomArray[9]);
        addPath(dungeon, roomArray[7], roomArray[10]);
        addPath(dungeon, roomArray[8], roomArray[10]);
        addPath(dungeon, roomArray[9], roomArray[11]);
        addPath(dungeon, roomArray[10], roomArray[12]);
        addPath(dungeon, roomArray[10], roomArray[13]);
        addPath(dungeon, roomArray[11], roomArray[13]);
        addPath(dungeon, roomArray[11], roomArray[14]);
        addPath(dungeon, roomArray[12], roomArray[15]);
        addPath(dungeon, roomArray[13], roomArray[16]);
        addPath(dungeon, roomArray[14], roomArray[17]);
        addPath(dungeon, roomArray[15], roomArray[18]);
        addPath(dungeon, roomArray[16], roomArray[18]);
        addPath(dungeon, roomArray[16], roomArray[19]);
        addPath(dungeon, roomArray[17], roomArray[20]);
        addPath(dungeon, roomArray[18], roomArray[21]);
        addPath(dungeon, roomArray[19], roomArray[21]);
        addPath(dungeon, roomArray[20], roomArray[21]);
    }

}
