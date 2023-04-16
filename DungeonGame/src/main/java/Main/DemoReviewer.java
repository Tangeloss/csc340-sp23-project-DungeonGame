package Main;

import java.io.File;
import java.io.*;
import java.util.*;

/**
 * Demo Reviewer is allowed to write a review at the end of a Demo session. Class is used to help track the demo
 * reviewer's submitted reviews and load them for later use.
 */
public class DemoReviewer {

    /**
     * When a Reviewer's session ends, allows them to write a review and have it output to a .txt file
     */
    public static void writeReview() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Thank you for playing! Please write your review:");
        String review = scanner.nextLine();
        String fileName = "review.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(review.getBytes());
            fileOutputStream.close();
            System.out.println("Your review has been saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the review to " + fileName);
            e.printStackTrace();
        }
        scanner.close();
    }

    /**
     * Prints all available reviews from the review.txt file to the screen.
     */
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
