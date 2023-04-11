package Dungeon;

import Creatures.Player;

import java.util.LinkedList;

public class Dungeon {

    private static int numRooms;
    private static LinkedList<Room>[] adjList;

    public Dungeon(int numRooms) {

        this.numRooms = numRooms;
        adjList = new LinkedList[numRooms];
        for (int i = 0; i < numRooms; i++) {
            adjList[i] = new LinkedList<>();
        }

        //startRoom = adjList[0].getFirst();
    }

    public static void addPath(Dungeon dungeon, Room source, Room destination) {

        dungeon.adjList[source.getId()].add(destination);

    }

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

    public static LinkedList<Room>[] getAdjList() {
        return (LinkedList<Room>[]) adjList;
    }

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

        /*
        addPath(dungeon, roomArray[0], roomArray[1]);
        addPath(dungeon, roomArray[1], roomArray[2]);
        addPath(dungeon, roomArray[1], roomArray[3]);
        addPath(dungeon, roomArray[2], roomArray[4]);
        addPath(dungeon, roomArray[3], roomArray[4]);
        */

        //builds paths in the dungeon
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

        //TODO populateDungeon, taking in dungeon and list of monster rooms, return true for rooms where monsters are

        return dungeon;
    }

    /*
    public static void populateDungeon(Dungeon dungeon, int[] monsterRooms){

        for(int i = 0; i < monsterRooms.length; i++){


        }

    }
    */
}
