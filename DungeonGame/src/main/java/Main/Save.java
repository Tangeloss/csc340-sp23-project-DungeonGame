package Main;
import Creatures.Player;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import static Main.Login.language;
import static Main.Login.translator;
import java.io.*;
import java.util.Scanner;

public class Save {
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
                //System.out.println("Error Opening file");

                TextResult result3 = translator.translateText("Error Opening file", null, language);
                System.out.println(result3.getText());

            }
        }


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

    public static void saveCharName(String filepath, String username, String name) throws DeepLException, InterruptedException {

        File oldFile = new File(filepath);
        File newFile = new File("temp.csv");
        //String charName = player.getName();

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
            //System.out.println("Error Opening file");

            TextResult result3 = translator.translateText("Error Opening file", null, language);
            System.out.println(result3.getText());

        }
    }


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