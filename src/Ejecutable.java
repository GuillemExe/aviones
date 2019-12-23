import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class Ejecutable {
	
	Scanner lectura = new Scanner(System.in);
	
	ArrayList<Coordenadas> coordenadas = new ArrayList<Coordenadas>();
	ArrayList<Carga> carga = new ArrayList<Carga>();
	ArrayList<Comercial> comercial = new ArrayList<Comercial>();
	ArrayList<Caza> cazas = new ArrayList<Caza>();
	int longitudX = 30;
	int longitudY = 30;
	char[][] mapa = new char[longitudX][longitudY];
	
	public static void main(String[] args) {
		Ejecutable inst = new Ejecutable();
		inst.exe();
	}
	
	public void exe() {
		generarMapa();
		menuPrincipal();
	}
	
	public void generarMapa() {
		for (int x = 0; x < longitudX; x++) {
			for (int y = 0; y < longitudY; y++) {
				mapa [y][x] = '·';
			}
		}
	}
	
	public void mostrarMapa() {
		
		// GENERO UN ESPACIO PARA EVITAR QUE SE JUNTO CON EL TEXTO
		System.out.println("");
		
		for (int x = 0; x < longitudX; x++) {
			for (int y = 0; y < longitudY; y++) {
				System.out.print(mapa[y][x] + " ");
			}
			System.out.println("");
		}
	}
	
	public void menuPrincipal() {
		int opcionMenuPrincipal = 0;
		boolean esValidoValor = false;
		
		do {
			interfazMenuPrincipal();
			
			do {
				if (lectura.hasNextInt()) {
					opcionMenuPrincipal = lectura.nextInt();
					esValidoValor = true;
				} else {
					lectura.nextLine();
					System.out.print("ERROR: El valor introducido no es un valor numerico");
				}
			} while (!esValidoValor);
			
			switch(opcionMenuPrincipal) {
			case 1:
				generarAvion();
				break;
			case 2:
				gestionarAvion();
				break;
			case 3:
				situacionAeronaves();
				break;
			case 4:
				encriptar();
				break;
			case 5:
				desencriptar();
				break;
			case 6:
				generarAvionesCarga();
				generarAvionesComercial();
				generarAvionesCazas();
				break;
			case 7:
				mostrarAviones();
				break;
			case 8:
				break;
			default:
				System.out.print("\nSeleciona una opcion valida");
			}
		} while (opcionMenuPrincipal != 10);
	}
	
	public void generarAvion() {
		int opcionGenerarAvion = 0;
		boolean esValidoValor = false;
		
		do {
			interfazGenerarAvion();
			
			do {
				if (lectura.hasNextInt()) {
					opcionGenerarAvion = lectura.nextInt();
					esValidoValor = true;
				} else {
					lectura.nextLine();
					System.out.print("ERROR: El valor introducido no es un valor numerico");
				}
			} while (!esValidoValor);
			
			switch(opcionGenerarAvion) {
			case 1:
				generarFormularioAvion("CAZA");
				break;
			case 2:
				generarFormularioAvion("COMERCIAL");
				break;
			case 3:
				generarFormularioAvion("CARGA");
				break;
			}
		} while (opcionGenerarAvion != 4);
	}
	
	public void gestionarAvion() {
		String matricula;
		
		System.out.print("\nIntroduce la matricula del avion a gestionar: ");
		matricula = lectura.next();
		
		// RECORREMOS TODOS LOS AVIONES EN BUSCA DE LA MATRICULA
		
		// COMPROBAMOS CARGA
		for (int x = 0; x < carga.size(); x++) {
			if (carga.get(x).getMatricula().contentEquals(matricula)) {
				controlAeronave("CARGA", x);
			}
		}
		
		// COMPROBAMOS COMERCIAL
		for (int x = 0; x < comercial.size(); x++) {
			if (comercial.get(x).getMatricula().contentEquals(matricula)) {
				controlAeronave("COMERCIAL", x);
			}
		}
		
		// COMPROBAMOS CAZA
		for (int x = 0; x < cazas.size(); x++) {
			if (cazas.get(x).getMatricula().contentEquals(matricula)) {
				controlAeronave("CAZA", x);
			}
		}
	}
	
	public void controlAeronave(String tipoAeronave, int numeroIndex) {
		int opcionGenerarAvion = 0;
		boolean esValidoValor = false;
		int velocidadSumar, velocidadRestada, altitudSumar;
			
		switch(tipoAeronave) {
		case "CARGA":
			do {
				interfazControlAeronavePacifica();
				
				do {
					if (lectura.hasNextInt()) {
						opcionGenerarAvion = lectura.nextInt();
						esValidoValor = true;
					} else {
						lectura.nextLine();
						System.out.print("ERROR: El valor introducido no es un valor numerico");
					}
				} while (!esValidoValor);
				
				switch(opcionGenerarAvion) {
				case 1:
					if (carga.get(numeroIndex).isEstado_de_motores() == true) {
						System.out.print("\nLos motores ya estaban encendidos");
					} else {
						carga.get(numeroIndex).setEstado_de_motores(true);
						System.out.print("\nLos motores estan encendidos");
					}
					break;
				case 2:
					if (carga.get(numeroIndex).getCoordenadas().getZ() > 0) {
						carga.get(numeroIndex).setEstado_de_motores(false);
						System.out.print("\nSe han apagado los motores");
					} else {
						System.out.print("\nNo puedes apagar los motores una vez estas en vuelo");
					}
					break;
				case 3:
					System.out.print("\nIntroduce la velocidad a sumar de la actual: ");
					velocidadSumar = lectura.nextInt();
					
					carga.get(numeroIndex).setVelocidad_actual(carga.get(numeroIndex).getVelocidad_actual() + velocidadSumar);
					System.out.print("\nHas aumentado la velocidad a " + carga.get(numeroIndex).getVelocidad_actual() + "km/h");
					break;
				case 4:
					System.out.print("\nIntroduce la velocidad a disminuir de la actual: ");
					velocidadRestada = lectura.nextInt();
					
					if (carga.get(numeroIndex).getCoordenadas().getZ() == 0) {
						carga.get(numeroIndex).setVelocidad_actual(carga.get(numeroIndex).getVelocidad_actual() - velocidadRestada);
						System.out.print("\nHas diminuido la velocidad a " + carga.get(numeroIndex).getVelocidad_actual() + "km/h");
					} else {
						if ((carga.get(numeroIndex).getVelocidad_actual() - velocidadRestada) < 180) {
							System.out.print("\nNo puede bajar de esa velocidad, podria ocurrir algo malo");
						} else {
							carga.get(numeroIndex).setVelocidad_actual(carga.get(numeroIndex).getVelocidad_actual() - velocidadRestada);
							System.out.print("\nHas diminuido la velocidad a " + carga.get(numeroIndex).getVelocidad_actual() + "km/h");
						}
					}
					break;
				case 5:
					System.out.print("\nIntroduce la altitud a sumar de la actual: ");
					altitudSumar = lectura.nextInt();
					
					if(carga.get(numeroIndex).getCoordenadas().getZ() == 0) {
						if(carga.get(numeroIndex).getVelocidad_actual()  >= 180) {
							carga.get(numeroIndex).getCoordenadas().setZ(altitudSumar);
							System.out.print("\nEstas despegando, estas a una altitud de " + carga.get(numeroIndex).getCoordenadas().getZ());
						} else {
							System.out.print("\nEl avion carece de la suficiente velocidad para elevarse");
						}
					} else if ((carga.get(numeroIndex).getCoordenadas().getZ() + altitudSumar) < 500 && carga.get(numeroIndex).getCoordenadas().getZ() > 0) {
						carga.get(numeroIndex).getCoordenadas().setZ(altitudSumar);
						System.out.print("\nHas aumentado la altitud, ahora estas en " + carga.get(numeroIndex).getCoordenadas().getZ());
					} else if ((carga.get(numeroIndex).getCoordenadas().getZ() + altitudSumar) > 500) {
						System.out.print("No puedes aumentar la velocidad hasta que replieges el tren de aterrizaje");
					}
					break;
				case 6:
					
					break;
				case 7:
					
					break;
				case 8:
					
					break;
				case 9:
					
					break;
				case 10:
					
					break;
				}
				
			} while (opcionGenerarAvion != 11);
			break;

		case "COMERCIAL":
			
			break;
		case "CAZA":
			break;
		}
	}
	
	public void interfazControlAeronavePacifica() {
		System.out.print("\n\nQue tipo de avion deseas agregar"
				+ "\n"
				+ "\n      1.- Encender motores"
				+ "\n      2.- Apagar motores"
				+ "\n      3.- Accelerar"
				+ "\n      4.- Frenar"
				+ "\n      5.- Aumentar altitud"
				+ "\n      6.- Bajar altitud"
				+ "\n      7.- Subir / Bajar tren de aterrizaje"
				+ "\n      8.- Establecer un rumbo"
				+ "\n      9.- Modificar coordenadas"
				+ "\n     10.- Resumen del avion"
				+ "\n     11.- Retroceder"
				+ "\n"
				+ "\nEscoge una opcion: ");
	}
	
	public void interfazControlAeronaveMilitar() {
		System.out.print("\n\nQue tipo de avion deseas agregar"
				+ "\n"
				+ "\n      1.- Encender motores"
				+ "\n      2.- Apagar motores"
				+ "\n      3.- Accelerar"
				+ "\n      4.- Frenar"
				+ "\n      5.- Aumentar altitud"
				+ "\n      6.- Bajar altitud"
				+ "\n      7.- Subir / Bajar tren de aterrizaje"
				+ "\n      8.- Establecer un rumbo"
				+ "\n      9.- Modificar coordenadas"
				+ "\n     10.- Resumen del avion"
				+ "\n     11.- Disparar a un avion"
				+ "\n     12.- Retroceder"
				+ "\n"
				+ "\nEscoge una opcion: ");
	}
	
	public void generarFormularioAvion(String tipo) {
		
		// INTRODUCIMOS LAS VARIABLES COMUNDES QUE TENDRAN TODOS LOS AVIONES
		
		String valorMarca, valorMatricula, valorModelo;
		int valorTripulantes; 
		
		// DECLARAMOS DOS VALORES NUMERICOS PARA ESCOGER EL ENUM, PARA SIMPLIFICAR LA INTRODUCION DE ESTOS
		
		int opcionTipoMotor = 0;
		tipo_de_motor valorTipoMotor = tipo_de_motor.TURBORREACTOR;
		
		// DECLARAMOS LAS VARIABLES DEL CAZA
		
		int valorCapacidadMisiles;
		boolean aliado;
		
		// DECLARAMOS LAS VARIABLES DEL AVION COMERCIAL
		
		int capacidadPasajeros;
		int plantasDelAvion;
		
		// DECLARAMOS LAS VARIABLES DEL AVION DE CARGA
		
		int cantidadDeRuedas;
		tipo_de_espacio_de_carga tipoDeEspacioDeCarga = tipo_de_espacio_de_carga.FRONTAL;
		tipo_de_fuselaje tipoDeFuselaje = tipo_de_fuselaje.PEQUEÑO;
		
		
		// EMPEZAMOS A PREGUNTAR AL USUARIO LO QUE PIDE
		
		System.out.print("\nMarca (String)----------------------- : ");
		valorMarca = lectura.next();
			
		System.out.print("Matricula (String)------------------- : ");
		valorMatricula = lectura.next();
			
		System.out.print("Modelo (String) --------------------- : ");
		valorModelo = lectura.next();
			
		System.out.print("Tripulantes (int) ------------------- : ");
		valorTripulantes = lectura.nextInt();
		
		// ESCOGEMOS EL TIPO DE MOTOR QUE UTILIZARA EL AVION EN TODOS SUS MOTORES
		
		System.out.print("\nTipo de motor"
				+ "\n"
				+ "\n     1.- Turbofan"
				+ "\n     2.- Turborreactor"
				+ "\n     3.- De piston"
				+ "\n     4.- Turbohelice"
				+ "\n"
				+ "\nEscoge el tipo de motor para tu avion (Opcion numerica '1, 2, 3, 4'): ");
		opcionTipoMotor = lectura.nextInt();
		
		switch(opcionTipoMotor) {
		case 1:
			valorTipoMotor = tipo_de_motor.TURBOFAN;
			break;
		case 2:
			valorTipoMotor = tipo_de_motor.TURBORREACTOR;
			break;
		case 3:
			valorTipoMotor = tipo_de_motor.DE_PISTON;
			break;
		case 4:
			valorTipoMotor = tipo_de_motor.TURBOHELICE;
			break;
		}
		
		switch(tipo) {
		
		case "CAZA":
			System.out.print("Capacidad de misiles (int) --------- : ");
			valorCapacidadMisiles = lectura.nextInt();
			
			System.out.print("Es aliado? s(Si) / n(No) ----------- : ");
			char respuestaAlidado = lectura.next().charAt(0);
			
			if (respuestaAlidado == 's' || respuestaAlidado == 'S') {
				aliado = true;
			} else {
				aliado = false;
			}
			
			Coordenadas coorCaza = new Coordenadas(0,0,0);
			
			Caza cazaCreate = new Caza(valorMarca, valorMatricula, valorModelo,
					coorCaza, valorTripulantes,
					valorTipoMotor,
					valorCapacidadMisiles, 
					aliado);
			
			cazas.add(cazaCreate);
			
			break;
			
		case "COMERCIAL":
			System.out.print("Capacidad de pasajeros (int) ------- : ");
			capacidadPasajeros = lectura.nextInt();
			
			System.out.print("Plantas del avion (int) ------------ : ");
			plantasDelAvion = lectura.nextInt();
			
			Coordenadas coorComercial = new Coordenadas(0,0,0);
			
			Comercial comercialCreate = new Comercial(valorMarca, valorMatricula, valorModelo,
					coorComercial, valorTripulantes, 
					valorTipoMotor,
					capacidadPasajeros, 
					plantasDelAvion);
			
			comercial.add(comercialCreate);
			
			break;
			
		case "CARGA":
			System.out.print("Cantidad de ruedas (int) ----------- : ");
			cantidadDeRuedas = lectura.nextInt();
			
			System.out.print("\nSistema de carga"
					+ "\n"
					+ "\n     1.- Frontal"
					+ "\n     2.- Trasero"
					+ "\n     3.- Lateral"
					+ "\n     4.- Frontal y trasero"
					+ "\n     5.- Frontal y laterales"
					+ "\n     6.- Trasero y laterales"
					+ "\n"
					+ "\nEscoge el tipo de sistema de carga de tu avion (Opcion numerica '1, 2, 3, 4, 5 y 6') : ");
			opcionTipoMotor = lectura.nextInt();
			
			switch(opcionTipoMotor) {
			case 1:
				tipoDeEspacioDeCarga = tipo_de_espacio_de_carga.FRONTAL;
				break;
			case 2:
				tipoDeEspacioDeCarga = tipo_de_espacio_de_carga.TRASERO;
				break;
			case 3:
				tipoDeEspacioDeCarga = tipo_de_espacio_de_carga.LATERALES;
				break;
			case 4:
				tipoDeEspacioDeCarga = tipo_de_espacio_de_carga.FRONTAL_TRASERO;
				break;
			case 5:
				tipoDeEspacioDeCarga = tipo_de_espacio_de_carga.FONTRAL_LATERALES;
				break;
			case 6:
				tipoDeEspacioDeCarga = tipo_de_espacio_de_carga.TRASERO_LATERALES;
				break;
			}
			
			System.out.print("\nTipo de fuselae"
					+ "\n"
					+ "\n     1.- Ancho"
					+ "\n     2.- Medio"
					+ "\n     3.- Liger"
					+ "\n"
					+ "\nEscoge el tipo de sistema de carga de tu avion (Opcion numerica '1, 2, 3, 4, 5 y 6') : ");
			opcionTipoMotor = lectura.nextInt();
			
			switch(opcionTipoMotor) {
			case 1:
				tipoDeFuselaje = tipo_de_fuselaje.ANCHO;
				break;
			case 2:
				tipoDeFuselaje = tipo_de_fuselaje.MEDIO;
				break;
			case 3:
				tipoDeFuselaje = tipo_de_fuselaje.PEQUEÑO;
				break;
			}
			
			Coordenadas coorCarga = new Coordenadas(0,0,0);
			
			Carga cargaCreate = new Carga(valorMarca, valorMatricula, valorModelo,
					coorCarga, valorTripulantes,
					valorTipoMotor,
					cantidadDeRuedas, 
					tipoDeEspacioDeCarga,
					tipoDeFuselaje);
			
			carga.add(cargaCreate);
			
			break;
			
		}
	}
	
	
	public void interfazGenerarAvion() {
		System.out.print("\n\nQue tipo de avion deseas agregar"
				+ "\n"
				+ "\n     1.- Crear caza"
				+ "\n     2.- Crear avaion comercial"
				+ "\n     3.- Crear avion de cargar"
				+ "\n     4.- Retroceder"
				+ "\n"
				+ "\nEscoge una opcion: ");
	}
	
	public void interfazMenuPrincipal() {
		System.out.print("\n\nControl del cielo"
				+ "\n"
				+ "\n     1.- Agregar un nuevo avion"
				+ "\n     2.- Gestionar un avion"
				+ "\n     3.- Mostrar espacio aereo"
				+ "\n     4.- Cifrar los cazas"
				+ "\n     5.- Descrifrar los cazas"
				+ "\n     6.- Generaracion de aviones predeterminada"
				+ "\n     7.- Mostrar todos los aviones (Debug)"
				+ "\n     8.- Mostrar mapa"
				+ "\n"
				+ "\nEscoge una opcion: ");
	}
	
	public void mostrarAviones() {
		for (int x = 0; x < carga.size(); x++) {
			this.carga.get(x).mostrarDatos();
		}
		for (int x = 0; x < cazas.size(); x++) {
			this.cazas.get(x).mostrarDatos();
		}
	}
	
	public void generarAvionesCarga() {
		Coordenadas coorCarga1 = new Coordenadas(0,0,0);
		Coordenadas coorCarga2 = new Coordenadas(0,0,0);
		
		Carga carga01 = new Carga("Airbus", "0001", "A310F",
				coorCarga1, 4,
				tipo_de_motor.TURBORREACTOR,
				20,
				tipo_de_espacio_de_carga.TRASERO_LATERALES,
				tipo_de_fuselaje.MEDIO);
		
		Carga carga02 = new Carga("Boeing", "0101", "B737-300F",
				coorCarga2, 4,
				tipo_de_motor.TURBORREACTOR,
				24, 
				tipo_de_espacio_de_carga.TRASERO_LATERALES,
				tipo_de_fuselaje.MEDIO);
		
		carga.add(carga01);
		carga.add(carga02);
	}
	
	public void generarAvionesComercial() {
//		Coordenadas coorComercial1 = new Coordenadas(0,0,0);
//		Coordenadas coorComercial2 = new Coordenadas(0,0,0);
		
//		Comercial comercial01 = new Comercial("Comercial Barce", "EC-001", "B737-300F",
//				coorComercial1,
//				400, 20, 500, 170, 180, 8200,
//				tipo_de_motor.TURBORREACTOR,
//				120, 1);
//		
//		Comercial comercial02 = new Comercial("Comercial Madri", "EC-001", "B737-300F",
//				coorComercial2,
//				400, 20, 500, 170, 180, 8200,
//				tipo_de_motor.TURBORREACTOR,
//				50, 1);
//		
//		comercial.add(comercial01);
//		comercial.add(comercial02);
	}
	
	// ESTA FUNCION NOS GENERARA UN NUMERO RANDOM PARA APLICAR LA FUNCIONALIDAD DEL SALT
	public static String generateRandom(int numeroMaximo) {
			int random = (int) (Math.random()*numeroMaximo);
			
			String value = String.valueOf(random);
		return value;
	}
	
	public void encriptar() {
		// DECLARAMOS MATRICULA A INTRODUCIR
		int state = 0;
		String matriculaIntroducida;
		
		System.out.print("\nIntroduce la matricula del caza que deseas encriptar: ");
		matriculaIntroducida = lectura.next();
		
		for (int x = 0; x < cazas.size(); x++) {
			if(cazas.get(x).getMatricula().contentEquals(matriculaIntroducida)) {
				if(cazas.get(x).isAliado() == true) {
					
					// GENERAMOS UN NUMERO RANDOM PARA EL SALT Y LO AGREGAMOS AL FINAL DE ESTE
					String contenidoAuxMatricula = cazas.get(x).getMatricula() + generateRandom(10);
					
					// SI ES ALIADO Y CORRESPONE LA MATRICULA NOS LA GUARDAMOS
					String contenidoMatricula = Base64.getEncoder().encodeToString(contenidoAuxMatricula.getBytes(StandardCharsets.UTF_16));
					
					// CREAMOS EL NOMBRE DEL DOCUMENTO
					String nombreDocumento = cazas.get(x).getMatricula() + ".gpg";
				
					// CREAMOS EL NOMBRE DEL FICHERO
					try (FileWriter escrito = new FileWriter(nombreDocumento, true); 
							BufferedWriter lector = new BufferedWriter(escrito); 
							PrintWriter print = new PrintWriter(lector)) {

						print.write("");
						print.print(contenidoMatricula);
						
						lector.close();
						
						cazas.get(x).setEncriptado(true);
			            state = 3;
			            x = cazas.size();
			            
			        } catch (Exception e) {
			            System.out.print("\n" + e);
			        }
					
				} else {
					state = 1;
				}
			} else {
				state = 2;
			}
		}
		
		if (state == 1) {
			System.out.print("\nEl caza que has introducido no es aliado");
		} else if (state == 2) {
			System.out.print("\nNingun caza coincide con la matricula");
		} else if (state == 3) {
			System.out.print("\nSe ha encriptado el caza correcamente");
		}
	}
	
	public void desencriptar() {
		// DECLARAMOS MATRICULA A INTRODUCIR
		String contenidoAuxMatricula, matriculaIntroducida, contenidoMatricula, lecturaContenidoDocumento = null;
		
		// LE PEDIMOS QUE ESCRIBA LA MATRICULA DEL AVION QUE ENCRIPTO
		System.out.print("\nIntroduce la matricula del caza que deseas desencriptar: ");
		matriculaIntroducida = lectura.next();
		
		try {
			String nombreDocumento = matriculaIntroducida + ".gpg";
			lecturaContenidoDocumento = new String(Files.readAllBytes(Paths.get(nombreDocumento)));
				
			// HACEMOS UN LOOP HASTA ENCONTRAR LA CLAVE CON EL NUMERO RANDOM GENERADO AGREGADO AL FINAL
			do {
				contenidoAuxMatricula = matriculaIntroducida + generateRandom(10);
				
				contenidoMatricula = Base64.getEncoder().encodeToString(contenidoAuxMatricula.getBytes(StandardCharsets.UTF_16));
			} while(!lecturaContenidoDocumento.equals(contenidoMatricula));
			
			// SI LA MATRICULA ES VALIDA DAREMOS PASO A BUSCAR LA CLAVE Y CAMBIAR LA FLAG DE ENCRIPTADO
			if (lecturaContenidoDocumento.equals(contenidoMatricula)) {
				for (int x = 0; x < cazas.size(); x++) {
					if(cazas.get(x).getMatricula().contentEquals(matriculaIntroducida)) {
						if(cazas.get(x).isEncriptado() == true) {
							cazas.get(x).setEncriptado(false);
							System.out.print("\nHas desencriptado el caza " + matriculaIntroducida + " ahora lo puedes ver sus datos.");
							
							// BORRAMOS EL DOCUMENTO
							File documento = new File(nombreDocumento);
							documento.delete();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.print("No se ha encontrado el documento para desencriptar la matricula del avion introducido");
		}
	}
	
	public void generarAvionesCazas() {
		Coordenadas coorCaza1 = new Coordenadas(0,0,0);
		Coordenadas coorCaza2 = new Coordenadas(0,0,0);
		
		Caza caza1 = new Caza("Bell Airacobra", "5050", "P-39",
				coorCaza1, 1,
				tipo_de_motor.TURBOHELICE,
				10, true);
		
		Caza caza2 = new Caza("Messerschmitt", "1010", "Bf 109",
				coorCaza2, 2,
				tipo_de_motor.TURBOHELICE,
				10, false);
		
		cazas.add(caza1);
		cazas.add(caza2);
	}
	
	public void situacionAeronavesDeprecated() {
		// DECLARAMOS EL CONTENIDO DE LA COLUMNA FANTASMA
		// LA QUE SERIA LA PRIMERA COLUMNA
		String columnaFantasma = "";
		
		// DECLARAMOS EL CONTADOR
		// SUMAMOS TODOS LOS AVIONES DE TODOS OS TIPOS
		int numeroDeNaves = carga.size() + comercial.size() + cazas.size();
		
		// DECLARAMOS EL NOMBRE BASICO DEL ENCABEZADO
		String tituloEncabezado = "  Aeronave ";
		
		// EL MAS UNO ES EL ESPACIO FANTASTMA DONDE PONDREMOS
		// EL TITULO DE LAS PROPIEDADES
		String[] aeronaves = new String[numeroDeNaves + 1];
		
		// DECLARAMOS EL TAMAÑO DE LA COLUMNA CON UN INT
		// QUE DEPSUES LO USAREMOS PARA RESTAR EL CONTENIDO
		// DE CADA FILA Y AGREGARLO CORRECTAMENTE
		int widthColumna = 30;
		
		// CREAMOS ESE ESPACIO FANTASMA Y
		// LO AGREGAMOS AL ARRAY DEL NOMBRE DE AERONAVES
		for (int espacios = 0; espacios < widthColumna; espacios++) {
			columnaFantasma = columnaFantasma + " ";
		}
		
		aeronaves[0] = columnaFantasma;
		
		for (int encabezadoColumna = 1; encabezadoColumna < numeroDeNaves + 1; encabezadoColumna++) {
			String tituloResidual = tituloEncabezado + encabezadoColumna;
			
			int numeroDeEspaciosAgregar = widthColumna - tituloResidual.length();
			
			String espacioParaAgregar = "";
			
			for(int espacio = 0; espacio < numeroDeEspaciosAgregar; espacio++) {
				espacioParaAgregar = espacioParaAgregar + " ";
			}
			
			aeronaves[encabezadoColumna] = tituloResidual + espacioParaAgregar;
		}
		
		for(int x = 0; x < aeronaves.length; x++) {
			System.out.print(aeronaves[x]);
		}
	}
	
	public void situacionAeronaves() {
		// DECLARAMOS EL CONTENIDO DE LA COLUMNA FANTASMA
		// LA QUE SERIA LA PRIMERA COLUMNA
		String columnaFantasma = "";
		
		// DECLARAMOS EL CONTADOR
		// SUMAMOS TODOS LOS AVIONES DE TODOS OS TIPOS
		int numeroDeNaves = carga.size() + comercial.size() + cazas.size();
		
		// DECLARAMOS EL NOMBRE BASICO DEL ENCABEZADO
		String tituloEncabezado = " Aeronave ";
		
		// EL MAS UNO ES EL ESPACIO FANTASTMA DONDE PONDREMOS
		// EL TITULO DE LAS PROPIEDADES
		String[][] aeronaves = new String[19][numeroDeNaves + 2];
				
		// DECLARAMOS EL TAMAÑO DE LA COLUMNA CON UN INT
		// QUE DEPSUES LO USAREMOS PARA RESTAR EL CONTENIDO
		// DE CADA FILA Y AGREGARLO CORRECTAMENTE
		int widthColumna = 30;
		
		// CREAMOS ESE ESPACIO FANTASMA Y
		// LO AGREGAMOS AL ARRAY DEL NOMBRE DE AERONAVES
		for (int espacios = 0; espacios < widthColumna; espacios++) {
			columnaFantasma = columnaFantasma + " ";
		}
		
		// VACIAMOS LA ARRAY
		for(int x = 0; x < numeroDeNaves + 1; x++) {
			for(int y = 0; y < 18; y++) {
				aeronaves[y][x] = columnaFantasma;
			}
		}
		
		aeronaves[0][0] = columnaFantasma;
		
		// AGREGAMOS LOS ENCABEZADOS CON LOS TITULOS
		// DE CADA UNO DE LOS AVIONES
		for (int encabezadoColumna = 1; encabezadoColumna < numeroDeNaves + 1; encabezadoColumna++) {
			String tituloResidual = tituloEncabezado + encabezadoColumna;
			
			int numeroDeEspaciosAgregar = widthColumna - tituloResidual.length();
			
			String espacioParaAgregar = "";
			
			for(int espacio = 0; espacio < numeroDeEspaciosAgregar; espacio++) {
				espacioParaAgregar = espacioParaAgregar + " ";
			}
			
			aeronaves[0][encabezadoColumna] = tituloResidual + espacioParaAgregar;
		}
		
		// PONEMOS LA INFORMACION DENTRO DE CADA FILERA
		for (int x = 0; x < carga.size(); x++) {
			int aux = x + 1;
			aeronaves[2][aux] = returnValorDeColumnaExt((" " + carga.get(x).getMarca() + " "), widthColumna);
			aeronaves[3][aux] = returnValorDeColumnaExt((" " + carga.get(x).getMatricula() + " "), widthColumna);
			aeronaves[4][aux] = returnValorDeColumnaExt((" " + carga.get(x).getModelo() + " "), widthColumna);
			aeronaves[5][aux] = returnValorDeColumnaExt((" " + carga.get(x).getCoordenadas().devolverCoordenadas() + " "), widthColumna);
			aeronaves[6][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).getTripulantes()) + " "), widthColumna);
			aeronaves[7][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).getRumbo()) + " "), widthColumna);
			aeronaves[8][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).getVelocidad_actual()) + " "), widthColumna);
			aeronaves[9][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).getTipo_de_motor()) + " "), widthColumna);
			aeronaves[10][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).isEstado_de_motores()) + " "), widthColumna);
			aeronaves[11][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).isEstado_del_tren_de_aterrizaje_desplegado()) + " "), widthColumna);
			aeronaves[12][aux] = returnValorDeColumnaExt((""), widthColumna);
			aeronaves[13][aux] = returnValorDeColumnaExt((""), widthColumna);
			aeronaves[14][aux] = returnValorDeColumnaExt((""), widthColumna);
			aeronaves[15][aux] = returnValorDeColumnaExt((""), widthColumna);
			aeronaves[16][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).getTipo_de_espacio_de_carga()) + " "), widthColumna);
			aeronaves[17][aux] = returnValorDeColumnaExt((" " + String.valueOf(carga.get(x).getTipo_de_fuselaje()) + " "), widthColumna);
		}
		
		// PONEMOS LA INFORMACION DE LOS AVIONES COMERCIALES
		for (int x = 0; x < comercial.size(); x++) {
			int aux = x + 1 + carga.size();
			aeronaves[2][aux] = returnValorDeColumnaExt((" " + comercial.get(x).getMarca() + " "), widthColumna);
			aeronaves[3][aux] = returnValorDeColumnaExt((" " + comercial.get(x).getMatricula() + " "), widthColumna);
			aeronaves[4][aux] = returnValorDeColumnaExt((" " + comercial.get(x).getModelo() + " "), widthColumna);
			aeronaves[5][aux] = returnValorDeColumnaExt((" " + comercial.get(x).getCoordenadas().devolverCoordenadas() + " "), widthColumna);
			aeronaves[6][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).getTripulantes()) + " "), widthColumna);
			aeronaves[7][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).getRumbo()) + " "), widthColumna);
			aeronaves[8][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).getVelocidad_actual()) + " "), widthColumna);
			aeronaves[9][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).getTipo_de_motor()) + " "), widthColumna);
			aeronaves[10][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).isEstado_de_motores()) + " "), widthColumna);
			aeronaves[11][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).isEstado_del_tren_de_aterrizaje_desplegado()) + " "), widthColumna);
			aeronaves[12][aux] = returnValorDeColumnaExt((""), widthColumna);
			aeronaves[13][aux] = returnValorDeColumnaExt((""), widthColumna);
			aeronaves[14][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).getCapacidad_pasajeros()) + " "), widthColumna);
			aeronaves[15][aux] = returnValorDeColumnaExt((" " + String.valueOf(comercial.get(x).getPlantas_del_avion()) + " "), widthColumna);
			aeronaves[16][aux] = returnValorDeColumnaExt((""), widthColumna);
			aeronaves[17][aux] = returnValorDeColumnaExt((""), widthColumna);
		}
		
		// PONEMOS LA INFORMACION DE LOS AVIONES CAZAS
		for (int x = 0; x < cazas.size(); x++) {
			int aux = x + 1 + carga.size() + comercial.size();
			if (cazas.get(x).aliado == false) {
				aeronaves[2][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getMarca() + " "), widthColumna);
				aeronaves[3][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getMatricula() + " "), widthColumna);
				aeronaves[4][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getModelo() + " "), widthColumna);
				aeronaves[5][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getCoordenadas().devolverCoordenadas() + " "), widthColumna);
				aeronaves[6][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getTripulantes()) + " "), widthColumna);
				aeronaves[7][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getRumbo()) + " "), widthColumna);
				aeronaves[8][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getVelocidad_actual()) + " "), widthColumna);
				aeronaves[9][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getTipo_de_motor()) + " "), widthColumna);
				aeronaves[10][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).isEstado_de_motores()) + " "), widthColumna);
				aeronaves[11][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).isEstado_del_tren_de_aterrizaje_desplegado()) + " "), widthColumna);
				aeronaves[12][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).isAliado()) + " "), widthColumna);
				aeronaves[13][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[14][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[15][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[16][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[17][aux] = returnValorDeColumnaExt((""), widthColumna);
			} else if (cazas.get(x).isEncriptado()) {
				aeronaves[2][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[3][aux] = returnValorDeColumnaExt((" ENCRIPTADO"), widthColumna);
				aeronaves[4][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[5][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[6][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[7][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[8][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[9][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[10][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[11][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[12][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[13][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[14][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[15][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[16][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[17][aux] = returnValorDeColumnaExt((""), widthColumna);
			} else {
				aeronaves[2][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getMarca() + " "), widthColumna);
				aeronaves[3][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getMatricula() + " "), widthColumna);
				aeronaves[4][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getModelo() + " "), widthColumna);
				aeronaves[5][aux] = returnValorDeColumnaExt((" " + cazas.get(x).getCoordenadas().devolverCoordenadas() + " "), widthColumna);
				aeronaves[6][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getTripulantes()) + " "), widthColumna);
				aeronaves[7][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getRumbo()) + " "), widthColumna);
				aeronaves[8][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getVelocidad_actual()) + " "), widthColumna);
				aeronaves[9][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).getTipo_de_motor()) + " "), widthColumna);
				aeronaves[10][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).isEstado_de_motores()) + " "), widthColumna);
				aeronaves[11][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).isEstado_del_tren_de_aterrizaje_desplegado()) + " "), widthColumna);
				aeronaves[12][aux] = returnValorDeColumnaExt((" " + String.valueOf(cazas.get(x).isAliado()) + " "), widthColumna);
				aeronaves[13][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[14][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[15][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[16][aux] = returnValorDeColumnaExt((""), widthColumna);
				aeronaves[17][aux] = returnValorDeColumnaExt((""), widthColumna);
			}
		}
		
		// PINTAMOS LA LINEA QUE SEPARA EL ENCABEZADO
		// DEL CONTENIDO Y LO RELLENAMOS
		String lineaSeparador = "";
		
		for(int linea = 0; linea < widthColumna; linea++) {
			if (lineaSeparador.length() > 0) {
				lineaSeparador = lineaSeparador + "-";
			} else {
				lineaSeparador = lineaSeparador + "+";
			}
		}
		for(int x = 0; x < numeroDeNaves + 1; x++) {
			aeronaves[1][x] = lineaSeparador;
			aeronaves[18][x] = lineaSeparador;
		}
		
		// AGREGAMOS DE FORMA HARDCODE LOS VALORES
		aeronaves[2][0] = returnValorDeColumna("| Marca ", widthColumna);
		aeronaves[3][0] = returnValorDeColumna("| Matricula ", widthColumna);
		aeronaves[4][0] = returnValorDeColumna("| Modelo ", widthColumna);
		aeronaves[5][0] = returnValorDeColumna("| Coordenadas ", widthColumna);
		aeronaves[6][0] = returnValorDeColumna("| Tripulantes ", widthColumna);
		aeronaves[7][0] = returnValorDeColumna("| Rumbo ", widthColumna);
		aeronaves[8][0] = returnValorDeColumna("| Velocidad actual ", widthColumna);
		aeronaves[9][0] = returnValorDeColumna("| Tipo de motor ", widthColumna);
		aeronaves[10][0] = returnValorDeColumna("| Estado motor ", widthColumna);
		aeronaves[11][0] = returnValorDeColumna("| Estado tren ", widthColumna);
		aeronaves[12][0] = returnValorDeColumna("| Aliado ", widthColumna);
		aeronaves[13][0] = returnValorDeColumna("| Encriptado ", widthColumna);
		aeronaves[14][0] = returnValorDeColumna("| Capacidad pasajeros ", widthColumna);
		aeronaves[15][0] = returnValorDeColumna("| Plantas del avion ", widthColumna);
		aeronaves[16][0] = returnValorDeColumna("| Tipo de espacio carga ", widthColumna);
		aeronaves[17][0] = returnValorDeColumna("| Tipo de fuselaje ", widthColumna);
		
		// PONEMOS EL BORDE DE LA DERECHA DE LA TABLA
		for (int x = 0; x < 19; x++) {
			if (x > 0 && x <= 1) {
				aeronaves[x][numeroDeNaves + 1] = "+";
			} else if (x == 18) {
				aeronaves[x][numeroDeNaves + 1] = "+";
			} else if (x > 1 && x < 18) {
				aeronaves[x][numeroDeNaves + 1] = "|";
			} else if (x == 0) {
				aeronaves[x][numeroDeNaves + 1] = "";
			}
		}
		
		for(int x = 0; x < aeronaves.length; x++) {
			for(int y = 0; y < aeronaves[x].length; y++) {
				System.out.print(aeronaves[x][y]);
			}
			System.out.println();
		}
	}
	
	public String returnValorDeColumnaExt(String valorParaTratar, int grosorColumna) {
		String espacioParaAgregar = "", valorParaDevolver;
		int contadorDeEspacios = grosorColumna - valorParaTratar.length();
		
		for(int espacio = 0; espacio < contadorDeEspacios; espacio++) {
			espacioParaAgregar = espacioParaAgregar + " ";
		}
		
		valorParaDevolver = valorParaTratar + espacioParaAgregar;
		
		return valorParaDevolver;
	}
	
	public String returnValorDeColumna(String valorParaTratar, int grosorColumna) {
		String espacioParaAgregar = "", valorParaDevolver;
		int contadorDeEspacios = grosorColumna - valorParaTratar.length();
		
		for(int espacio = 0; espacio < contadorDeEspacios; espacio++) {
			espacioParaAgregar = espacioParaAgregar + "-";
		}
		
		valorParaDevolver = valorParaTratar + espacioParaAgregar;
		
		return valorParaDevolver;
	}
	

}
