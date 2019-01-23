import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import utilidades.Leer;

/**
 * @author Javier Monforte Taboada
 * 6 dic. 2018
 */
public class DOM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File dep = new File("./departamentos.dat");
		ArrayList <Departamentos> departamentos = new ArrayList<Departamentos>() ;
		Departamentos departamento;
		FileInputStream in;
		ObjectInputStream leer;
		
		leerObjetos(dep, departamentos);
		
		
		
	try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementacion = builder.getDOMImplementation();
		Document document = implementacion.createDocument(null, "Departamentos", null);
		document.setXmlVersion("1.0");
		

		for (Departamentos departamentos2 : departamentos) {
			Element raiz = document.createElement("departamento");
			document.getDocumentElement().appendChild(raiz);
			crearElemento(document, raiz, "numero", Integer.toString(departamentos2.getNumero()) );
			crearElemento(document, raiz, "nombre", departamentos2.getNombre() );
			crearElemento(document, raiz, "localidad", departamentos2.getLocalizable() );
		}
		
		String nombreFichero ="Departamentos.xml";
		generarFichero(document, nombreFichero);
		
		
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	private static void leerObjetos(File dep, ArrayList<Departamentos> departamentos) {
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
