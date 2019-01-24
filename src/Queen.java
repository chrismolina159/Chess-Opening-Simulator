
public class Queen extends Piece{

	
	public Queen(String color) {
		this.id = "Queen";
		this.color = color;
	}
	
	protected boolean isValid(int piecePosX, int piecePosY, int pieceDesX, int pieceDesY) {
		int distance = piecePosX - pieceDesX;
		
		if(distance < 0)
			distance *= -1;
		
		if(piecePosY + distance == pieceDesY  || piecePosY - distance == pieceDesY) {
			return true;
		}
		if((piecePosX == pieceDesX && piecePosY != pieceDesY) || (piecePosX != pieceDesX && piecePosY == pieceDesY)) {
			return true;
		}
		return false;
	}
}
