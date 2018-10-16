import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/*EJERCICIO 15
5A partir del fichero XML “departamentos.xml” creado en el ejercicio anterior, 
realiza un programa Java, utilizando el analizador sintáctico DOM, 
que lea dicho fichero XML y que visualice por pantalla su estructura y contenido.*/
public class E15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document documento = builder.parse(new File("departamentos.xml"));
			documento.getDocumentElement().normalize();
			System.out.println("Elemento raiz : " + documento.getDocumentElement().getNodeName());
			NodeList departamentos = documento.getElementsByTagName("departamento");
			System.out.println("Nodos empleado a recorrer: " + departamentos.getLength());
			for (int i = 0; i < departamentos.getLength()-1; i++) {
				Node dep = departamentos.item(i);
				if (dep.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) dep;
					NodeList numeros = elemento.getElementsByTagName("numero");
					System.out.println("numero : " + getNodo("numero", elemento));
					for (int j = 0; j < numeros.getLength(); j++) {
						Node numero = numeros.item(j);
						if (numero.getNodeType() == Node.ELEMENT_NODE) {
							elemento = (Element) numero;
							System.out.println("Nombre: " + getNodo("nombre", elemento));
						}
						if (numero.getNodeType() == Node.ELEMENT_NODE) {
							elemento = (Element) numero;
							System.out.println("Localidad: " + getNodo("Localidad", elemento));
						}
					}
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
