import java.util.ArrayList;

// ESTA CLASE NOS INDICARA LOS AEROPUERTOS EXISTENTES EN EL MAPA
// CUANDO EL AVION ESTACIONE EN UN AEROPUERTO ESTA PERTENECERA A
// LA CLASSE AEROPUERTO EN UNA ARRAYLIST PARA SACARLO ABRA QUE
// LANZAR EL AVION

public class Aeropuerto {
	// VARIABLES GLOBALES 
	protected String nombre_del_aeropuerto;

	// DEFINIMOS EL TIPO DE AEROPUERTO QUE ES PARA LOS AVIONES
	// CON TREN DE ATERRIZAJE DE RUEDAS O DE HIDROAVION
	protected boolean sobre_tierra;
	
	// ESTABLECEMOS EL MAXIMO DE ESPACIO AEREO POR AERNOAVE
	protected int limite_espacio_aereo;
	
	// ASSIGNAMOS LAS COORDENADAS DEL AEROPUERTO
	protected Coordenadas coordenadas;
	
	// LA CANTIDAD DE AVIONES EN EL HANGAR
	protected ArrayList<Carga> carga = new ArrayList<Carga>();
	protected ArrayList<Comercial> comercial = new ArrayList<Comercial>();
	protected ArrayList<Caza> cazas = new ArrayList<Caza>();
	
	
	public Aeropuerto(String nombre_del_aeropuerto,
					boolean sobre_tierra,
					int limite_espacio_aereo,
					Coordenadas coordenadas) {
		
		this.nombre_del_aeropuerto = nombre_del_aeropuerto;
		this.sobre_tierra = sobre_tierra;
		this.limite_espacio_aereo = limite_espacio_aereo;
		this.coordenadas = coordenadas;
		
		this.carga = null;
		this.comercial = null;
		this.cazas = null;
	}
	
	
	public void mostrarInfo() {
		Coordenadas coor = this.getCoordenadas();
		
		System.out.print("\nNombre del aeropuerto ---------------------> " + this.nombre_del_aeropuerto
				+ "\nCoordenadas ID: " + this.getCoordenadas()
				+ "\n      Coordenada X ------------------------------->" + coor.x
				+ "\n      Coordenada Y ------------------------------->" + coor.y
				+ "\n      Coordenada Z ------------------------------->" + coor.z
				+ "\nNumero de cazas en el hangas --------------------->" + this.carga.size()
				+ "\nNumero de aviones comerciales en el hangas ------->" + this.carga.size()
				+ "\nNumero de aviones de carga en el hangas ---------->" + this.carga.size()
		);
	}

	public String getNombre_del_aeropuerto() {
		return nombre_del_aeropuerto;
	}
	public void setNombre_del_aeropuerto(String nombre_del_aeropuerto) {
		this.nombre_del_aeropuerto = nombre_del_aeropuerto;
	}
	public boolean isSobre_tierra() {
		return sobre_tierra;
	}
	public void setSobre_tierra(boolean sobre_tierra) {
		this.sobre_tierra = sobre_tierra;
	}
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}
	public ArrayList<Carga> getCarga() {
		return carga;
	}
	public void setCarga(ArrayList<Carga> carga) {
		this.carga = carga;
	}
	public ArrayList<Comercial> getComercial() {
		return comercial;
	}
	public void setComercial(ArrayList<Comercial> comercial) {
		this.comercial = comercial;
	}
	public ArrayList<Caza> getCazas() {
		return cazas;
	}
	public void setCazas(ArrayList<Caza> cazas) {
		this.cazas = cazas;
	}
}
