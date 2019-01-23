import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 * 
 */

/**
 * @author Javier Monforte Taboada 22 ene. 2019
 */
public class Coleccion {
	static String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
	static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/colecciontema5"; // URI colección
	static String usuario = "admin"; // Usuario
	static String contraseña = "admin"; // Clave
	private Collection col;
	public Coleccion() {
		super();
		
		// establecer la conexion con a base de datos

		try {
			Class cl = Class.forName(driver);// Cargar del driver
			Database database = (Database) cl.newInstance(); // Instancia de la BD
			DatabaseManager.registerDatabase(database); // Registro del driver
			this.col = DatabaseManager.getCollection(URI, usuario, contraseña);
			// la coleccion no existe en la base de datos

		} catch (ClassNotFoundException e) {
			// en una clase no deberia imprimir algo para que el codigo no dependa de la interfaz
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getDriver() {
		return driver;
	}
	public static void setDriver(String driver) {
		Coleccion.driver = driver;
	}
	public static String getURI() {
		return URI;
	}
	public static void setURI(String uRI) {
		URI = uRI;
	}
	public static String getUsuario() {
		return usuario;
	}
	public static void setUsuario(String usuario) {
		Coleccion.usuario = usuario;
	}
	public static String getContraseña() {
		return contraseña;
	}
	public static void setContraseña(String contraseña) {
		Coleccion.contraseña = contraseña;
	}
	public Collection getCol() {
		return col;
	}
	public void setCol(Collection col) {
		this.col = col;
	}
	public ResourceSet realizarConsulta(String query) {// REALIZA CUALQUIER CONSULTA
		ResourceSet result = null;
		try {
			if (this.col == null) {
				System.out.println("La coleccion no existe.");

			}
			// la coleccion existe en la base de datos
			else {
				// solicitud del servicio
				XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
				// consulta Xpath/Xquery
				result = servicio.query(query);
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
		}
		return result;
	}
	public  void cerrarColeccion() {
		try {
			col.close();
		} catch (XMLDBException e) {
			System.out.println("Error XMLDBException, col.close. : "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void ImprimirConsulta(String query) {

		ResourceSet result = this.realizarConsulta(query);
		ResourceIterator i;
		try {
			i = result.getIterator();
			if (!i.hasMoreResources()) {
				System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÃ MAL ESCRITA");
			} else {
				// extraer el siguiente recurso del iterador
				while (i.hasMoreResources()) {
					Resource r = i.nextResource();
					// visualizar el contenido del recurso extraido
					System.out.println("--------------------------------------------");
					System.out.println((String) r.getContent());
				}
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	
