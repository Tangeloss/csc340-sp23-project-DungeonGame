package Main;

import Creatures.Player;
import Dungeon.Dungeon;
import Dungeon.Room;

public class Game {
    public static void main(String[] args) {

        int place = 0;

        Player player = new Player("jeff", 100);

        //Dungeon is created with graph logic
        Dungeon dungeon = Dungeon.createDungeon(21);
        player.setDungeonLocation(place);
        //dungeon.getAdjList()[0].get(0).setPlayerHere(true);

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

