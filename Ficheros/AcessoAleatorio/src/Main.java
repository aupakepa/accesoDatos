import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import utilidades.Leer;

/**
 * 
 */

/**
 * @author Javier Monforte Taboada 9 dic. 2018
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero = new File("./aleatorio.dat");
		if (!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String nomNuevo, nombre; 
		String cadena = ""; 
		RandomAccessFile fiche;
		try {
			fiche = new RandomAccessFile("directo.txt", "rw");
			System.out.print("\n Introduce un nombre (\"Ninguno\" para salir): "); 
			nomNuevo = Leer.pedirCadena(cadena); 
			while (!nomNuevo.equals("Ninguno")) { 
				fiche.seek(fiche.length()); 
				fiche.writeBytes(nomNuevo); 
				fiche.write('\n'); //Para separar los nombres 
				System.out.print("Introduce otro nombre (\"Ninguno\" para salir):"); 
				nomNuevo = Leer.pedirCadena(cadena);
				} 
			fiche.seek(0); 
		nombre=fiche.readLine(); 
			while(nombre != null) { 
				System.out.println("Nombre leído: " + nombre); 
				nombre = fiche.readLine(); 
				} fiche.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			fiche = new RandomAccessFile("directo.txt", "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		}
	
}