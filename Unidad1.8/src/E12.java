import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import utilidades.Leer;

/*EJERCICIO 12
Escribe un programa Java que recupere los datos del fichero binario creado en el ejercicio anterior,
 con el fin de visualizar los datos de aquellos m�dicos que ganen un sueldo superior a una cantidad que se indicar� por teclado.*/
public class E12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int menu = 0;
		String nombre;
		int anio;
		double sueldo;
		int antiguedad;
		Medicos medico;
		File fichero;
		ArrayList<Medicos> medicos = new ArrayList<Medicos>();
		do {
			Leer.mostrarEnPantalla("1.-introduce medicos");
			Leer.mostrarEnPantalla("9.- Buscar por sueldo");
			menu = Leer.pedirEntero("Introduzca opcion", "1|9");
			switch (menu) {
			case 1:
				anio = Leer.pedirEntero("introduce el a�o de nacimiento");
				nombre = Leer.pedirCadena("introduce el nombre");
				sueldo = Leer.pedirDouble("introduce sueldo");
				antiguedad = Leer.pedirEntero("introduce antiguedad");
				medico = new Medicos(nombre, anio, sueldo, antiguedad);
				medicos.add(medico);
				break;
			case 9:

				break;

			default:
				break;
			}
		} while (menu != 9);

		fichero = new File("./medicos");

		escribirFicheros(fichero, medicos);

		leerFichero(fichero);
	}

	private static void leerFichero(File fichero) {
		FileInputStream abrir;
		ObjectInputStream lectura;
		try {
			abrir = new FileInputStream(fichero);
			lectura = new ObjectInputStream(abrir);
			double minimo = Leer.pedirDouble("introzca ell salario minimo a mostrar");
			while (true) {
				Medicos medico = ((Medicos) lectura.readObject());
				if (medico.getSueldo() >= minimo) {
					Leer.mostrarEnPantalla(medico.toString());
				}
			}
			
		} catch (EOFException e) {
			System.err.print("fin del fichero");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		finally {
		}
	}

	private static void escribirFicheros(File fichero, ArrayList<Medicos> medicos) {
		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
				FileOutputStream abrir = new FileOutputStream(fichero, true);
				ObjectOutputStream escribir = new ObjectOutputStream(abrir);
				for (Medicos med : medicos) {
					escribir.writeObject(med);
				}
				escribir.close();
			} else {
				fichero.createNewFile();
				FileOutputStream abrir = new FileOutputStream(fichero, true);
				MyObjectOutputStream escribir = new MyObjectOutputStream(abrir);
				for (Medicos med : medicos) {
					escribir.writeObject(med);
				}
				escribir.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}