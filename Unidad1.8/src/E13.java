import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

import utilidades.Leer;

/*EJERCICIO 13
Realiza un programa Java que cree un fichero binario para guardar datos de departamentos.
Dale el nombre “departamentos.dat” e introduce varios departamentos. Los datos para cada
departamento son:
Número de departamento: entero.
Nombre: String.
Localidad: String.
Para su uso en posteriores ejercicios, el fichero binario “departamentos.dat” deberá contener
varios registros de departamentos ordenados por el número de departamento. Este campo será
un identificador único.
Nota: puedes fijarte en el ejemplo del fichero de la clase Persona utilizado anteriormente en los
apuntes del módulo*/
public class E13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombres[] = { "finanzas", "ventas", "compras", "marketing" };
		int numero[] = { 10, 20, 30, 45 };
		String localidad[] = { "zaragoza", "Huesca", "Teruel", "Alcañiz" };
		Departamentos dep;
		TreeSet<Departamentos> departamentos = new TreeSet<Departamentos>();

		File fichero = new File("./departamentos.dat");
		if (!fichero.exists()) {
			try {
				fichero.createNewFile();
				FileOutputStream out = new FileOutputStream(fichero);
				ObjectOutputStream escribir = new ObjectOutputStream(out);
				escribir.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				departamentos = leerFichero(fichero,departamentos);
				FileOutputStream out = new FileOutputStream(fichero);
				ObjectOutputStream escribir = new ObjectOutputStream(out);
				for (int i = 0; i < localidad.length; i++) {
					dep = new Departamentos(numero[i], nombres[i], localidad[i]);
					departamentos.add(dep);
				}
				for (Departamentos dep2 : departamentos) {
					escribir.writeObject(dep2);
	
				}

				escribir.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		imprimirFichero(fichero, departamentos);
	}

	private static TreeSet<Departamentos> leerFichero(File fichero, TreeSet<Departamentos> departamentos) {
		Departamentos dep;
		ObjectInputStream leer;
		try {
			FileInputStream in = new FileInputStream(fichero);
			leer = new ObjectInputStream(in);
			while (true) {
				dep = (Departamentos)leer.readObject();
				if (dep!=null) {
					departamentos.add(dep);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			System.err.println("fin del fichero");
			return departamentos;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departamentos;
	}
	private static void imprimirFichero(File fichero, TreeSet<Departamentos> departamentos) {
		Departamentos dep;
		ObjectInputStream leer;
		try {
			FileInputStream in = new FileInputStream(fichero);
			leer = new ObjectInputStream(in);
			while (true) {
				dep = (Departamentos)leer.readObject();
				if (dep!=null) {
					Leer.mostrarEnPantalla(dep.toString());
	
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			System.err.println("fin del fichero");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
