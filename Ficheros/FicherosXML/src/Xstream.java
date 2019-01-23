import java.io.*;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

import utilidades.Leer;

/**
 * 
 */

/**
 * @author Javier Monforte Taboada
 * 6 dic. 2018
 */
public class Xstream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File dep = new File("./departamentos.dat");
		
		ListaDepartamentos departamentos = new ListaDepartamentos(leerObjetos(dep));
		
		ArrayList<Departamentos> prueba = departamentos.getDep();
		for (Departamentos departamentos2 : prueba) {
			Leer.mostrarEnPantalla(departamentos.toString());
		}
		XStream xstream = new XStream();
		xstream.alias("departamentos",ListaDepartamentos.class);
		xstream.alias("departamento",Departamentos.class);

		xstream.aliasField("numero", Departamentos.class, "numero");
		xstream.aliasField("nombre", Departamentos.class, "nombre");
		xstream.aliasField("localidad", Departamentos.class, "localidad");
		

		Leer.mostrarEnPantalla("Fichero creado");
		try {
			xstream.toXML(departamentos,new FileOutputStream("./Departamentos2.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}

	private static ArrayList<Departamentos> leerObjetos(File dep) {
		ArrayList <Departamentos> departamentos = new ArrayList<Departamentos>() ;

		Departamentos departamento;
		FileInputStream in;
		ObjectInputStream leer;
		try {
			in = new FileInputStream(dep);
			leer = new ObjectInputStream(in);
			
				try {
					while(true) {
						departamento =(Departamentos)leer.readObject();
						if (departamento != null) {
							departamentos.add(departamento);

						}
					}
				} catch (EOFException e) {
				
				}
				in.close();
				leer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departamentos;
	}
}
