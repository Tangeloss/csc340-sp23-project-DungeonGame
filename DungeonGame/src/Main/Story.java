package Main;

public class Story {

    public static void printIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("STORY");
        GameLogic.printSeperator(30);
        System.out.println("You find yourself standing at the entrance of a dark and ominous dungeon.");
        System.out.println("The stench of moldy stone fills your nostrils and you hear the sound of scurrying rats.");
        System.out.println("You've been told that treasure and glory lie within, but why would anyone in their right " +
                "mind venture into such a foreboding place?");
        System.out.println("Well, maybe it's because you're broke and in desperate need of some coin.");
        System.out.println("Or maybe it's because you're trying to impress a potential love interest with your bravery" +
                " (or sheer stupidity, depending on who you ask)");
        System.out.println("Or maybe you just really love the smell of mildew and rodent droppings.");
        System.out.println("Regardless of your reasons, you take a deep breath and steel yourself for what lies ahead.");
        System.out.println("Who knows what dangers, treasures, and terrible creatures await you within these walls?");
        System.out.println("But one thing is for certain: this dungeon won't know what hit it.");
        System.out.println("Enter:");
        GameLogic.printHeading("DUNGEON GAME");
    }

    public static void deathScreen(){
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("                                                                                                                                           \n" +
                "                                                                    *&&&&&&&&&&&&&*                                                                   \n" +
                "                                                              %&&&&&&&&&&&&&&&&&&&&&&&&&#                                                             \n" +
                "                                                           &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                          \n" +
                "                                                        &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                       \n" +
                "                                                      &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                     \n" +
                "                                                    &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                   \n" +
                "                                                   &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                  \n" +
                "                                                  &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                 \n" +
                "                                                 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#                                                \n" +
                "                                                 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                \n" +
                "                                                 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                \n" +
                "                                                 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                \n" +
                "                                                 &&&&&&&&&&          &&&&&&&&&&&&&          &&&&&&&&&&                                                \n" +
                "                                                  &&&&&&              &&&&&&&&&&&              &&&&&&                                                 \n" +
                "                                                  &&&&/               &&&&&&&&&&&               #&&&&                                                 \n" +
                "                                                  &&&&               &&&&&&&&&&&&&               &&&&                                                 \n" +
                "                                                 &&&&&&             &&&&&&&&&&&&&&&             &&&&&&                                                \n" +
                "                                                 &&&&&&&&.       &&&&&&&       &&&&&&@       *&&&&&&&&                                                \n" +
                "                                                 &&&&&&&&&&&&&&&&&&&&&@         &&&&&&&&&&&&&&&&&&&&&#                                                \n" +
                "                                                   &&&&&&&&&&&&&&&&&&&           &&&&&&&&&&&&&&&&&&&                                                  \n" +
                "                                                            &&&&&&&&&             &&&&&&&&&                                                           \n" +
                "                                                             &&&&&&&&&           &&&&&&&&&                                                            \n" +
                "                                                             &&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                            \n" +
                "                                                              &&&&&&&&&&&&&&&&&&&&&&&&&&&                                                             \n" +
                "                                         &&&&&&&&              &&&&&&&&&&&&&&&&&&&&&&&&&              &&&&&&&&                                        \n" +
                "                                        &&&&&&&&&&               &&&&&&&&&&&&&&&&&&&&@               &&&&&&&&&&                                       \n" +
                "                                        &&&&&&&&&&&#                                               &&&&&&&&&&&&                                       \n" +
                "                                       (&&&&&&&&&&&&&&                                           &&&&&&&&&&&&&&/                                      \n" +
                "                                     &&&&&&&&&&&&&&&&&&&&&&&&                             &&&&&&&&&&&&&&&&&&&&&&&&                                    \n" +
                "                                    (&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%               &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&,                                   \n" +
                "                                     &&&&&&&&&&&    &&&&&&&&&&&&&&&&&&&&&&/ (&&&&&&&&&&&&&&&&&&&&&&    &&&&&&&&&&&                                    \n" +
                "                                                           &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                                          \n" +
                "                                                               &&&&&&&&&&&&&&&&&&&&&&&&&                                                              \n" +
                "                                      ,&&&&&&           &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&           &&&&&&.                                     \n" +
                "                                     &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&.       ,&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&                                    \n" +
                "                                    ,&&&&&&&&&&&&&&&&&&&&&&&&&&&(                     #&&&&&&&&&&&&&&&&&&&&&&&&&&&                                    \n" +
                "                                      @&&&&&&&&&&&&&&&&@&&                                   &&&&&&&&&&&&&&&&&&&&                                     \n" +
                "                                        &&&&&&&&&&&&*                                             #&&&&&&&&&&&#                                       \n" +
                "                                        &&&&&&&&&&&                                                 &&&&&&&&&&&                                       \n" +
                "                                        &&&&&&&&&&                                                   &&&&&&&&&&                                       \n" +
                "                                          &&&&&&                                                       &&&&&&                                         \n" +
                "                                                                                                                                                      ");
        GameLogic.printSeperator(30);
        System.out.println("Well, that was it for you, hero. Hopes and dreams can only carry you so far with that many" +
                "vital stab wounds");
        System.out.println("Looks like it's back to the drawing board for you. Or, you know, the afterlife. Whichever.");
        System.out.println("On the bright side, at least you won't have to deal with those terrible skeleton puns " +
                "anymore.");
        System.out.println("Hey, no one said being a hero was easy! Or survivable, apparently... Better luck in your " +
                "next life!");
        GameLogic.printHeading("DEATH");
    }

    public static void winScreen(){
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("VICTORY");
        GameLogic.printSeperator(30);
        GameLogic.printSeperator(30);
        System.out.println("And just like that, you emerge on the other side. Very scathed, traumatized by " +
                "terrible puns, but alive none-the-less");
        System.out.println("You survived the horrors of the dungeon and are victorious. " +
                "Now all that's left to do is write a bestselling memoir about your adventures and rake " +
                "in the profits.\n Hey, being a hero is expensive.");
        System.out.println("So, you step towards the sun setting on the horizon, but you'll never forget the " +
                "terrible puns and cheesy one-liners that got you here. Or maybe you will. We don't judge.");
        GameLogic.printHeading("THE END");
    }

}
