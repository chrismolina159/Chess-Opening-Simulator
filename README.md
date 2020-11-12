# CHESS OPENING SIMULATOR

### VERSION 1.0:

Welcome to the Chess Opening Simulator.  If anyone has played chess a lot and looked into some of the more specific details of the game, they would have learned that the initial moves of a game are very important to remember.  However, there are so many openings with many different moves/responses to remember, so this text based simulator aims to help practice openings and how to respond.

#### Input:
The input is case sensitive except for when the program asks if you would like to play as white or black in which the user responds with **w** or **b**.  From then on, the program will ask for your move and how you would like your opponent to respond.

First the user must give the position of the piece they are moving(Ex: *Qd1*, *Pe2*, *Ng1*, etc.).  Then the user will have to input the destination for the piece. (**IMPORTANT NOTE**: If castling, the user will have to write *O* for the position of the piece, and *O* or *OO* for the destination to determine if the move is King side castling or Queen side castling respectively.  If taking another piece then give the position of your piece and for its destination, include an *x* before your destination i.e. *xd4*)  For destination, simply put the position you wish for your piece to be on ie: d4, e2 ...<br>

The purpose of these inputs is to simulate how a Chess app would record and display past moves.

Once you move, the program will ask how you want your oppnent to respond and will list possible openings remaining and the different moves to continue along that opening.  Input syntax remains the same.

#### Output:
Output is printed to the console.

#### Board:
The board is like a chess board in that the pieces in front of you are your color.  So if you are playing as white, *a1* is the rook on the bottom left, but if you are playing as black, *a1* is the rook on the top right.