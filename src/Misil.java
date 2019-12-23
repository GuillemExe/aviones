// DEFINO LOS TIPOS DE MISILES QUE HABRA EN JUEGO
// TENDRES TRES TIPOS:
//		COHETES => 5 POSITIONES / 50 METROS
// 		BUSCADOR_DE_CALOR => 10 POSICIONES / 100 METROS
//		FIJACION => 25 POSICIONES / 250 METROS

enum tipo_de_misil {
	COHETES,
	BUSCADOR_DE_CALOR,
	FIJACION
}

public class Misil {
	
	// DEFINIMOS LAS VARIABLES BASICAS DEL MISIL
	protected String nombre_del_misil;
	protected String descripcion_del_misil;
	
	// DEFINIMOS EL TIPO DE MISIL
	protected tipo_de_misil misil_tipo;
	
	// DEFINIMOS EN QUE DISTANCIA SE PODRA DISPARAR
	protected int distancia_de_efectividad;
	
	public Misil(String nombre_del_misil,
			String descripcion_del_misil,
			int peso_del_misil,
			tipo_de_misil misil_tipo) {
		
		// SETEAMOS LOS DATOS
		
		this.nombre_del_misil = nombre_del_misil;
		this.descripcion_del_misil = descripcion_del_misil;
		this.misil_tipo = misil_tipo;
		
		if (misil_tipo == tipo_de_misil.COHETES) {
			this.distancia_de_efectividad = 5;
		} else if (misil_tipo == tipo_de_misil.BUSCADOR_DE_CALOR) {
			this.distancia_de_efectividad = 10;
		} else if (misil_tipo == tipo_de_misil.FIJACION) {
			this.distancia_de_efectividad = 25;
		}
	}
		
	public String mostrarInfoMisil() {
		
		// DEVOLVEMOS LA INFORMACION DEL MISIL
		
		String informacionMisil;
		informacionMisil = "\n"
				+ "\nNombre del misil ============> " + this.nombre_del_misil
				+ "\nDescripcion del misil =======> " + this.descripcion_del_misil
				+ "\nTipo de misil ===============> " + this.misil_tipo
				+ "\nDistancia de efectividad ====> " + this.distancia_de_efectividad;
		
		return informacionMisil;
	}

	// GETTERS Y SETTERS
	public String getNombre_del_misil() {
		return nombre_del_misil;
	}
	public void setNombre_del_misil(String nombre_del_misil) {
		this.nombre_del_misil = nombre_del_misil;
	}
	public String getDescripcion_del_misil() {
		return descripcion_del_misil;
	}
	public void setDescripcion_del_misil(String descripcion_del_misil) {
		this.descripcion_del_misil = descripcion_del_misil;
	}
	public int getDistancia_de_efectividad() {
		return distancia_de_efectividad;
	}
	public void setDistancia_de_efectividad(int distancia_de_efectividad) {
		this.distancia_de_efectividad = distancia_de_efectividad;
	}
	public tipo_de_misil getMisil_tipo() {
		return misil_tipo;
	}
	public void setMisil_tipo(tipo_de_misil misil_tipo) {
		this.misil_tipo = misil_tipo;
	}
}