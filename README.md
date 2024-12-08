# Indigo Card Game

This is a simple but enjoyable console card game in which a player competes against 
the computer for winning the most points by collecting cards.

The computer has intelligence and thus plays as if it were another human player.

### Game rules
The game rules are as follows:
- There are 52 cards in the deck.
- Initially, 4 cards are put on the table from the deck, face up.
- Then, players are given 6 cards each from the deck. Players cannot see the cards of each other.
- Players take turns:
  - if a player has in his hand a card that matches a rank or a suit of the last card on the table, 
he can toss it on the table and then take all the cards from the table and put them aside into his pile of won cards.
  - otherwise, the player put any card on the table and this card becomes the last card on the table.
- If players don’t have cards in their hands, each player takes 6 new cards from the deck.
- The game lasts as long as there are cards in the deck.
- At the end of the game, the points are given for each special card that is in the cards that a player won (A, 10, J, Q, K gives 1 point each).
- If at the end of the game, there are cards left on the table, they are given to the player who started second (put in his pile of won cards).
- Also, a player with the most cards gets 3 additional points.
- A winner is a player with the biggest number of points.

### Setup and run instructions
1. Install this game on your local machine by:
   - downloading it from GitHub (green button `<> CODE` -> `Download ZIP`) or
   - cloning the repository using a terminal shell:
       - in the terminal, navigate to the folder where you would like to store the game 
       - command: `git clone address_on_github`
       - `address_on_github` can be found here: green button `<> CODE` -> `Clone` - > `HTTPS` -> `https://github.com/NattiShakira/indigo-card-game.git` (just copy it)


2. To play the game, you'll need Kotlin installed on your machine and an IDE. We recommend 
using a free of charge IntelliJ IDEA Community Edition that already has Kotlin integrated into it. 
It can be downloaded from the official JetBrains website:
   - Windows: https://www.jetbrains.com/idea/download/?section=windows
   - macOS: https://www.jetbrains.com/idea/download/?section=mac
   - Linux: https://www.jetbrains.com/idea/download/?section=linux
   

3. Open the game in the IDE (File -> Open... -> choose the folder where you saved the game).


4. Start the game by hitting a green arrow next to the `main` method in the `Main.kt` file (src -> main -> kotlin).
The game will start on the console. Then, follow instructions there.


Happy playing!


