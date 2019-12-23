import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

public class Caza extends AirPlanes {
	
	// ESTABLECEMOS VARIABLES BASICAS DEL CAZA
	protected int capacidad_de_misiles;
	
	// DEFINIMOS EL FRIEND AND ENEMY Y CLAVE DE ENCRIPTACION
	protected boolean aliado;
	protected boolean encriptado;
		
	// DEFINIREMOS EL NUMERO DE MISILES
	protected ArrayList<Misil> inventario = new ArrayList<Misil>();

	public Caza(String marca,
			String matricula,
			String modelo,
			Coordenadas coordenadas,
			int tripulantes,
			tipo_de_motor tipo_de_motor,
			int capacidad_de_misiles,
			boolean aliado) {
		
		super(marca, 
			matricula, 
			modelo, 
			coordenadas,
			tripulantes, 
 			tipo_de_motor);
		
		this.capacidad_de_misiles = capacidad_de_misiles;
		this.aliado = aliado;
	}
	
	public void mostrarDatos() {
		System.out.print("\nAvion de Carga"
				+ "\nMarca ======================> " + this.marca
				+ "\nMatricula ==================> " + this.matricula
				+ "\nModelo =====================> " + this.modelo
				+ "\nCoordenadas ================> " + this.coordenadas.devolverCoordenadas()
				+ "\nTripulantes ================> " + this.tripulantes
				+ "\nVelocidad actual ===========> " + this.velocidad_actual
				+ "\nTipo de motor ==============> " + this.tipo_de_motor
				+ "\nCapacidad de misiles =======> " + this.capacidad_de_misiles
				+ "\nAliado =====================> " + this.aliado
				+ "\nEncriptado =================> " + this.encriptado
				+ "\n"
		);
		
		// LISTAMOS LOS MISILES Y SUS PROPIEDADES Y LAS LISTAMOS
		
		for (int misil = 0; misil < inventario.size(); misil++) {
			System.out.print(inventario.get(misil).mostrarInfoMisil());
		}
		
		System.out.print("\n");
	}
	
	// GETTERS AND SETTERS
	
	public int getCapacidad_de_misiles() {
		return capacidad_de_misiles;
	}

	public void setCapacidad_de_misiles(int capacidad_de_misiles) {
		this.capacidad_de_misiles = capacidad_de_misiles;
	}

	public boolean isAliado() {
		return aliado;
	}

	public void setAliado(boolean aliado) {
		this.aliado = aliado;
	}

	public boolean isEncriptado() {
		return encriptado;
	}

	public void setEncriptado(boolean encriptado) {
		this.encriptado = encriptado;
	}

	public ArrayList<Misil> getInventario() {
		return inventario;
	}

	public void setInventario(ArrayList<Misil> inventario) {
		this.inventario = inventario;
	}
}
