
public class King extends Piece{

	public King(String color) {
		this.id = "King";
		this.color = color;
	}
	
	protected boolean isValid(int piecePosX, int piecePosY, int pieceDesX, int pieceDesY) {
		int distanceX = piecePosX - pieceDesX;
		int distanceY = piecePosY - pieceDesY;
		
		if(distanceX <  0)
			distanceX *= -1;
		if(distanceY <  0)
			distanceY *= -1;
		if(distanceX + distanceY > 2 || distanceX > 1 || distanceY > 1)
			return false;
		return true;
	}
}
