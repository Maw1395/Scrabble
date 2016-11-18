The interface is fairly simple to use, click on a letter, click on a box within 
the board, place the letter, and move on to the next. Letters can only be placed
going up and down, or left to right, but that will be made obivous as the game 
is played. Cancel button will cancel all buttons on the board, but have no fear!
This will not end your turn. If you chose to redraw letters, checkboxes will
appear. Check the letters you wish to get rid of, and continue onwards. This will
however, end your turn. The game continue until all 99 tiles have been placed on 
the board, at this point a winner is declared and the game has finished!
The game is not an applet, but rather a raw application.
It heavily relies upon JPanels. One user goes, looks away from the screen, and
then the next user proceeds on. The game as based as one would expect. The tools
first check to see if a word is valid, then check to see if the middle tile has 
been set, the checks to see that adjancies are set, then it checks for score,
tiles remaining in hand, tiles left in the bag, consolidates the two, refreshes
the tiles within the hand, and passes the game off to the next user. The java
files are seperated into 4 seperate files: board.java, letters.java, players.java
and box.java. Box sets up the boxes on the board with their color and text, 
Letters sets up the letters within the bag, their values, and their dependencies. 
Player simply sets a get score and set score function, and board.java does the
rest. Suffice to say, the rest is a lot. Variables got slightly out of hand within
the main file which made their implementation fairly difficult, as i would often 
get lost as to which variable points to which array points to which location. 
While the code does undoubtly work, improvments can be nonetheless made on the 
readability and performance of it.
--Michael Woodham
