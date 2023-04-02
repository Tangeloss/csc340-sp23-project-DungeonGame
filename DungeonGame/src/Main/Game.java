package Main;

import Dungeon.Dungeon;

public class Game {
    public static void main(String[] args) {

        Dungeon dungeon = new Dungeon(21);

        dungeon.addPath(0, 1);
        dungeon.addPath(0, 2);
        dungeon.addPath(1, 3);
        dungeon.addPath(1, 4);
        dungeon.addPath(3, 6);
        dungeon.addPath(6, 9);
        dungeon.addPath(4, 7);
        dungeon.addPath(5, 7);
        dungeon.addPath(5, 8);
        dungeon.addPath(7, 9);
        dungeon.addPath(8, 10);
        dungeon.addPath(9, 11);

        dungeon.print();


        //Login.LoginMenu();
    }

    /*
    public static void main(String[] args) {

        new Game();
    }

    public Game(){

        //GameLogic.Login
        GameLogic.startGame();

    }

     */
}

