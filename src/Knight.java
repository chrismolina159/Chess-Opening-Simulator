
public class Knight extends Piece {
	
	public Knight(String color) {
		this.id = "Knight";
		this.color = color;
	}
	
	protected boolean isValid(int piecePosX, int piecePosY, int pieceDesX, int pieceDesY) {
		int first = piecePosX - pieceDesX;
		int second = piecePosY - pieceDesY;
		
		if(first < 0)
			first *= -1;
		if(second < 0)
			second *= -1;
		
		return (first + second) == 3;
	}
}
