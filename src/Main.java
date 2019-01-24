import java.awt.*;
import java.util.*;

public class Main {

	//Current objectives:
	//Need to allow user to retry after choosing a move that doesn't go along with a variation(COMPLETE)
	//Add everything I did for white for black so players can play as both(COMPLETE)
	//Implement a way to take another piece and castling(HAVENT TESTING CASTLING)
	//Automatically have computer move piece if there is only one opening left
	//Will need to implement a way to check if user has made the last move and the simulation is done
	//Implement a valid move checker
	
	public static void main(String[] args) {

		Piece[][] board = new Piece[8][8];
		System.out.println("Which side will you be playing as White(W) or Black(B)?");
		Scanner input = new Scanner(System.in);
		String color = input.next();
		boolean white = true;
		
		if(color.equalsIgnoreCase("w"))
			;
		else
			white = false;
		initiate(board, white);
		print(board);
		
		boolean simulating = true;
		boolean castling = false;
		
		int playerOrCPU = 0; //0 == player is castling, 1 == computer is castling
		Opening opener = new Opening();
		int counter = 1;
		String openerSimulating = null;
		
		//without an opener chosen
		while(simulating) {
			//if playing as white
			if(white) {
				try {
					boolean needToRetry = false;
					
					System.out.println("Which piece are you moving?(Written as Pe2 to communicate that I am moving the pawn on e2, if castling do O for this question and O or OO for the next)");
					String pieceToMove = input.next();
						
					System.out.println("Which space would you like to move this piece to?(Written as e4 if you were moving the pawn on e2 to e4 or if you were taking a piece on e4 no need to include the 'x'");
					String pieceDestination = input.next();
						
					String chessMove = pieceToMove.charAt(0) + pieceDestination;
					if(chessMove.equals("OO")) {
						chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1);
						playerOrCPU = 0;
						castling = true;
					}
					
					if(chessMove.equals("OOO")) {
						chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1) + "-" + chessMove.charAt(2);
						playerOrCPU = 0;
						castling = true;
					}
					if(pieceDestination.charAt(0) == 'x') {
						chessMove = pieceToMove + pieceDestination;
						pieceDestination = pieceDestination.substring(1);
					}
					
					boolean isAValidMove = isValidMove(board, pieceToMove, pieceDestination, white);
					if(isAValidMove) {
						needToRetry = opener.checkOpeningsW(chessMove, counter);
						
						if(needToRetry) {
							if(castling)
								castling = false;
							while(needToRetry) {
								needToRetry = retryW(board, opener, input, counter, white);
							}
						}
						else {
							if(castling) {
								castle(board, chessMove, white, playerOrCPU);
								castling = false;
							}
							else
								move(board, pieceToMove, pieceDestination, counter, white);
						}
					}
					else {
						System.out.println("Not a valid move");
						needToRetry = true;
						if(castling)
							castling = false;
						while(needToRetry) {
							needToRetry = retryW(board, opener, input, counter, white);
						}
					}
					
					print(board);
					//CPU's response to player if player is playing as white
					System.out.println("How would you like your opponent to respond?(format is the same as before)");
					String oppPTM = input.next();
						
					System.out.println("And the space you would like your opponent's piece to move to");
					String oppPD = input.next();
					String oppChessMove = oppPTM.charAt(0) + oppPD;
					
					if(oppChessMove.equals("OO")) {
						oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1);
						playerOrCPU = 1;
						castling = true;
					}
					
					if(oppChessMove.equals("OOO")) {
						oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1) + "-" + oppChessMove.charAt(2);
						playerOrCPU = 1;
						castling = true;
					}
					
					if(oppPD.charAt(0) == 'x') {
						oppChessMove = oppPTM + oppPD;
						oppPD = oppPD.substring(1);
					}
					
