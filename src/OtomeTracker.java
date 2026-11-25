import java.io.*;
import java.util.ArrayList;

public class OtomeTracker {
    public static void main(String[] args) {
        IO.println("Welcome to the Otome Tracker!");

        // Keeps program running until user manually chooses the Quit option.
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

                    // Make sure input is valid (does not already exist + isn't a blank line) so that no files get overwritten.
                    while (true) {
                        gameName = IO.readln("Enter otome game name.\n");

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

                    // Collect the number of routes/love interests so we can use a while loop asking for the exact amount of names.
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

                    // Collect the names of the love interests.
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

                    // Collect completion status.
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

                            if (completionStatus.equalsIgnoreCase("in progress")) {
                                completionStatus = "In progress";
                            } else if (completionStatus.equalsIgnoreCase("completed")) {
                                completionStatus = "Completed";
                            } else if (completionStatus.equalsIgnoreCase("backlog")) {
                                completionStatus = "Backlog";
                            }
                            break;
                        }
                    }

                    String gameNotes = IO.readln("Please enter any game notes for " + gameName + ".\n");

                    // Saving all the game details to a file in the order of: name --> routes (separated by commas)
                    // --> completion status --> game notes
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
                        pw.println(gameNotes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (choice == 2) {
                    IO.println("Selecting game...");

                    while (true) {
                        // Get list of all files within current directory.
                        File f = new File(".");
                        File[] files = f.listFiles();
                        // Singling out the txt files (we don't want the Eisenhower matrix or the system diagram stuff).
                        ArrayList<File> fileArrayList = new ArrayList<>();
                        for (File file : files) {
                            if (file.isFile() && file.getName().endsWith(".txt")) {
                                fileArrayList.add(file);
                            }
                        }

                        // Check if there are any games saved.
                        if (fileArrayList.isEmpty()) {
                            IO.println("No games are saved. Please enter games before trying to select any.");
                            break;
                        }

                        IO.println("Please input the number corresponding to the game you would like to select.");

                        for (int i = 0; i < fileArrayList.size(); i++) {
                            IO.print(i + 1 + ": ");
                            IO.println(fileArrayList.get(i).getName().substring(0, fileArrayList.get(i).getName().length() - 4));
                        }
                        int n = 0;
                        try {
                            n = Integer.parseInt(IO.readln());

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

                        // Get user's desired action.
                        int p = 0;
                        while (true) {
                            IO.println("Please input the number corresponding to the action you would like to take with " + selectedGameName + ".");
                            IO.println("1: View completion status");
                            IO.println("2: Edit completion status");
                            IO.println("3: View game notes");
                            IO.println("4: Edit game notes");
                            try {
                                p = Integer.parseInt(IO.readln());

                                if (p < 1 || p > 4) {
                                    throw new NumberFormatException();
                                }
                                break;
                            } catch (NumberFormatException e) {
                                IO.println("Invalid input! Try again.");
                                continue;
                            }
                        }

                        String completionStatus = "";
                        if (p == 1) {
                            IO.println("Viewing progress for " + selectedGameName + "...");
                            String line = "";
                            try (BufferedReader br = new BufferedReader(new FileReader(selectedGame))) {
                                for (int i = 0; i < 3; i++) {
                                    line = br.readLine();
                                }
                                completionStatus = line;
                                IO.println("Progress for " + selectedGameName + ": " + completionStatus);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }

                        if (p == 2) {
                            while (true) {
                                IO.println("Editing progress for " + selectedGameName + "...");
                                IO.println("Please enter the new completion status of " + selectedGameName + " from one of the following: ");
                                IO.println("Completed");
                                IO.println("In progress");
                                completionStatus = IO.readln("Backlog\n");
                                if (completionStatus.isBlank() || !completionStatus.equalsIgnoreCase("completed")
                                        && !completionStatus.equalsIgnoreCase("in progress") && !completionStatus.equalsIgnoreCase("backlog")) {
                                    IO.println("Please enter a valid input. Try again.");
                                    continue;
                                } else {
                                    IO.println("Saving completion status...");

                                    if (completionStatus.equalsIgnoreCase("in progress")) {
                                        completionStatus = "In progress";
                                    } else if (completionStatus.equalsIgnoreCase("completed")) {
                                        completionStatus = "Completed";
                                    } else if (completionStatus.equalsIgnoreCase("backlog")) {
                                        completionStatus = "Backlog";
                                    }

                                    // FileOutputStream clears the file, so all the other lines need to be saved beforehand.
                                    String name = "";
                                    String routes = "";
                                    String gameNotes = "";
                                    try (BufferedReader br = new BufferedReader(new FileReader(selectedGame))) {
                                        name = br.readLine();
                                        routes = br.readLine();
                                        br.readLine();
                                        gameNotes = br.readLine();

                                        try (PrintWriter pw = new PrintWriter(new FileOutputStream(selectedGameName + ".txt"))) {
                                            pw.println(name);
                                            pw.println(routes);
                                            pw.println(completionStatus);
                                            pw.println(gameNotes);
                                            IO.println("Completion status saved!");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                            }
                            break;

                        }

                        if (p == 3) {
                            IO.println("Viewing game notes for " + selectedGameName + "...");
                            String line = "";
                            try (BufferedReader br = new BufferedReader(new FileReader(selectedGame))) {
                                for (int i = 0; i < 4; i++) {
                                    line = br.readLine();
                                }
                                IO.println("Game notes for " + selectedGameName + ":");
                                IO.println(line);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }

                        if (p == 4) {
                            IO.println("Editing game notes for " + selectedGameName + "...");
                            String gameNotes = IO.readln("Please enter new game notes for " + selectedGameName + ".\n");
                            // Saving every line BUT game notes so that file can be rewritten with new game notes.
                            String name = "";
                            String routes = "";
                            String completion = "";
                            try (BufferedReader br = new BufferedReader(new FileReader(selectedGame))) {
                                name = br.readLine();
                                routes = br.readLine();
                                completion = br.readLine();

                                try (PrintWriter pw = new PrintWriter(new FileOutputStream(selectedGameName + ".txt"))) {
                                    pw.println(name);
                                    pw.println(routes);
                                    pw.println(completion);
                                    pw.println(gameNotes);
                                    IO.println("Game notes saved!");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }

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
