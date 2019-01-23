
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class ConsultarEmpleadosDelDepartamento10 {

	public static void main(String[] args) {

		String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
		// String URI = "xmldb:exist://localhost:8083/exist/xmlrpc/db"; //URI colección
		String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ejemplos"; // URI colección
		String usuName = "admin"; // Usuario
		String usuPwd = "admin"; // Clave
		Collection col = null;
		// establecer la conexion con a base de datos
		try {
			Class cl = Class.forName(driver); // Cargar del driver
			Database database = (Database) cl.newInstance(); // Instancia de la BD
			DatabaseManager.registerDatabase(database); // Registro del driver
			col = DatabaseManager.getCollection(URI, usuName, usuPwd);
			// la coleccion no existe en la base de datos
			if (col == null) {
				System.out.println("La coleccion no existe.");

			}
			// la coleccion existe en la base de datos
			else {
				// solicitud del servicio
				XPathQueryService servicio;
				// consulta Xpath/Xquery
				servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
				String query = "for $em in /EMPLEADOS/EMP_ROW[DEPT_NO=10] return $em";
				ResourceSet result = servicio.query(query);
				// ResourceSet result = servicio.query("for $em in
				// /EMPLEADOS/EMP_ROW[DEPT_NO=10] return $em");

				// iterador para recorrer los datos del recurso devuelto.
				ResourceIterator i;
				i = result.getIterator();
				if (!i.hasMoreResources()) {
					System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
				} else {
					// extraer el siguiente recurso del iterador
					while (i.hasMoreResources()) {
						Resource r = i.nextResource();
						// visualizar el contenido del recurso extraido
						System.out.println("--------------------------------------------");
						System.out.println((String) r.getContent());
					}
				}
				col.close();
			}

		} catch (Exception e) {
			System.out.println(" ERROR AL CONSULTAR DOCUMENTO.");
			e.printStackTrace();
		}
	}

}
