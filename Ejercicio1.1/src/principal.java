import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import utilidades.Fecha;
import utilidades.Leer;

/*EJERCICIO 2
Realiza un programa Java que muestre los ficheros de un directorio.
El nombre del directorio se pasará al programa desde la línea de comandos al ejecutarlo.
Modifica el programa anterior para que muestre los nombres de los ficheros de un directorio, 
cuya extensión coincida con la que se pase como segundo argumento.
*/
public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);
		int menu = -1;
		String ficheros[];
		do {
			System.out.println("1. introduce el nombre del directorio en la ruta C:\\Users\\usuario\\eclipse-workspace\\Ejercicio1.1\\");
			System.out.println("2. Buscar directorio en una ruta");
			System.out.println("0. salir");
			menu = teclado.nextInt();
			switch (menu) {
			case 1:
				System.out.println("introduce el directorio");
				String directorio = teclado.nextLine();
				File f1 = new File("C:\\Users\\usuario\\eclipse-workspace\\Ejercicio1.1\\".concat(directorio));
				if (f1.exists() && f1.isDirectory()) {
					ficheros = new String[f1.list().length - 1];
					System.out.println("introduce la extension de los archivos a mostrar");
					String extension = teclado.nextLine();
					for (int i = 0; i < ficheros.length; i++) {
						if (ficheros[i].endsWith(extension)) {
							System.out.println(ficheros[i]);

						}
					}
				} else {
					System.out.println("No es un directorio valido");
				}
				break;
			case 2:

				break;
			case 0:
				teclado.close();
				break;

			default:
				break;
			}

		} while (menu != 0);
	}
}
