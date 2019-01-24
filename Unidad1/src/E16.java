import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;

/*EJERCICIO 16
A partir de los datos del fichero Departamentos.dat creado anteriormente crea un fichero XML
usando la librería XStream.*/
public class E16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero = new File("./departamentos.dat");
		ListaDepartamentos lista = null;
		try {
			FileInputStream filein = new FileInputStream(fichero);
			ObjectInputStream dataIs = new ObjectInputStream(filein);
			lista = new ListaDepartamentos(null);
			try {
				while (true) {
					Departamentos departamentos = (Departamentos) dataIs.readObject();
					lista.add(departamentos);
				}
			} catch (EOFException e) {
				// TODO: handle exception
			}
			dataIs.close();
			// dataIs.close();// No entiendo porque no me deja cerrarlo y me ha pasado en
			// varios

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

		try {

			XStream xsstream = new XStream();
			xsstream.alias("departamentos", ListaDepartamentos.class);
			xsstream.alias("departamento", Departamentos.class);

			xsstream.aliasField("numerodepartamentos", Departamentos.class, "numero");
			xsstream.aliasField("nombre", Departamentos.class, "nombre");
			xsstream.aliasField("localidad", Departamentos.class, "localidad");
			
			xsstream.addImplicitCollection(ListaDepartamentos.class, "dep");

			xsstream.toXML(lista, new FileOutputStream("./departamentos2.xml"));//me daba error por la Ñ de alcañiz
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
