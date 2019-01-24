
public class Rook extends Piece {
	
	public Rook(String color) {
		this.id = "Rook";
		this.color = color;
	}
	
	protected boolean isValid(int piecePosX, int piecePosY, int pieceDesX, int pieceDesY) {
		if((piecePosX == pieceDesX && piecePosY != pieceDesY) || (piecePosX != pieceDesX && piecePosY == pieceDesY)) {
			return true;
		}
		else
			return false;
	}
}
