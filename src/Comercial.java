
public class Comercial extends AirPlanes {
	
	// DEFINIMOS LAS VARIABLES BASICAS
	protected int capacidad_pasajeros;
	protected int plantas_del_avion;

	public Comercial(String marca, 
			String matricula, 
			String modelo, 
			Coordenadas coordenadas,
			int tripulantes,
			tipo_de_motor tipo_de_motor,
			int capacidad_pasajeros,
			int plantas_del_avion) {
		
		super(marca, 
				matricula, 
				modelo, 
				coordenadas,
				tripulantes, 
				tipo_de_motor);
		
		this.capacidad_pasajeros = capacidad_pasajeros;
		this.plantas_del_avion = plantas_del_avion;
	}
	
	
	// FUNCIONES Y METODOS
		public void mostrarDatos() {
			System.out.print("\nAvion de Carga"
					+ "\nMarca ======================> " + this.marca
					+ "\nMatricula ==================> " + this.matricula
					+ "\nModelo =====================> " + this.modelo
					+ "\nCoordenadas ================> " + this.coordenadas.devolverCoordenadas()
					+ "\nTripulantes ================> " + this.tripulantes
					+ "\nVelocidad actual ===========> " + this.velocidad_actual					+ "\nTipo de motor ==============> " + this.tipo_de_motor
					+ "\nCapacidad de pasajeros =====> " + this.capacidad_pasajeros
					+ "\nPlantas del avion ==========> " + this.plantas_del_avion
					+ "\n"
		);
		}

	public int getCapacidad_pasajeros() {
		return capacidad_pasajeros;
	}

	public void setCapacidad_pasajeros(int capacidad_pasajeros) {
		this.capacidad_pasajeros = capacidad_pasajeros;
	}

	public int getPlantas_del_avion() {
		return plantas_del_avion;
	}

	public void setPlantas_del_avion(int plantas_del_avion) {
		this.plantas_del_avion = plantas_del_avion;
	}
}
