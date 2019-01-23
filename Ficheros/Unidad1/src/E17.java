import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/*EJERCICIO 17
Crea una plantilla XSL para dar una presentación al fichero XML
 generado por el ejercicio anterior y realiza un programa Java para transformarlo en HTML.*/
public class E17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hojaEstilo = "departamentos.xsl";
		String datosDepart = "departamentos.xml";
		File paghtml = new File("departamentos.html");
		FileOutputStream os = null;
		try {
			 os = new FileOutputStream(paghtml);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Source estilos = new StreamSource(hojaEstilo);
		Source datos = new StreamSource(datosDepart);
		Result result = new StreamResult(os);
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
			transformer.transform(datos, result);
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
