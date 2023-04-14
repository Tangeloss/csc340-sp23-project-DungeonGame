package Main;
import Creatures.Player;
import com.deepl.api.DeepLException;

import java.io.*;
import java.util.Scanner;

public class Save {
    public static void saveStats(String filepath, Player player) throws DeepLException, InterruptedException {

            File oldFile = new File(filepath);
            File newFile = new File("temp.csv");
            String charName = player.getName();

            // int line = 0;
            String currentLine = "";


            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
                PrintWriter output = new PrintWriter(new FileWriter("temp.csv", true));
                String line = "";
                String splitBy = ",";
                String[] loginArr;

                while ((currentLine = br.readLine()) != null) {
                    loginArr = line.split(splitBy);

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
                System.out.println("Error Opening file");
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
}