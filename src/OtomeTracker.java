import java.io.*;
public class OtomeTracker {
    public static void main(String[] args) {
        IO.println("Welcome to the Otome Tracker!");

        while(true) {
            try {
                IO.println("Please enter a number: ");
                IO.println("1: Add game");
                IO.println("2: Select game");
                int choice = Integer.parseInt(IO.readln("3: Quit\n"));

                if (choice < 1 || choice > 3) {
                    throw new NumberFormatException();
                }

                if (choice == 1) {
                    IO.println("Adding game --> will code later!!");
                } else if (choice == 2) {
                    IO.println("Selecting game --> will code later!!");
                } else if (choice == 3) {
                    IO.println("Quitting...");
                    IO.println("Thank you for using my Otome Game Tracker!");
                    break;
                }

            } catch (NumberFormatException e) {
                IO.println("That is not a valid input. Please try again.");
            }
        }
    }
}
