import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.*;
import javax.xml.transform.*;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;


import utilidades.Leer;

/*EJERCICIO 14
A partir de los datos del fichero binario “departamentos.dat” 
creado en el ejercicio anterior, realiza un programa Java, utilizando el analizador sintáctico DOM, 
que cree un fichero XML “departamentos.xml”.*/
public class E14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero = new File("./departamentos.dat");

		Departamentos dep;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ObjectInputStream leer;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementacion = builder.getDOMImplementation();
			Document documento = implementacion.createDocument(null, "departamentos", null);
			documento.setXmlVersion("1.0");
			
			FileInputStream in = new FileInputStream(fichero);
			leer = new ObjectInputStream(in);
			try {
				while (true) {
				Element raiz = documento.createElement("departamento");
				documento.getDocumentElement().appendChild(raiz);
				dep = (Departamentos) leer.readObject();
				Element numero = crearElemento("numero", dep.getNumero() + "", raiz, documento);
				crearElemento("nombre", dep.getNombre(), numero, documento);
				crearElemento("Localidad", dep.getLocalizable(), numero, documento);

			}
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			System.err.println("fin del fichero");
		}
			Source source = new DOMSource(documento);
			Result result = new StreamResult(new java.io.File("departamentos.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			Leer.mostrarEnPantalla("supuestamente creado");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private static Element crearElemento(String datoDepart, String valor, Element raiz, Document documento) {
		Element elemento = documento.createElement(datoDepart);
		Text texto = documento.createTextNode(valor);
		raiz.appendChild(elemento);
		elemento.appendChild(texto);
		return elemento;
	}
}
