enum tipo_de_espacio_de_carga {
	FRONTAL,
	TRASERO,
	LATERALES,
	FRONTAL_TRASERO,
	FONTRAL_LATERALES,
	TRASERO_LATERALES,
}

enum tipo_de_fuselaje {
	ANCHO,
	MEDIO,
	PEQUEÑO,
}

public class Carga extends AirPlanes {

	// DEFINIMOS ALGUNAS VARIABLES BASICAS DEL AVION
	protected int cantidad_de_ruedas;
	
	// DEFINIMOS CARACTERISITCAS ESPECIALES DEL AVION ENUM
	protected tipo_de_espacio_de_carga tipo_de_espacio_de_carga;
	protected tipo_de_fuselaje tipo_de_fuselaje;
	
	public Carga(String marca, 
			String matricula, 
			String modelo,
			Coordenadas coordenadas,
			int tripulantes, 
			tipo_de_motor tipo_de_motor,
			int cantidad_de_ruedas,
			tipo_de_espacio_de_carga tipo_de_espacio_de_carga,
			tipo_de_fuselaje tipo_de_fuselaje) {
		
		super(marca, 
			matricula, 
			modelo, 
			coordenadas,
			tripulantes, 
			tipo_de_motor);
		
		this.cantidad_de_ruedas = cantidad_de_ruedas;
		this.tipo_de_espacio_de_carga = tipo_de_espacio_de_carga;
		this.tipo_de_fuselaje = tipo_de_fuselaje;
	}
	
	// FUNCIONES Y METODOS
	public void mostrarDatos() {
		System.out.print("\nAvion de Carga"
				+ "\nMarca ======================> " + this.marca
				+ "\nMatricula ==================> " + this.matricula
				+ "\nModelo =====================> " + this.modelo
				+ "\nCoordenadas ================> " + this.coordenadas.devolverCoordenadas()
				+ "\nTripulantes ================> " + this.tripulantes
				+ "\nVelocidad actual ===========> " + this.velocidad_actual
				+ "\nTipo de motor ==============> " + this.tipo_de_motor
				+ "\nCantidad de ruedas =========> " + this.cantidad_de_ruedas
				+ "\nTipo de espacio de carga ===> " + this.tipo_de_espacio_de_carga
				+ "\nTipo de fuselaje ===========> " + this.tipo_de_fuselaje
				+ "\n"
		);
	}

	
	// GETTERS AND SETTERS
	public int getCantidad_de_ruedas() {
		return cantidad_de_ruedas;
	}
	public void setCantidad_de_ruedas(int cantidad_de_ruedas) {
		this.cantidad_de_ruedas = cantidad_de_ruedas;
	}
	public tipo_de_espacio_de_carga getTipo_de_espacio_de_carga() {
		return tipo_de_espacio_de_carga;
	}
	public void setTipo_de_espacio_de_carga(tipo_de_espacio_de_carga tipo_de_espacio_de_carga) {
		this.tipo_de_espacio_de_carga = tipo_de_espacio_de_carga;
	}
	public tipo_de_fuselaje getTipo_de_fuselaje() {
		return tipo_de_fuselaje;
	}
	public void setTipo_de_fuselaje(tipo_de_fuselaje tipo_de_fuselaje) {
		this.tipo_de_fuselaje = tipo_de_fuselaje;
	}
	
}
