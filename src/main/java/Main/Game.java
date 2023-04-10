package Main;

import com.deepl.api.DeepLException;

public class Game {

    //public static Dungeon dungeon = Dungeon.createDungeon(22);
    public static void main(String[] args) throws DeepLException, InterruptedException {

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

    public Game() throws DeepLException, InterruptedException {

        //GameLogic.Login
        GameLogic.startGame();

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

