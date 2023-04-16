package Main;

import java.io.File;
import java.io.*;
import java.util.*;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import static Main.Login.language;
import static Main.Login.translator;

public class DemoReviewer {
    public static void writeReview() throws DeepLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Thank you for playing! Please write your review:");

        TextResult result = translator.translateText("Thank you for playing! Please write your review:", null, language);
        System.out.println(result.getText());

        String review = scanner.nextLine();
        String fileName = "review.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(review.getBytes());
            fileOutputStream.close();
            //System.out.println("Your review has been saved to " + fileName);

            TextResult result2 = translator.translateText("Your review has been saved to " + fileName, null, language);
            System.out.println(result2.getText());

        } catch (IOException e) {
            //System.out.println("An error occurred while writing the review to " + fileName);

            TextResult result3 = translator.translateText("An error occurred while writing the review to " + fileName, null, language);
            System.out.println(result3.getText());

            e.printStackTrace();
        }
        scanner.close();
    }
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
