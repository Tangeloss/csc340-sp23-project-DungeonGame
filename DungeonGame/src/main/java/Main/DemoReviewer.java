package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DemoReviewer {
    public static void loadReviews() {
        try {
            Scanner read = new Scanner(new File("review.txt"));

            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
