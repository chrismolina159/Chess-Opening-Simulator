
public class Bishop extends Piece{
	
	public Bishop(String color) {
		this.id = "Bishop";
		this.color = color;
	}
	
	protected boolean isValid(int piecePosX, int piecePosY, int pieceDesX, int pieceDesY) {
		int distance = piecePosX - pieceDesX;
		
		if(distance < 0)
			distance *= -1;
		
		if(piecePosY + distance == pieceDesY  || piecePosY - distance == pieceDesY) {
			return true;
		}
		return false;
	}
}
