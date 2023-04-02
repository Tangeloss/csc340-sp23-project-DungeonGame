package Dungeon;

import java.util.LinkedList;

public class Dungeon {

    private static int rooms;

    //Need to figure out converting Integers to Room, that way the graph can store Room objects instead.
    private static LinkedList<Integer>[] adjList;

    public Dungeon(int rooms) {

        this.rooms = rooms;
        adjList = new LinkedList[rooms];
        for (int i = 0; i < rooms; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addPath(int source, int destination) {

        adjList[source].add(destination);

    }

    public static void print() {
        for (int i = 0; i < rooms; i++) {
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

    //Method for creating the dungeon
    //Dungeon.addPath(room1, room2)

}
