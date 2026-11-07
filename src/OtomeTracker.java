import java.io.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

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
                    IO.println("Adding game...");
                    String gameName = "";

                    //making sure input is valid
                    while (true) {
                        gameName = IO.readln("Enter otome game name\n");
                        //checking if game already exists
                        File f = new File(gameName + ".txt");
                        if (f.exists()) {
                            IO.println("Game already exists! Please try again.");
                            continue;
                        }

                        if (gameName.isBlank()) {
                            IO.println("Empty game name. Please retype.");
                            continue;
                        } else {
                            break;
                        }
                    }

                    //collect number of routes
                    int routeNumber = 0;
                    while (true) {
                        try {
                            routeNumber = Integer.parseInt(IO.readln("Enter number of routes as a number (ex: 1)\n"));
                            if (routeNumber > 0) {
                                break;
                            } else {
                                IO.println("Invalid route number. Route number must be greater than 0. Try again.");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            IO.println("Invalid input! Try again.");
                        }
                    }

                    //collect names of the routes
                    ArrayList<String> routeNames = new ArrayList<>();
                    while (routeNumber > 0) {
                        String s = IO.readln("Enter love interest name.\n");
                        if (s.isBlank()) {
                            IO.println("You need to enter in an actual name. Try again.");
                            continue;
                        }
                        IO.println("Saving " + s + "...");
                        routeNames.add(s);
                        routeNumber--;
                    }

                    //collect completion status
                    String completionStatus = "";
                    while (true) {
                        IO.println("Please enter the completion status of " + gameName + " from one of the following: ");
                        IO.println("Completed");
                        IO.println("In progress");
                        completionStatus = IO.readln("Backlog\n");
                        if (completionStatus.isBlank() || !completionStatus.equalsIgnoreCase("completed")
                            && !completionStatus.equalsIgnoreCase("in progress") && !completionStatus.equalsIgnoreCase("backlog")) {
                            IO.println("Please enter a valid input. Try again.");
                            continue;
                        } else {
                            IO.println("Saving completion status...");
                            break;
                        }
                    }

                    //saving it to a file
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream(gameName + ".txt"))) {
                        pw.println(gameName);

                        String str = "";
                        for (String s : routeNames) {
                            str += s + ",";
                        }
                        if (str.endsWith(",")) {
                            str = str.substring(0, str.length() - 1);
                        }
                        pw.println(str);

                        pw.println(completionStatus);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (choice == 2) {
                    IO.println("Selecting game...");

                    while (true) {
                        //get list of all files --> search up how to do that
                        File f = new File(".");
                        File[] files = f.listFiles();
                        //trying to get only txt files!
                        ArrayList<File> fileArrayList = new ArrayList<>();
                        for (File file : files) {
                            if (file.isFile() && file.getName().endsWith(".txt")) {
                                fileArrayList.add(file);
                            }
                        }

                        //check if there are games saved
                        if (fileArrayList.isEmpty()) {
                            IO.println("No games are saved. Please enter games before trying to select them");
                            break;
                        }

                        IO.println("Please input the number corresponding to the game you would like to select");

                        for (int i = 0; i < fileArrayList.size(); i++) {
                            IO.print(i + 1 + ": ");
                            IO.println(fileArrayList.get(i).getName().substring(0, fileArrayList.get(i).getName().length() - 4));
                        }
                        int n = 0;
                        try {
                            n = Integer.parseInt(IO.readln());
                            //check invalid number
                            if (n < 1 || n > fileArrayList.size()) {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e) {
                            IO.println("Invalid input! Try again.");
                            continue;
                        }

                        File selectedGame = fileArrayList.get(n - 1);
                        String selectedGameName = selectedGame.getName().substring(0, selectedGame.getName().length() - 4);
                        IO.println("Selected: " + (n) + " - " + selectedGameName);
                        IO.println("End of today's work.");
                        break;

                    }



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
