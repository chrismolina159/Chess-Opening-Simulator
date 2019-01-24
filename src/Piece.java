
public abstract class Piece {

	protected String id;
	protected String color;
	
	public String getID() {
		return this.id;
	}
	
	protected String getColor() {
		return this.color;
	}

	protected abstract boolean isValid(int pTMX, int pTMY, int pDX, int pDY);
}
