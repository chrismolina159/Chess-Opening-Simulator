
public class Pawn extends Piece{

	private boolean firstMove = true;
	
	public Pawn(String color) {
		this.id = "Pawn";
		this.color = color;
	}
	
	protected boolean isValid(int piecePosX, int piecePosY, int pieceDesX, int pieceDesY) {
		int distanceY = piecePosY - pieceDesY;
		int distanceX = pieceDesX - piecePosX;
		
		if(distanceY < 0)
			distanceY *= -1;
		if(distanceX < 0)
			distanceX *= -1;
		
		if(firstMove) {
			if((distanceX <= 2 && distanceY == 0) || (distanceY == 1 && distanceX == 0) || (distanceY == 1 && distanceX == 1)) {
				firstMove = false;
				return true;
			}
			return false;
		}
		else {
			if((distanceX == 1 && distanceY == 0) ||(distanceY == 1 && distanceX == 1) ) {
				if(firstMove)
					firstMove = false;
				return true;
			}
			return false;
		}
	}
}
