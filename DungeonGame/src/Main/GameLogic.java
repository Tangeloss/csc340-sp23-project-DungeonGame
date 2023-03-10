package Main;
import java.util.Scanner;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);

    //method to get user input from console
    public static int readInt(String prompt, int userChoices){
        int input = 0;

        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt(scanner.next());
                if (input < 0 || input > userChoices){
                    System.out.println("Invalid choice, please enter an integer between 1 and " + userChoices);
                }
            } catch (Exception e){
                input = -1;
                System.out.println("Please enter an Integer!");
            }


        } while(input < 1 || input > userChoices);

        return input;
    }

    //method to clear the console
    public static void clearConsole(){
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    //method to print separator with length n
    public static void printSeparator(int n){
        for(int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    //Method to print a heading
    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    //method to stop the game until the user enters something
    public static void anythingToContinue(){
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }

}
