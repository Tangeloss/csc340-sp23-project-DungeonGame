import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Testing review");
        writeReview();
    }

//Test branchk
    public static void gameLoop() {
        while (isRunning == true) {
            printMenu();
            int input = readChoice("-> ", 3);
            if (input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else if (input == 3)
                isRunning = false;
        }
    }


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

}