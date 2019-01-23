import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class GestionPersonas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Persona> personas = new ArrayList<Persona>();
		String nombre = null;
		String apellido = null;
		File f = new File("fichTextoExamen.txt");

		// personas.add(new
		// Estudiante("Beatriz","Zapater","Radiodiagnostico",2)); //añadir a col
		// XXX
		// personas.add(new Estudiante("Pilar","Sanz","Diseño Grafico",2));
		// //añadir a col XXX =
		// personas.add(new Trabajador("Fernando","Gonzalez","jefe de
		// taller",1500.f)); //añadir a
		// personas.add(new Estudiante("Juan","Gomez","Informatica",2));
		// //añadir a col
		// personas.add(new Trabajador("Pedro","Tornos","Analista",2000.f));
		// //añadir a col
		// personas.add(new Trabajador("Patricia","Lopez","Gerente",2500.f));
		// //añadir a col
		// personas.add(new Estudiante("Ana","Zapater","Radiodiagnostico",1));
		// //añadir a
		// personas.add(new Trabajador("Luis","Suarez","Administrativo",900.f));
		// personas.add(new
		// Trabajador("Elena","Suarez","Administrativo",950.f)); //añadir a col
		// }

		Fichero ficheroTexto;// CONSIGO LEER EL FICHERO PERO NO LOS DATOS
								// NUMERICOS ASI QUE LE ASIGNA 0
		if (f.exists()) {
			ficheroTexto = new Fichero("fichTextoExamen.txt", "I");

			personas = leeFichero(personas, ficheroTexto);
		}
		// anadePersonas(lista);
		// ficheroTexto = new Fichero("fichero.txt","O");//abrimos el fichero
		// para escritura
		// escribeFichero(lista, ficheroTexto);

		// personas=leerFichero("fichTextoExamen.txt");
		int opcion = -1;

		do {
			switch (opcion) {
			case 1:
				// listaPersonas(personas);
				for (Persona persona : personas) {
					Leer.mostrarEnPantalla(persona.toString());
				}
				break;
			case 2:
				// altaPersonas(personas);

				nombre = Leer.pedirCadena("introduzca Nombre");
				apellido = Leer.pedirCadena("introduzca el Apellido");
				int opc = Leer.pedirEntero("Introduzca 2 para trabajadores y 1 para estudiantes", "[1-2]");
				switch (opc) {
				case 1:
					int curso = Leer.pedirEntero("introduzca el curso");
					String estudios = Leer.pedirCadena("introduzca los estudios");
					Estudiante estudiante = new Estudiante(nombre, apellido, estudios, curso);
					personas.add(estudiante);
					break;

				case 2:
					String categoria = Leer.pedirCadena("introduzca la categoria");
					Float sueldo = Leer.pedirFloat("introduzca el sueldo");
					Trabajador trabajador = new Trabajador(nombre, apellido, categoria, sueldo);
					personas.add(trabajador);
					break;

				default:
					break;
				}
				escribirFichero(personas);
				break;
			case 3:
				// listarEstudiantes(personas); Aqui no entiendo bien si hay que ordenar por cada una de las posibilidades o por todas juntas
				Leer.mostrarEnPantalla("1 por apellido");
				Leer.mostrarEnPantalla("2 por curso");
				int opc2 = Leer.pedirEntero("introduzca por que quiere ordenar a los estudiantes");
				switch (opc2) {
				case 1:
					personas.sort(PersonaComparator);

					break;

				case 2:
					personas.sort(cursoComparator);
					break;

				default:
					break;
				}
				for (Persona persona : personas) {
					if (persona instanceof Estudiante) {
						Leer.mostrarEnPantalla(persona.toString());
					}
				}

				break;

			case 4:
				for (Persona persona : personas) {
					if (persona instanceof Trabajador) {
						Leer.mostrarEnPantalla(persona.toString());
					}
				}

				// listarTrabajadores(personas);
				break;
			}
			opcion = menu();
		} while (opcion != 0);

	}

	private static void escribirFichero(ArrayList<Persona> personas) {
		Fichero ficheroTexto;
		ficheroTexto = new Fichero("fichTextoExamen.txt", "O");// abrimos el fichero
														// para escritura
		for (Persona persona : personas) {
			ficheroTexto.escribir(persona);
		}
		ficheroTexto.cerrar("O");
	}

	public static int menu() {
		int opcion;
		Leer.mostrarEnPantalla("1.- Listar personas");
		Leer.mostrarEnPantalla("2.- Alta de personas");
		Leer.mostrarEnPantalla("3.- Listar estudiantes");
		Leer.mostrarEnPantalla("4.- Listar trabajadores");
		Leer.mostrarEnPantalla("0.- Salir");
		opcion = Leer.pedirEntero("Elija opcion", "[0-4]");

		return opcion;
	}

	public static ArrayList<Persona> leeFichero(ArrayList<Persona> personas, Fichero ficheroTexto) {
		Persona reg = ficheroTexto.leer();
		;
		while (reg != null) {
			personas.add(reg);// lo añadimos a la lista
			reg = ficheroTexto.leer();
		}
		ficheroTexto.cerrar("I");// cerrar fichero de lectura
		return personas;
	}

	/*
	 * Coleccion<Persona> col= new .... Persona XXX; XXX = new
	 * 
	 */
	public static Comparator<Persona> PersonaComparator = new Comparator<Persona>() {//no he considerado las mayusculas pero ya no tengo Tiempo

		public int compare(Persona persona1, Persona persona2) {

			// ascending order
			return persona1.getApellido().compareTo(persona2.getApellido());

			// descending order
			// return fruitName2.compareTo(fruitName1);
		}

	};
	public static Comparator<Persona> cursoComparator = new Comparator<Persona>() {

		
		public int compare(Persona persona1, Persona persona2) {

			// ascending order
			if (persona1 instanceof Estudiante && persona2 instanceof Estudiante) {
				return ((Estudiante)persona1).getCurso() - ((Estudiante)persona2).getCurso();
	
			}else{ return -10000;}

			// descending order
		}

	};
}
