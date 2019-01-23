import java.io.*;
import javax.xml.parsers.*;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import utilidades.Leer;
/**
 * 
 */

/**
 * @author Javier Monforte Taboada
 * 6 dic. 2018
 */
public class DOMLectura {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("./Departamentos.xml"));
			document.getDocumentElement().normalize();
			Leer.mostrarEnPantalla("Elemento raiz: " + document.getDocumentElement().getNodeName());
			NodeList departamentos = document.getElementsByTagName("departamento");
			Leer.mostrarEnPantalla("Numero de elementos a recorrer : "+departamentos.getLength());
			
			for (int i = 0; i < departamentos.getLength(); i++) {
				Node dep = departamentos.item(i);
				if (dep.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) dep;	
				Leer.mostrarEnPantalla(elemento.getNodeName());
				Leer.mostrarEnPantalla("Numero = "+ getNodo("numero", elemento));
				Leer.mostrarEnPantalla("Nombre = "+ getNodo("nombre", elemento));
				Leer.mostrarEnPantalla("Nombre = "+ getNodo("localidad", elemento));

			}
					
			}		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getNodo(String etiqueta, Element elemento) {
		NodeList nodos = elemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node nodo = (Node) nodos.item(0);
		return nodo.getNodeValue();
	}

}
