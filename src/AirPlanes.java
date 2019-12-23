enum tipo_de_motor {
	TURBOFAN,
	TURBORREACTOR,
	DE_PISTON,
	TURBOHELICE,
}

abstract class AirPlanes {
	// VALORES GLOBALES
	protected String marca;
	protected String matricula;
	protected String modelo;
	
	// COORDENADAS X, Y, Z
	protected Coordenadas coordenadas;
	
	// VARIABLES BASICAS
	protected int tripulantes;
	protected int rumbo;
	
	// FACTORES DE VUELO
	protected int velocidad_actual;
	
	// ENUMS TIPO DE MOTOR
	protected tipo_de_motor tipo_de_motor;
	
	// ACTIVADORES
	protected boolean estado_de_motores;
	protected boolean estado_del_tren_de_aterrizaje_desplegado;
	protected boolean estado_de_los_flaps;
	protected boolean estacionado;
	
	
	// CONSTRUCTOR
	public AirPlanes(String marca,
			String matricula,
			String modelo,
			Coordenadas coordenadas,
			int tripulantes,
			tipo_de_motor tipo_de_motor) {
		
		// NOS GUARDAMOS LO RECIBIDO
		this.marca = marca;
		this.matricula = matricula;
		this.modelo = modelo;
		
		// MANTENEMOS LOS CARDENADAS
		this.coordenadas = coordenadas;
		
		// BASICOS
		this.tripulantes = tripulantes;
		
		// DEFINICION EXTRAS
		this.tipo_de_motor = tipo_de_motor;
		
		// ESCRIBIMOS LOS DATOS POR DEFECTO AL CREAR CADA AVION
		this.estacionado = false;
		this.estado_de_motores = false;
		this.estado_del_tren_de_aterrizaje_desplegado = true;
		this.estado_de_los_flaps = true;
	}
	
	

	// METODOS Y FUNCIONES
	

	// SUBIR Y BAJAR EL AVION
	public void modificarAltitud(String altitud) {
		switch(altitud) {
		case "SUBIR_MUCHO":
			aumentarAltitud(250);
			break;
		case "SUBIR":
			aumentarAltitud(50);
			break;
		case "SUBIR_POCO":
			aumentarAltitud(10);
			break;
		case "BAJAR_MUCHO":
			disminuirAltitud(250);
			break;
		case "BAJAR":
			disminuirAltitud(50);
			break;
		case "BAJAR_POCO":
			disminuirAltitud(10);
			break;
		}
	}
	
	public void aumentarAltitud(int altitudSubir) {
		this.getCoordenadas().setZ(this.getCoordenadas().getZ() + altitudSubir);
	}
	public void disminuirAltitud(int altitudBajar) {
		this.getCoordenadas().setZ(this.getCoordenadas().getZ() - altitudBajar);
	}
	
	abstract void mostrarDatos();

	// GETTERS AND SETTER
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

	public int getTripulantes() {
		return tripulantes;
	}

	public void setTripulantes(int tripulantes) {
		this.tripulantes = tripulantes;
	}

	public int getRumbo() {
		return rumbo;
	}

	public void setRumbo(int rumbo) {
		this.rumbo = rumbo;
	}

	public int getVelocidad_actual() {
		return velocidad_actual;
	}

	public void setVelocidad_actual(int velocidad_actual) {
		this.velocidad_actual = velocidad_actual;
	}

	public tipo_de_motor getTipo_de_motor() {
		return tipo_de_motor;
	}

	public void setTipo_de_motor(tipo_de_motor tipo_de_motor) {
		this.tipo_de_motor = tipo_de_motor;
	}

	public boolean isEstado_de_motores() {
		return estado_de_motores;
	}

	public void setEstado_de_motores(boolean estado_de_motores) {
		this.estado_de_motores = estado_de_motores;
	}

	public boolean isEstado_del_tren_de_aterrizaje_desplegado() {
		return estado_del_tren_de_aterrizaje_desplegado;
	}

	public void setEstado_del_tren_de_aterrizaje_desplegado(boolean estado_del_tren_de_aterrizaje_desplegado) {
		this.estado_del_tren_de_aterrizaje_desplegado = estado_del_tren_de_aterrizaje_desplegado;
	}

	public boolean isEstado_de_los_flaps() {
		return estado_de_los_flaps;
	}

	public void setEstado_de_los_flaps(boolean estado_de_los_flaps) {
		this.estado_de_los_flaps = estado_de_los_flaps;
	}

	public boolean isEstacionado() {
		return estacionado;
	}

	public void setEstacionado(boolean estacionado) {
		this.estacionado = estacionado;
	}
}
