import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.thoughtworks.xstream.XStream;

import utilidades.Leer;

/**
 * 
 */

/**
 * @author Javier Monforte Taboada 11 dic. 2018
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int menu = 0;
		
		Libros libro;
		File fichero = new File("./libreria.dat");
		ArrayList<Libros> libros = new ArrayList<Libros>();
		do {
			Leer.mostrarEnPantalla("1.-introduce Libros");
			Leer.mostrarEnPantalla("2.-Generar Archivo xml");
			Leer.mostrarEnPantalla("3.-visualizar Archivo xml");
			Leer.mostrarEnPantalla("9.- Salir guardar libros");
			menu = Leer.pedirEntero("Introduzca opcion", "1|2|3|9");
			switch (menu) {
			case 1:
				añadirLibro(libros);
				break;
				
			case 2:
				leerFichero(fichero,libros);

				try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				DOMImplementation implementacion = builder.getDOMImplementation();
				Document document = implementacion.createDocument(null, "listalibros", null);
				document.setXmlVersion("1.0");


				for (Libros libro2 : libros) {
					Element raiz = document.createElement("Libro");
					document.getDocumentElement().appendChild(raiz);
					crearElemento(document, raiz, "id", Integer.toString(libro2.getIdLibro()) );
					crearElemento(document, raiz, "titulo", libro2.getTitulo() );
					crearElemento(document, raiz, "autor", libro2.getAutor() );
					crearElemento(document, raiz, "Editorial", libro2.getEditorial());
					crearElemento(document, raiz, "Ejemplares", Integer.toString(libro2.getEjemplares()));
				
				}

				String nombreFichero ="libreria2.xml";
				generarFichero(document, nombreFichero);


				} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

				
				break;
			case 3:
				leerXML(fichero, libros);
				break;
			case 9:

				break;

			default:
				break;
			}
		} while (menu != 9);

		fichero = new File("./libreria.dat");

		escribirFicheros(fichero, libros);

	}

	private static void añadirLibro(ArrayList<Libros> libros) {
		int idlibro = Leer.pedirEntero("introduce el idlibro");
		String titulo = Leer.pedirCadena("introduce el titulo");
		String autor = Leer.pedirCadena("introduce el autor");
		String editorial = Leer.pedirCadena("introduce la editorial");
		int ejemplares = Leer.pedirEntero("introduce el numero  de ejemplares");
		/*libro = new Libros(1, "La insoportable Levedad del ser", "Millan Kundera", 3, "niidea");
		libros.add(libro);*/
		Libros libro2 = new Libros(idlibro, titulo, autor, ejemplares, editorial);
		libros.add(libro2);
	}

	private static void leerFichero(File fichero,ArrayList<Libros> libros) {
		FileInputStream abrir;
		ObjectInputStream lectura;
		try {
			abrir = new FileInputStream(fichero);
			lectura = new ObjectInputStream(abrir);
			while (true) {
				Libros libro = ((Libros) lectura.readObject());
				libros.add(libro);
					Leer.mostrarEnPantalla(libro.toString());
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

	private static void escribirFicheros(File fichero, ArrayList<Libros> libros) {
		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
				FileOutputStream abrir = new FileOutputStream(fichero, true);
				ObjectOutputStream escribir = new ObjectOutputStream(abrir);
				for (Libros lib : libros) {
					escribir.writeObject(lib);
				}
				escribir.close();
			} else {
				fichero.createNewFile();
				FileOutputStream abrir = new FileOutputStream(fichero, true);
				MyObjectOutputStream escribir = new MyObjectOutputStream(abrir);
				for (Libros lib : libros) {
					escribir.writeObject(lib);
				}
				escribir.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void generaXml (File fichero, ArrayList<Libros> libros) {
	

	XStream xstream = new XStream();
	xstream.alias("ListaLibros",ArrayList.class);
	xstream.alias("Libros",Libros.class);

	xstream.aliasField("numero", Libros.class, "idlibro");
	xstream.aliasField("titulo", Libros.class, "titulo");
	xstream.aliasField("autor", Libros.class, "autor");
	xstream.aliasField("ejemplares", Libros.class, "ejemplares");
	xstream.aliasField("editorial", Libros.class, "editorial");


	Leer.mostrarEnPantalla("Fichero creado");
	try {
		xstream.toXML(libros,new FileOutputStream("./libreria.xml"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	
	private static void leerXML (File fichero, ArrayList<Libros> libros) {

	XMLReader procesadorXML;
	Gestioncontenido gestor;
	try {
		procesadorXML = XMLReaderFactory.createXMLReader ();
		gestor = new Gestioncontenido();
		procesadorXML.setContentHandler (gestor) ;
		InputSource fileXML = new InputSource ("libreria.xml" );
		procesadorXML.parse (fileXML );
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//fin PruebaSaxl

	}






private static void generarFichero(Document document, String nombreFichero) {
Source sourde = new DOMSource(document);
Result resul = new StreamResult(new java.io.File(nombreFichero));
try {
	Transformer transformer = TransformerFactory.newInstance().newTransformer();
	transformer.transform(sourde, resul);
} catch (TransformerConfigurationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (TransformerFactoryConfigurationError e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (TransformerException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

private static void crearElemento(Document document, Element raiz, String datoEmpleado, String valor) {
Element elem = document.createElement(datoEmpleado);
Text text = document.createTextNode(valor);
raiz.appendChild(elem);
elem.appendChild(text);
}


}
class Gestioncontenido extends DefaultHandler{
public Gestioncontenido () {
super();
}
public void startDocument () {
System.out.println("Comienzo del Documento XML" );
}
public void endDocument () {
System.out.println ("Final del Documento XML" );
}
public void startElement (String uri , String nombre , String nombreC , Attributes atts ) {
System.out.printf ("\t Principio Elemento: %s %n" , nombre );
}
public void endElement (String uri , String nombre , String nombreC) {
System.out.printf ("\tFin Elemento: %s %n" , nombre );
}
public void characters (char [] ch , int inicio , int longitud) throws SAXException {
String car = new String (ch , inicio , longitud);
car = car.replaceAll ("[\t\n]" , "" ); //quitar Saltos de línea
System.out.printf ("\t Caracteres: %s %n" , car) ;
}//fin Ge5tioncontenido

}
