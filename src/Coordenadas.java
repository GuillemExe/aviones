public class Coordenadas {
	protected int x;
	protected int y;
	protected int z;
	
	public Coordenadas(int x,
			int y,
			int z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// METODOS BASICOS
	protected String devolverCoordenadas() {
		String coordenadas;
		coordenadas = "X :" + x + ", Y: " + y + ", Z: " + z;
		
		return coordenadas;
	}

	// GETTERS AND SETTERS
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
}
