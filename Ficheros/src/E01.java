import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import utilidades.Leer;

/**EJERCICIO 1
Escribe un programa Java que inicialice cuatro objetos de tipo File: dos que hagan referencia a ficheros, que uno exista y otro no,
 y los otros dos a directorios, que uno exista y el otro no.
 El programa debe averiguar si existen dichos ficheros o directorios, 
 independientemente de que sean una cosa o la otra.
También debe averiguar para cada objeto que exista si es un fichero o directorio. 
A la hora de visualizar el nombre del fichero o directorio, prueba a usar los diferentes métodos get que ofrece la clase File 
para ver la diferencia que hay entre cada uno de ellos.
 * 
 */

/**
 * @author Javier Monforte Taboada 6 dic. 2018
 */
public class E01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero1 = new File("C:\\Users\\Usuario\\Desktop\\tienda.sql");
		if (fichero1.exists()) {
			Leer.mostrarEnPantalla("El fichero existe");
		} else {
			Leer.mostrarEnPantalla("El fichero no existe");
		}
		if (fichero1.isFile()) {
			Leer.mostrarEnPantalla("Es un fichero");
		} else {
			Leer.mostrarEnPantalla("Es un directorio");
		}

		File directorio1 = new File("C:\\Users\\Usuario\\Desktop");
		if (directorio1.exists()) {
			Leer.mostrarEnPantalla("El fichero o directorio existe");
		} else {
			Leer.mostrarEnPantalla("El fichero o directorio no existe");
		}
		if (directorio1.isDirectory()) {
			Leer.mostrarEnPantalla("Es un directorio");
		} else {
			Leer.mostrarEnPantalla("Es un directorio");
		}
		File resultadobuffer = new File("./resultado.dat");
		try {
			resultadobuffer.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			FileWriter stream = new FileWriter(resultadobuffer);
			BufferedWriter buffer = new BufferedWriter(stream);
			File[] archivos = directorio1.listFiles();
			for (File file : archivos) {
				buffer.write(file.getAbsolutePath());
				buffer.newLine();
			}
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File resultado2 = new File("./resultado2.dat");
		File resultado3 = new File("./resultado3.dat");
		FileOutputStream stream4;
		ObjectOutputStream salidaObjetos;
		try {
			FileReader stream = new FileReader(resultadobuffer);
			BufferedReader buffer = new BufferedReader(stream);
			FileOutputStream stream2 = new FileOutputStream(resultado2);
			DataOutputStream salida = new DataOutputStream(stream2);
			stream4 = new FileOutputStream(resultado3);
			salidaObjetos = new ObjectOutputStream(stream4);
			try {
				while (true) {

					salida.writeUTF(buffer.readLine());
					salidaObjetos.writeObject(buffer.readLine());

				}
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				Leer.mostrarEnPantalla("Fin del fichero");
			}
			stream.close();
			buffer.close();
			stream2.close();
			salida.close();

		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileInputStream stream3;
		DataInputStream salida;
		try {
			stream3 = new FileInputStream(resultado2);
			salida = new DataInputStream(stream3);
			while (true) {
				try {
					Leer.mostrarEnPantalla(salida.readUTF());
				} catch (EOFException e) {
					// TODO Auto-generated catch block
					Leer.mostrarEnPantalla("Fin del fichero");
					salida.close();
					stream3.close();

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		FileInputStream stream5;
		ObjectInputStream entradaObjetos;
		
		try {
			stream5 = new FileInputStream(resultado3);
			entradaObjetos = new ObjectInputStream(stream5);
			while(true) {
				try {
					Leer.mostrarEnPantalla(entradaObjetos.readObject().toString());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					Leer.mostrarEnPantalla("Fin del fichero");
				}catch (EOFException e) {
					// TODO: handle exception
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}