package Main;
import Creatures.Player;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import static Main.Login.language;
import static Main.Login.translator;
import java.io.*;
import java.util.*;

/**
 * Save class helps to save user data to external .csv files for persisting data between sessions.
 */
public class Save {

    /**
     * @param filepath takes in path to save file
     * @param player takes in player object to persist player statistics, saving them to the file.
     * @throws DeepLException
     * @throws InterruptedException
     *
     * parses the player object and saves the statistics to the save file.
     */
    public static void saveStats(String filepath, Player player) throws DeepLException, InterruptedException {

        File oldFile = new File(filepath);
        File newFile = new File("temp.csv");
        String charName = player.getName();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            PrintWriter output = new PrintWriter(new FileWriter("temp.csv", true));
            String splitBy = ",";
            String[] loginArr;
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                loginArr = currentLine.split(splitBy);

                String name = loginArr[2];

                if (charName.equals(name)) {
                    currentLine = editStats(player, currentLine);
                }

                output.println(currentLine);
            }
            output.close();
            br.close();

            oldFile.delete();
            File file = new File(filepath);
            newFile.renameTo(file);

        } catch (IOException ex) {
            TextResult result3 = translator.translateText("Error Opening file", null, language);
            System.out.println(result3.getText());

        }
    }

    /**
     * @param player takes in player object from saveStats
     * @param currentLine takes the current line being written to from saveStats
     * @return the modified line with player's statistics saved.
     */
    public static String editStats(Player player, String currentLine) {

        String splitBy = ",";
        String[] statArr;

        statArr = currentLine.split(splitBy);

        String user = statArr[0];
        String pass = statArr[1];
        String name = statArr[2];
        String HP = String.valueOf(player.getHp());
        String potNum = String.valueOf(player.getNumPotions());
        String room = String.valueOf(player.getDungeonLocation());

        currentLine = user + "," + pass + "," + name + "," + HP + "," + potNum + "," + room;

        return currentLine;
    }

    /**
     * @param filepath takes in path to the save file
     * @param username takes in the username of the selected file to find persistent data
     * @param name takes in name of the character
     * @throws DeepLException
     * @throws InterruptedException
     */
    public static void saveCharName(String filepath, String username, String name) throws DeepLException, InterruptedException {

        File oldFile = new File(filepath);
        File newFile = new File("temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            PrintWriter output = new PrintWriter(new FileWriter("temp.csv", true));
            String splitBy = ",";
            String[] loginArr;
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                loginArr = currentLine.split(splitBy);

                String user = loginArr[0];

                if (username.equals(user)) {
                    currentLine = newCharName(name, currentLine);
                }

                output.println(currentLine);
            }
            output.close();
            br.close();

            oldFile.delete();
            File file = new File(filepath);
            newFile.renameTo(file);

        } catch (IOException ex) {
            TextResult result3 = translator.translateText("Error Opening file", null, language);
            System.out.println(result3.getText());

        }
    }

    /**
     * @param charName takes in character name from saveCharName.
     * @param currentLine the current line being modified by the user.
     * @return the modified current line with adjusted statistics.
     */
    public static String newCharName(String charName, String currentLine) {

        String splitBy = ",";
        String[] statArr;

        statArr = currentLine.split(splitBy);

        String user = statArr[0];
        String pass = statArr[1];
        String name = charName;
        String HP = String.valueOf(100);
        String potNum = String.valueOf(0);
        String room = String.valueOf(-1);

        currentLine = user + "," + pass + "," + name + "," + HP + "," + potNum + "," + room;

        return currentLine;
    }
}