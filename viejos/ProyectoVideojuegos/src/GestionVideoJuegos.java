
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class GestionVideoJuegos {

	public static void main(String[] args) {

		ArrayList<VideoJuego> listaJuegos = new ArrayList<VideoJuego>();
		Fichero ficheroTexto = null;
		File f = new File("videojuegos.dat");
		if (f.exists()) {
			ficheroTexto = new Fichero("videojuegos.dat", "I");// Apertura del
																// fichero de
																// entrada.donde
																// leemos
			listaJuegos = ficheroTexto.leer();
			ficheroTexto.cerrar("I");

		} else {
			crearJuegos(listaJuegos);
		}
		escribirFichero(listaJuegos);
		// crearJuegos
		int opcion = menu();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				altaVideojuegos(listaJuegos);
				escribirFichero(listaJuegos);
				// abria que escribir
				// ...
				break;
			case 2:
				bajaVideojuegos(listaJuegos);
				escribirFichero(listaJuegos);
				// ...
				break;
			case 3:// listado de estudiantes por estudios, curso, apellidos y
					// nombre
				modificaVideojuegos(listaJuegos);
				escribirFichero(listaJuegos);//deberia hacerlo por cada uno de los casos pero creo que asi esta bien
				// ...
				break;
			case 4:// listado de trabajadores por categoria, apellidos y nombre
					// ...
				listaJuegos.sort(FabricanteComparator);
				for (VideoJuego videoJuego : listaJuegos) {
					Leer.mostrarEnPantalla(videoJuego.toString());
				}
				break;
			case 5: // listado de personas por apellidos y nombre
				// ...
				listaJuegos.sort(nombreComparator);
				for (VideoJuego videoJuego : listaJuegos) {
					Leer.mostrarEnPantalla(videoJuego.toString());
				}
				break;
			}
			opcion = menu();
		}

	}// main

	private static void escribirFichero(ArrayList<VideoJuego> listaJuegos) {
		Fichero ficheroTexto;
		ficheroTexto= new Fichero("videojuegos.dat", "O");
		ficheroTexto.escribir(listaJuegos);
		ficheroTexto.cerrar("O");
	}

	public static void crearJuegos(ArrayList<VideoJuego> listaJuegos) {
		String[] juegos = { "League of legends", "Final Fantasy XXVII", "Star Wars", "FIFA 2020" };
		String[] fabricantes = { "F1", "F1", "F2", "F3" };
		Integer[] edadesMinimas = { 12, 14, 15, 8 };
		Float[] precios = { 20.f, 30.f, 35.f, 25.f };
		String[] plataformas = { "PC", "PC", "XBox", "Play Station" };
		Integer[] unidadesVendidas = { 1000, 900, 2000, 1300 };
		String[] formasJuegos = { "En red", "Multijugador", "Multijugador", "En red" };
		int i;
		VideoJuego juego;
		for (i = 0; i < juegos.length; i++) {
			juego = new VideoJuego(juegos[i], fabricantes[i], edadesMinimas[i], precios[i], plataformas[i],
					unidadesVendidas[i], formasJuegos[i]);
			listaJuegos.add(juego);
		}
	}

	private static void altaVideojuegos(ArrayList<VideoJuego> listaJuegos) {
		// .....
		String nombre = Leer.pedirCadena("introduzca el nombre");
		String fabricante = Leer.pedirCadena("introduzca el fabricante");
		String plataforma = Leer.pedirCadena("introduzca la plataforma");
		VideoJuego juego = new VideoJuego(nombre, fabricante, plataforma);
		listaJuegos.add(juego);

	}// altaVideojuegos

	private static void bajaVideojuegos(ArrayList<VideoJuego> listaJuegos) {
		// ...
		int i = 0;
		for (VideoJuego videoJuego : listaJuegos) {
			Leer.mostrarEnPantalla(i + ".-" + videoJuego.getNombre());
			i++;
		}
		i = Leer.pedirEntero("introduzca el id del juego a borrar");// esto
																	// deberia
																	// estar
																	// entre un
																	// try cacth
		listaJuegos.remove(i);
	}// bajaVideojuegos

	private static void modificaVideojuegos(ArrayList<VideoJuego> listaJuegos) {// esto
																				// deberia
																				// estar
																				// entre
																				// un
																				// try
																				// cacth
		// ...
		int i = 0;
		for (VideoJuego videoJuego : listaJuegos) {
			Leer.mostrarEnPantalla(i + ".-" + videoJuego.getNombre());
			i++;
		}
		i = Leer.pedirEntero("introduzca el id del juego a modificar");
		Leer.mostrarEnPantalla("1. para modificar el nombre");
		Leer.mostrarEnPantalla("2. para modificar el frabricante");
		Leer.mostrarEnPantalla("3. para modificar el edad minima");
		Leer.mostrarEnPantalla("4. para modificar el precio");
		Leer.mostrarEnPantalla("5. para modificar el plataforma");
		Leer.mostrarEnPantalla("6. para modificar el unidades vendidas");
		Leer.mostrarEnPantalla("7. para modificar el forma de juego");
		int key = Leer.pedirEntero("introduzca el campo que quiere modificar");
		switch (key) {
		case 1:
			listaJuegos.get(i).setNombre(Leer.pedirCadena("introduzca el nuevo Nombre"));
			break;

		case 2:
			listaJuegos.get(i).setFabricante(Leer.pedirCadena("introduzca el nuevo Fabricante"));

			break;

		case 3:
			listaJuegos.get(i).setEdadMinima(Leer.pedirEntero("introduzca el nuevo Edad Minima"));

			break;

		case 4:
			listaJuegos.get(i).setPrecio(Leer.pedirFloat("introduzca el nuevo precio"));

			break;

		case 5:
			listaJuegos.get(i).setPlataformas(Leer.pedirCadena("introduzca el nuevo plataforma"));

			break;

		case 6:
			listaJuegos.get(i).setUnidadesVendidas(Leer.pedirEntero("introduzca el unidades vendidas"));

			break;

		case 7:
			listaJuegos.get(i).setFormaJuego(Leer.pedirCadena("introduzca el nuevo Fabricante"));

			break;

		default:
			break;
		}
	}
	// bajaVideojuegos

	public static int menu() {
		int op;
		Leer.mostrarEnPantalla("GESTION DE VIDEOJUEGOS");
		Leer.mostrarEnPantalla("-------------------");
		Leer.mostrarEnPantalla("1 - Alta de videojuegos");
		Leer.mostrarEnPantalla("2 - Baja de videojuegos");
		Leer.mostrarEnPantalla("3 - Modificación de videojuegos");
		Leer.mostrarEnPantalla("4 - Listado de videojuegos por nombre");
		Leer.mostrarEnPantalla("5 - Listado de videojuegos por fabricante");
		Leer.mostrarEnPantalla("0 - Salir");
		op = Leer.pedirEntero("Elija opcion", "[0-5]");
		return op;
	}

	public static Comparator<VideoJuego> FabricanteComparator = new Comparator<VideoJuego>() {

	    public int compare(VideoJuego juego1, VideoJuego juego2) {


	      //ascending order
	      return (juego1.getFabricante().compareTo(juego2.getFabricante()))*1000+juego1.getNombre().compareTo(juego2.getNombre());

	      //descending order
	      //return fruitName2.compareTo(fruitName1);
	    }

	};
	public static Comparator<VideoJuego> nombreComparator = new Comparator<VideoJuego>() {

	    public int compare(VideoJuego juego1, VideoJuego juego2) {


	      //ascending order
	      return juego1.getNombre().compareTo(juego2.getNombre());

	      //descending order
	      //return fruitName2.compareTo(fruitName1);
	    }

	};
}// GestionVideojuegos
