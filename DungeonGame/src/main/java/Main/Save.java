package Main;
import java.io.*;
import java.util.Scanner;

public class Save {
    public static void saveStats(String filepath, int acct) {
        File oldFile = new File(filepath);
        File newFile = new File("temp.csv");

        int line = 0;
        String currentLine = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            PrintWriter output = new PrintWriter(new FileWriter("temp.csv", true));

            while ((currentLine = br.readLine()) != null) {
                line++;

                if (acct == line) {
                    currentLine = editLine(currentLine);

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

    // Method 4.2 - edit each element of Player File
    public static String editLine(String currentLine) {
        Scanner input1 = new Scanner(System.in);

        String splitBy = ",";
        String[] acctArr;

        acctArr = currentLine.split(splitBy);

        String user = acctArr[0];
        String pass = acctArr[1];
        String name = acctArr[2];
        String HP = acctArr[3];
        String potNum = acctArr[4];
        String room = acctArr[5];

        currentLine = user + "," + pass + "," + name + "," + HP + "," + potNum + "," + room;

        return currentLine;
    }
}
