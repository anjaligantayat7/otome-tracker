# otome-tracker

Adds and tracks otome games.

## The Problem

Right now I track my otome game completion, but I wasn't able to get a good sense of what I had completed. I'd like to have a designated, organized space where I can clearly see my progress for each game.

## How It Works

This program can add or select otome games. If the user chooses to add an otome game, the program will ask for the name, the names of the routes, the current completion status, and game notes and will add all of that to a file. If the user chooses to select an otome game, they will have the option of viewing the game's current progress, editing the game's current progress, viewing the game notes, or editing the game notes. There is a quit option that will end the program. 

![System diagram](system-diagram.png)

## Tech Stack

- **Language:** Java
- **Libraries:** java.io, java.util.ArrayList
- **External tools:** N/A

## Setup

1. Install JDK 25.
2. Download and extract this repository.
3. Open terminal and cd into the otome-tracker folder.
4. Compile the program using this: `javac src/OtomeTracker.java`
5. Run the program using this: `java -cp src OtomeTracker`
6. Game information is saved within the current folder in a txt file with the game's name. Run the program from within the otome-tracker folder to access saved game information.


## Usage

The menu has three options: Add game, select game, and quit. Type the corresponding number and press Enter to choose which action you want to take.
- Add game: Enter the otome game name, number of routes/love interests, love interest names, completion status, and game notes when prompted. Ex: "Collar x Malice", 5, "Yanagi Aiji" "Okazaki Kei" "Shiraishi Kageyuki" "Mineo Enomoto" "Takeru Sasazuka", "Completed", "Great game, would play again". Do not enter the names in one go, enter one name, press the Enter key, and then enter the next. Quotations are not part of the input.
- Select game: Select a game from the ones you have inputted by typing in the corresponding number. Choose to either view or change the completion status of this game, or view or change the game notes by typing in the corresponding number. Ex: 1 (corresponds to Collar x Malice), 1 (corresponds to viewing completion status).
- Quit: Quits program.

## Roadmap

- [ ] Track progress for individual routes
- [x] Editable game notes
- [ ] Editable route notes

## Video Walkthrough
[Walkthrough](https://youtu.be/Ed8p3z9o_mY)

## Author

Anjali Gantayat, https://github.com/anjaligantayat7