					isAValidMove = isValidMove(board, oppPTM, oppPD, white);
					if(isAValidMove) {
						needToRetry = opener.oppRespondW(oppChessMove, counter);
						
						if(needToRetry) {
							if(castling)
								castling = false;
							while(needToRetry) {
								needToRetry = retryOW(board, opener, input, counter, white);
							}
						}
						else {
							if(castling) {
								castle(board, oppChessMove, white, 1);
								castling = false;
							}
							else {
								move(board, oppPTM, oppPD, counter, white);
								castling = false;
							}
						}
					}
					else {
						System.out.println("Not a valid move");
						needToRetry = true;
						if(castling)
							castling = false;
						while(needToRetry) {
							needToRetry = retryOW(board, opener, input, counter, white);
						}
					}
					print(board);
					counter++;
				}catch(IndexOutOfBoundsException e) {
					System.out.println("You have successfully finished simulating your chess opening!!");
					System.out.println("Would you like to play again? Type retry to start again or done to be finished.");
					String retryOrDone = input.next();
					if(retryOrDone.equals("retry")) {
						System.out.print("Which color (W) or (B)");
						color = input.next();
						if(color.equalsIgnoreCase("w")) {
							white = true;
						}
						else {
							white = false;
						}
						initiate(board, white);
						counter = 1;
						opener = new Opening();
						print(board);
					}
					else {
						System.out.println("Thanks for playing!");
						simulating = false;
					}
				}
			}
			//if playing as black(I want to have a randomize option and a let player dictate white's moves as well
			else {
				try {
					boolean needToRetry = false;
					
					System.out.println("Which opponenet's peice would you like to move?(Written as Pe2 to communicate that I am moving the pawn on e2)");
					String oppPTM = input.next();
					
					System.out.println("Which space would you like to move this piece to?(Written as e4 if you were moving the pawn on e2 to e4");
					String oppPD = input.next();
					String oppChessMove = oppPTM.charAt(0) + oppPD;
					
					if(oppChessMove.equals("OO")) {
						oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1);
						playerOrCPU = 1;
						castling = true;
					}
					
					if(oppChessMove.equals("OOO")) {
						oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1) + "-" + oppChessMove.charAt(2);
						playerOrCPU = 1;
						castling = true;
					}
					if(oppPD.charAt(0) == 'x') {
						oppChessMove = oppPTM + oppPD;
						oppPD = oppPD.substring(1);
					}
					
					boolean isAValidMove = isValidMove(board, oppPTM, oppPD, white);
					
					if(isAValidMove) {
						needToRetry = opener.oppRespondB(oppChessMove, counter);
						
						if(needToRetry) {
							if(castling)
								castling = false;
							while(needToRetry) {
								needToRetry = retryOB(board, opener, input, counter, white);
							}
						}
						else {
							if(castling) {
								castle(board, oppChessMove, white,1);
								castling = false;
							}
							else {
								move(board, oppPTM, oppPD, counter, white);
								castling = false;
							}
						}
					}
					else {
						System.out.println("Not a valid move");
						needToRetry = true;
						if(castling)
							castling = false;
						while(needToRetry) {
							needToRetry = retryOB(board, opener, input, counter, white);
						}
					}
					
					print(board);
					
					//Player's(black) response to the CPU's(white) move
					System.out.println("How would you like to respond?(format is the same as before)");
					String pieceToMove = input.next();
					
					System.out.println("And the space you would like your piece to move to");
					String pieceDestination = input.next();
					String chessMove = pieceToMove.charAt(0) + pieceDestination;
					
					if(chessMove.equals("OO")) {
						chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1);
						playerOrCPU = 0;
						castling = true;
					}
					
					if(chessMove.equals("OOO")) {
						chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1) + "-" + chessMove.charAt(2);
						playerOrCPU = 0;
						castling = true;
					}
					if(pieceDestination.charAt(0) == 'x') {
						chessMove = pieceToMove + pieceDestination;
						pieceDestination = pieceDestination.substring(1);
					}
					
					isAValidMove = isValidMove(board, pieceToMove, pieceDestination, white);
					needToRetry = opener.checkOpeningsB(chessMove, counter);
	
					if(isAValidMove) {
						if(needToRetry) {
							if(castling)
								castling = false;
							while(needToRetry) {
								needToRetry = retryB(board, opener, input, counter, white);
							}
						}
						else {
							if(castling) {
								castle(board, chessMove, white, playerOrCPU);
								castling = false;
							}
							else {
								move(board, pieceToMove, pieceDestination, counter, white);
								castling = false;
							}
						}
					}
					else {
						System.out.println("Not a valid move");
						needToRetry = true;
						if(castling)
							castling = false;
						while(needToRetry) {
							needToRetry = retryB(board, opener, input, counter, white);
						}
					}
					
					print(board);
					
					counter++;
					}catch(IndexOutOfBoundsException e) {
						System.out.println("You have successfully finished simulating your chess opening!!");
						System.out.println("Would you like to play again? Type retry to start again or done to be finished.");
						String retryOrDone = input.next();
						if(retryOrDone.equals("retry")) {
							System.out.print("Which color (W) or (B)");
							color = input.next();
							if(color.equalsIgnoreCase("w")) {
								white = true;
							}
							else {
								white = false;
							}
							initiate(board, white);
							counter = 1;
							opener = new Opening();
							print(board);
						}
						else {
							System.out.println("Thanks for playing!");
							simulating = false;
						}
					}
			}
		}
		
	}
	
	private static boolean isValidMove(Piece[][] board, String piecePosition, String pieceDestination, boolean white) {
		if(piecePosition.equals("O")) {
			return true;
		}
		else {
			if(white) {
				String pTMTranslated = translateW(piecePosition.substring(1));
				int pTMX = Character.getNumericValue(pTMTranslated.charAt(0));
				int pTMY = Character.getNumericValue(pTMTranslated.charAt(1));
				
				String pDTranslated = translateW(pieceDestination);
				int pDX = Character.getNumericValue(pDTranslated.charAt(0));
				int pDY = Character.getNumericValue(pDTranslated.charAt(1));
				
				return board[pTMX][pTMY].isValid(pTMX, pTMY, pDX, pDY);
			}
			else {
				String pTMTranslated = translateB(piecePosition.substring(1));
				int pTMX = Character.getNumericValue(pTMTranslated.charAt(0));
				int pTMY = Character.getNumericValue(pTMTranslated.charAt(1));
				
				String pDTranslated = translateB(pieceDestination);
				int pDX = Character.getNumericValue(pDTranslated.charAt(0));
				int pDY = Character.getNumericValue(pDTranslated.charAt(1));
				
				return board[pTMX][pTMY].isValid(pTMX, pTMY, pDX, pDY);
			}
		}
	}
	
	private static boolean retryW(Piece[][] board, Opening open, Scanner input, int counter, boolean white) {
		boolean castling = false;
		
		System.out.println("Which piece are you moving?(Written as Pe2 to communicate that I am moving the pawn on e2)");
		String pieceToMove = input.next();
			
		System.out.println("Which space would you like to move this piece to?(Written as e4 if you were moving the pawn on e2 to e4");
		String pieceDestination = input.next();
			
		String chessMove = pieceToMove.charAt(0) + pieceDestination;
		
		if(chessMove.equals("OO")) {
			chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1);
			castling = true;
		}
		
		if(chessMove.equals("OOO")) {
			chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1) + "-" + chessMove.charAt(2);
			castling = true;
		}
		if(pieceDestination.charAt(0) == 'x') {
			chessMove = pieceToMove + pieceDestination;
			pieceDestination = pieceDestination.substring(1);
		}
		
		boolean isAValidMove = isValidMove(board, pieceToMove, pieceDestination, white);
		if(isAValidMove) {
			boolean rtrn = open.checkOpeningsW(chessMove, counter);
			
			if(rtrn)
				return rtrn;
			else {
				if(castling) {
					castle(board, chessMove, white, 0);
					return rtrn;
				}
				else {
					move(board, pieceToMove, pieceDestination, counter, white);
					return rtrn;
				}
			}
		}
		else {
			System.out.println("Not a valid move");
			return true;
		}
	}
	
	private static boolean retryB(Piece[][] board, Opening open, Scanner input, int counter, boolean white) {
		boolean castling = false;
		
		System.out.println("Which piece are you moving?(Written as Pe2 to communicate that I am moving the pawn on e2)");
		String pieceToMove = input.next();
			
		System.out.println("Which space would you like to move this piece to?(Written as e4 if you were moving the pawn on e2 to e4");
		String pieceDestination = input.next();
			
		String chessMove = pieceToMove.charAt(0) + pieceDestination;
		if(chessMove.equals("OO")) {
			chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1);
			castling = true;
		}
		
		if(chessMove.equals("OOO")) {
			chessMove = chessMove.charAt(0) + "-" + chessMove.charAt(1) + "-" + chessMove.charAt(2);
			castling = true;
		}
		if(pieceDestination.charAt(0) == 'x') {
			chessMove = pieceToMove + pieceDestination;
			pieceDestination = pieceDestination.substring(1);
		}
		
		boolean isAValidMove = isValidMove(board, pieceToMove, pieceDestination, white);
		if(isAValidMove) {
			boolean rtrn = open.checkOpeningsB(chessMove, counter);
			
			if(rtrn)
				return rtrn;
			else {
				if(castling) {
					castle(board, chessMove, white,0);
					return rtrn;
				}
				move(board, pieceToMove, pieceDestination, counter, white);
				return rtrn;
			}
		}
		else {
			System.out.println("Not a valid move");
			return true;
		}
	}
	
	private static boolean retryOW(Piece[][] board, Opening open, Scanner input, int counter, boolean white) {
		boolean castling = false;
		
		System.out.println("How would you like your opponent to respond?(format is the same as before)");
		String oppPTM = input.next();
			
		System.out.println("And the space you would like your opponent's piece to move to");
		String oppPD = input.next();
		String oppChessMove = oppPTM.charAt(0) + oppPD;
		
		if(oppChessMove.equals("OO")) {
			oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1);
			castling = true;
		}
		
		if(oppChessMove.equals("OOO")) {
			oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1) + "-" + oppChessMove.charAt(2);
			castling = true;
		}
		if(oppPD.charAt(0) == 'x') {
			oppChessMove = oppPTM + oppPD;
			oppPD = oppPD.substring(1);
		}
		
		boolean isAValidMove = isValidMove(board, oppPTM, oppPD, white);
		if(isAValidMove) {
			boolean rtrn = open.oppRespondW(oppChessMove, counter);
			
			if(rtrn)
				return rtrn;
			else {
				if(castling) {
					castle(board, oppChessMove, white, 1);
					return rtrn;
				}
				move(board, oppPTM, oppPD, counter, white);
				return rtrn;
			}
		}
		else {
			System.out.println("Not a valid move");
			return true;
		}
	}
	
	private static boolean retryOB(Piece[][] board, Opening open, Scanner input, int counter, boolean white) {
		boolean castling = false;
		
		System.out.println("How would you like your opponent to respond?(format is the same as before)");
		String oppPTM = input.next();
			
		System.out.println("And the space you would like your opponent's piece to move to");
		String oppPD = input.next();
		String oppChessMove = oppPTM.charAt(0) + oppPD;
		
		if(oppChessMove.equals("OO")) {
			oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1);
			castling = true;
		}
		
		if(oppChessMove.equals("OOO")) {
			oppChessMove = oppChessMove.charAt(0) + "-" + oppChessMove.charAt(1) + "-" + oppChessMove.charAt(2);
			castling = true;
		}
		
		if(oppPD.charAt(0) == 'x') {
			oppChessMove = oppPTM + oppPD;
			oppPD = oppPD.substring(1);
		}
		
		boolean isAValidMove = isValidMove(board, oppPTM, oppPD, white);
		
		if(isAValidMove) {
			boolean rtrn = open.oppRespondB(oppChessMove, counter);
			
			if(rtrn)
				return rtrn;
			else {
				if(castling) {
					castle(board, oppChessMove, white, 1);
				}
				move(board, oppPTM, oppPD, counter, white);
				return rtrn;
			}
		}
		else {
			System.out.println("Not a valid move");
			return true;
		}
	}
	
	public static void initiate(Piece[][] board, boolean white) {
		for(int i = 2; i < board.length; i++) {
			for(int j = 0 ; j < board[0].length; j++) {
				board[i][j] = null;
			}
		}
		
		if(white) {
			board[0][0] = new Rook("black");
			board[0][1] = new Knight("black");
			board[0][2] = new Bishop("black");
			board[0][3] = new Queen("black");
			board[0][4] = new King("black");
			board[0][5] = new Bishop("black");
			board[0][6] = new Knight("black");
			board[0][7] = new Rook("black");
			
			board[7][0] = new Rook("white");
			board[7][1] = new Knight("white");
			board[7][2] = new Bishop("white");
			board[7][3] = new Queen("white");
			board[7][4] = new King("white");
			board[7][5] = new Bishop("white");
			board[7][6] = new Knight("white");
			board[7][7] = new Rook("white");
			
			board[1][0] = new Pawn("black");
			board[1][1] = new Pawn("black");
			board[1][2] = new Pawn("black");
			board[1][3] = new Pawn("black");
			board[1][4] = new Pawn("black");
			board[1][5] = new Pawn("black");
			board[1][6] = new Pawn("black");
			board[1][7] = new Pawn("black");
			
			board[6][0] = new Pawn("white");
			board[6][1] = new Pawn("white");
			board[6][2] = new Pawn("white");
			board[6][3] = new Pawn("white");
			board[6][4] = new Pawn("white");
			board[6][5] = new Pawn("white");
			board[6][6] = new Pawn("white");
			board[6][7] = new Pawn("white");
		}
		else {
			board[0][0] = new Rook("white");
			board[0][1] = new Knight("white");
			board[0][2] = new Bishop("white");
			board[0][3] = new King("white");
			board[0][4] = new Queen("white");
			board[0][5] = new Bishop("white");
			board[0][6] = new Knight("white");
			board[0][7] = new Rook("white");
			
			board[7][0] = new Rook("black");
			board[7][1] = new Knight("black");
			board[7][2] = new Bishop("black");
			board[7][3] = new King("black");
			board[7][4] = new Queen("black");
			board[7][5] = new Bishop("black");
			board[7][6] = new Knight("black");
			board[7][7] = new Rook("black");
			
			board[1][0] = new Pawn("white");
			board[1][1] = new Pawn("white");
			board[1][2] = new Pawn("white");
			board[1][3] = new Pawn("white");
			board[1][4] = new Pawn("white");
			board[1][5] = new Pawn("white");
			board[1][6] = new Pawn("white");
			board[1][7] = new Pawn("white");
			
			board[6][0] = new Pawn("black");
			board[6][1] = new Pawn("black");
			board[6][2] = new Pawn("black");
			board[6][3] = new Pawn("black");
			board[6][4] = new Pawn("black");
			board[6][5] = new Pawn("black");
			board[6][6] = new Pawn("black");
			board[6][7] = new Pawn("black");
		}
		
	}
	
	public static void print(Piece[][] board) {
		System.out.println("_________________");
		for(int i = 0; i < board.length; i++) {
			System.out.print("|");
			for(int k = 0; k < board[0].length; k++) {
				if(board[i][k] != null) {
					if(board[i][k].getID().equals("Knight"))
						System.out.print("N"+"|");
					else
						System.out.print(board[i][k].getID().charAt(0)+"|");
				}
				else
					System.out.print(" |");
			}
			System.out.println();
		}
	}
	
	//method that reads in the input and moves the pieces on the board.
	//As of right now pieces can move anywhere on the board.
	//Does not recognize the "x" to take another piece.  Does not check to see if the move is valid
	public static void move(Piece[][] board, String pTM, String pD, int counter, boolean white) {
		if(white) {
			String pTMTranslated = translateW(pTM.substring(1));
			String pDTranslated = translateW(pD);
			
			int pTMX = Character.getNumericValue(pTMTranslated.charAt(0));
			int pTMY = Character.getNumericValue(pTMTranslated.charAt(1));
			
			int pDX = Character.getNumericValue(pDTranslated.charAt(0));
			int pDY = Character.getNumericValue(pDTranslated.charAt(1));
			
			board[pDX][pDY] = board[pTMX][pTMY];
			board[pTMX][pTMY] = null;
		}
		else {
			String pTMTranslated = translateB(pTM.substring(1));
			String pDTranslated = translateB(pD);
			
			int pTMX = Character.getNumericValue(pTMTranslated.charAt(0));
			int pTMY = Character.getNumericValue(pTMTranslated.charAt(1));
			
			int pDX = Character.getNumericValue(pDTranslated.charAt(0));
			int pDY = Character.getNumericValue(pDTranslated.charAt(1));
			
			board[pDX][pDY] = board[pTMX][pTMY];
			board[pTMX][pTMY] = null;
		}
		
	}
	
	private static void castle(Piece[][] board, String whichCastle, boolean white, int playerOrCPU) {
		if(whichCastle.equals("O-O")) {
			if(playerOrCPU == 0) {
				if(white) {
					board[7][6] = board[7][4];
					board[7][5] = board[7][7];
					board[7][4] = null;
					board[7][7] = null;
				}
				else {
					board[7][1] = board[7][3];
					board[7][2] = board[7][0];
					board[7][3] = null;
					board[7][0] = null;
				}
			}
			else {
				if(white) {
					board[0][6] = board[0][4];
					board[0][5] = board[0][7];
					board[0][4] = null;
					board[0][7] = null;
				}
				else {
					board[0][1] = board[0][3];
					board[0][2] = board[0][0];
					board[0][3] = null;
					board[0][0] = null;
				}
			}
		}
		if(whichCastle.equals("O-O-O")) {
			if(playerOrCPU == 0) {
				if(white) {
					board[7][2] = board[7][4];
					board[7][3] = board[7][0];
					board[7][4] = null;
					board[7][0] = null;
				}
				else {
					board[7][5] = board[7][3];
					board[7][4] = board[7][7];
					board[7][3] = null;
					board[7][7] = null;
				}
			}
			else {
				if(white) {
					board[0][2] = board[0][4];
					board[0][3] = board[0][0];
					board[0][4] = null;
					board[0][0] = null;
				}
				else {
					board[0][5] = board[0][3];
					board[0][4] = board[0][7];
					board[0][3] = null;
					board[0][7] = null;
				}
			}
		}
			
	}
	
	//this method reads in the letter and number combination of a chess move in order to find the corresponding square in a matrix.
	//the return value is a String of two numbers, x and y, representing the row and column of the box.
	private static String translateW(String move) {
		char yStr = move.charAt(0);
		String xStr = move.substring(1);
		String rtrn = "";
		rtrn += (8 - Integer.parseInt(xStr)) + "";
		
		switch(yStr) {
		case 'a':
			rtrn += "0";
			break;
		case 'b':
			rtrn += "1";
			break;
		case 'c':
			rtrn += "2";
			break;
		case 'd':
			rtrn += "3";
			break;
		case 'e':
			rtrn += "4";
			break;
		case 'f':
			rtrn += "5";
			break;
		case 'g':
			rtrn += "6";
			break;
		case 'h':
			rtrn += "7";
			break;
		default:
				System.out.println("Not a valid input");
				break;
		}
		
		return rtrn;
	}
	
	//this method reads in the letter and number combination of a chess move in order to find the corresponding square in a matrix.
	//the return value is a String of two numbers, x and y, representing the row and column of the box.
	private static String translateB(String move) {
		char yStr = move.charAt(0);
		String xStr = move.substring(1);
		String rtrn = "";
		rtrn += (Integer.parseInt(xStr) - 1 ) + "";
		
		switch(yStr) {
		case 'a':
			rtrn += "7";
			break;
		case 'b':
			rtrn += "6";
			break;
		case 'c':
			rtrn += "5";
			break;
		case 'd':
			rtrn += "4";
			break;
		case 'e':
			rtrn += "3";
			break;
		case 'f':
			rtrn += "2";
			break;
		case 'g':
			rtrn += "1";
			break;
		case 'h':
			rtrn += "0";
			break;
		default:
				System.out.println("Not a valid input");
				break;
		}
		
		return rtrn;
	}
	
}
