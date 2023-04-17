package Main;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import static Main.Login.language;
import static Main.Login.translator;

public class Story {

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * prints the introduction to the story of the game
     */
    public static void printIntro() throws DeepLException, InterruptedException {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        TextResult result = translator.translateText("STORY", null, language);
        System.out.println(result.getText());
        GameLogic.printSeperator(30);
        TextResult result1 = translator.translateText("You find yourself standing at the entrance of a dark and ominous dungeon.", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("The stench of moldy stone fills your nostrils and you hear the sound of scurrying rats.", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText("You've been told that treasure and glory lie within, but why would anyone in their right " +
                "mind venture into such a foreboding place?", null, language);
        System.out.println(result3.getText());
        TextResult result4 = translator.translateText("Well, maybe it's because you're broke and in desperate need of some coin.", null, language);
        System.out.println(result4.getText());
        TextResult result5 = translator.translateText("Or maybe it's because you're trying to impress a potential love interest with your bravery" +
                " (or sheer stupidity, depending on who you ask)", null, language);
        System.out.println(result5.getText());
        TextResult result6 = translator.translateText("Or maybe you just really love the smell of mildew and rodent droppings.", null, language);
        System.out.println(result6.getText());
        TextResult result7 = translator.translateText("Regardless of your reasons, you take a deep breath and steel yourself for what lies ahead.", null, language);
        System.out.println(result7.getText());
        TextResult result8 = translator.translateText("Who knows what dangers, treasures, and terrible creatures await you within these walls?", null, language);
        System.out.println(result8.getText());
        TextResult result9 = translator.translateText("But one thing is for certain: this dungeon won't know what hit it.", null, language);
        System.out.println(result9.getText());
        System.out.println();
        TextResult result10 = translator.translateText("Enter:", null, language);
        System.out.println(result10.getText());
        GameLogic.printHeading("DUNGEON GAME");
    }

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * prints the outcome of if the player hits 0 hp
     */
    public static void deathScreen() throws DeepLException, InterruptedException {
        GameLogic.clearConsole();
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
        TextResult result = translator.translateText("Well, that was it for you, hero. Hopes and dreams can only carry you so far with that many" +
                " vital stab wounds", null, language);
        System.out.println(result.getText());
        TextResult result1 = translator.translateText("Looks like it's back to the drawing board for you. Or, you know, the afterlife. Whichever.", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("On the bright side, at least you won't have to deal with John " +
                "anymore.", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText("Hey, no one said being a hero was easy! Or survivable, apparently... Better luck in your " +
                "next life!", null, language);
        System.out.println(result3.getText());
        GameLogic.printHeading("DEATH");
    }

    /**
     * @throws DeepLException
     * @throws InterruptedException
     *
     * prints the outcome of if the player reaches room 21 of the dungeon.
     */
    public static void winScreen() throws DeepLException, InterruptedException {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        TextResult result = translator.translateText("VICTORY", null, language);
        System.out.println(result.getText());
        GameLogic.printSeperator(30);
        GameLogic.printSeperator(30);
        TextResult result1 = translator.translateText("And just like that, you emerge on the other side. Very scathed, traumatized by " +
                "terrible puns, but alive none-the-less", null, language);
        System.out.println(result1.getText());
        TextResult result2 = translator.translateText("You survived the horrors of the dungeon and are victorious. " +
                "Now all that's left to do is write a bestselling memoir about your adventures and rake " +
                "in the profits.\nHey, being a hero is expensive.", null, language);
        System.out.println(result2.getText());
        TextResult result3 = translator.translateText("So, you step towards the setting sun on the horizon, but you'll never forget the " +
                "terrible puns and cheesy one-liners that got you here. Or maybe you will. We don't judge.", null, language);
        System.out.println(result3.getText());
        GameLogic.printHeading("THE END");
    }

}

