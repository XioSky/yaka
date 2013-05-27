package yaka;

public class Mouvement {
	private int deltaX, deltaY;

	public Mouvement(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	@Override
	public String toString() {
		return "[" + deltaX + ", " + deltaY + "]";
	}

	// plus simple à visualiser sur un pavé numérique
	public int moveCode() {

		if (deltaX == 0) {

			if (deltaY == 1) {

				return 0;

			} else if (deltaY == -1) {

				return 4;
			}
		}

		if (deltaX == -1) {

			if (deltaY == 0) {

				return 2;

			} else if (deltaY == 1) {

				return 3;

			} else if (deltaY == -1) {

				return 1;

			}
		}
		return -1;
	}
}
