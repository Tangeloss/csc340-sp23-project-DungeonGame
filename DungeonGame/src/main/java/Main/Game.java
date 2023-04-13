package Main;

import com.deepl.api.DeepLException;

import java.io.IOException;

public class Game {

    //public static Dungeon dungeon = Dungeon.createDungeon(22);
    public static void main(String[] args) throws DeepLException, InterruptedException, IOException {

        new Game();

        /*
        int place = 0;

        Player player = new Player("jeff", 100);

        player.setDungeonLocation(place);

        //dungeon.getAdjList()[0].get(0).setPlayerHere(true);
        System.out.println(dungeon.getAdjList()[7].element().isMonsterHere());

        dungeon.print();
        */
        //Login.LoginMenu();
    }

    public Game() throws DeepLException, InterruptedException, IOException {


        GameLogic.startGame();
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

