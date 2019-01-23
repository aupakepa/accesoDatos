/**
 * 
 */
import java.io.*;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author Javier Monforte Taboada
 * 10 dic. 2018
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException , IOException , SAXException {
		// TODO Auto-generated method stub

		
		XMLReader procesadorXML;
		Gestioncontenido gestor;
		try {
			procesadorXML = XMLReaderFactory.createXMLReader ();
			gestor = new Gestioncontenido();
			procesadorXML.setContentHandler (gestor) ;
			InputSource fileXML = new InputSource ("Departamentos.xml" );
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
