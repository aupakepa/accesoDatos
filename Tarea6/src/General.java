
import java.util.InputMismatchException;
import java.util.Scanner;

import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class General {
	
	Scanner teclado = new Scanner(System.in);
	
	String driver ="org.exist.xmldb.DatabaseImpl";//Este es el driver para eXist-db
	String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/colecciontema5";//Uri de la colecci�n
	String usuName = "admin";//Nombre del usuario
	String password = "admin";//Contrase�a del usuario
	Collection col = null;
	
	public boolean conectar() {
		
		try {
			Class cl = Class.forName(driver);//Cargo el driver.
			Database database = (Database)cl.newInstance();//Instancia de la BD
			DatabaseManager.registerDatabase(database);
			col = DatabaseManager.getCollection(URI, usuName, password);
		}catch(ClassNotFoundException e) {
			System.out.println("No se encuentra la clase del driver."+e.getMessage());return false;
		}catch(InstantiationException e) {
			System.out.println("Error instanciando el driver.");return false;
		}catch(NullPointerException e){
			System.out.println("Error al instanciar la clase del driver."+e.getMessage());return false;
		}catch(IllegalAccessException e) {
			System.out.println("Se ha producido una excepci�n de acceso ilegal.");return false;
		}catch(XMLDBException e) {
			System.out.println("Error XMLDB: "+e.getMessage());return false;
		}
		
		if(col == null) {
			System.out.println("La colecci�n no existe.");
			return false;
		}else {
			System.out.println("Conectado a la base de datos.");
			return true;
		}		
	}
	
	public void desconectar() {
		try {
		col.close();//Cierro la conexi�n con la BD.
		System.out.println("Desconexi�n BD");
	}catch(NullPointerException n) {
		System.out.println("Error en el cierre de la colecci�n.");
	}catch(XMLDBException e) {
		System.out.println("Error XMLDBException, col.close. : "+e.getMessage());
	}
	
	}
	
	public void listar() {
		try {
		XPathQueryService servicio;//Solicito el servicio.
		servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");	
		String query = "for $em in /departamentos return $em";//Creo la consulta, para hacer el listado de los departamentos
		ResourceSet result = servicio.query(query);
		ResourceIterator i;//Creo un iterador, para recorrer los datos devueltos
		i = result.getIterator();		
		if(!i.hasMoreResources()) {
			 System.out.println(" LA CONSULTA NO DEVUELVE NADA O EST� MAL ESCRITA");
		}else {
			while(i.hasMoreResources()) {
				 Resource r = i.nextResource();				 
				 System.out.println("--------------------------------------------");
                 System.out.println((String) r.getContent()); 
                 System.out.println("--------------------------------------------");
			}
		}
	}catch(NullPointerException n) {
		System.out.println("Error en getService. No se puede crear el servicio.");
	}catch(XMLDBException e) {
		System.out.println("Error XMLDBException, en get service."+e.getMessage());
	}
	}
	
	public void insertar() {
		
		boolean correcto;
		do {			
		correcto = true;
		
		System.out.println("INSERTAR DEPARTAMENTO:");
		System.out.println("-----------------------");
		System.out.println("Para salir al men� principal, escriba 0 al introducir el n� de departamento");
		System.out.println("Introduzca el n�mero de departamento: ");
		try {
		int dep = teclado.nextInt();
		if(dep==0) {
			break;
		}else {
			
			comprobar(dep);
			if(comprobar(dep)) {
				System.out.println("Lo siento, pero ese departamento, ya existe.");
				break;
			}else {
				System.out.println("Muy bien, ese departamento, no existe, as� que seguiremos cogiendo datos.");
			}	
			
		System.out.println("Escriba el nombre del departamento: ");
		String nom = teclado.next();		
		System.out.println("Escriba la localidad: ");
		String loc = teclado.next();
		
		XPathQueryService servicio;
		servicio = (XPathQueryService)col.getService("XPathQueryService", "1.0");
		String query = "update insert"
				+ "<DEP_ROW>"
				+ "<DEPT_NO>"+dep+"</DEPT_NO>"
				+ "<DNOMBRE>"+nom+"</DNOMBRE>"
				+ "<LOC>"+loc+"</LOC>"
				+ "</DEP_ROW> into /departamentos";
		ResourceSet result = servicio.query(query);
		System.out.println("Muy bien. Se ha introducido el departamento.");
		
		}
		}catch(XMLDBException e) {
			System.out.println("XMLDBException, en getService: "+e.getMessage());
		
	}catch(InputMismatchException mm) {
		System.out.println("Introduzca valores correctos, por favor.");
		teclado.next();
		correcto = false;
	}
	}while(correcto == false);
		
	}
	
	public boolean comprobar(int dep) {//M�todo para comprobar que el departamento escrito por el usuario, no exista
		
		try {
			XPathQueryService servicio;//Solicito el servicio.
			servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");	
			String query = "//DEP_ROW/DEPT_NO/text()";//Creo la consulta, para hacer el listado de los departamentos
			ResourceSet result = servicio.query(query);		
			ResourceIterator i = result.getIterator();
			if(i.hasMoreResources()) {
			while(i.hasMoreResources()) {		
			Resource r = i.nextResource();
			String respuesta =(String) r.getContent();
			String compara = String.valueOf(dep);
			if(respuesta.equals(compara)) {
				
				return true;
			}
			}
			}
			}catch(XMLDBException e) {
				System.out.println("Error XMLDBException, en get service."+e.getMessage());
			}
		
		return false;
	}
	
	public void borrar() {
		boolean correcto;
	
		do {
			correcto=true;
		
		
		System.out.println("BORRAR DEPARTAMENTO:");
		System.out.println("-----------------------");
		System.out.println("Para salir al men� principal, escriba 0 al introducir el n� de departamento");
		System.out.println("Introduzca el n�mero de departamento: ");
		try {
		int dep = teclado.nextInt();
		if(dep==0) {
			break;
		}else {
			comprobar(dep);
			if(!comprobar(dep)) {
				System.out.println("Lo siento, no existe ese departamento.");
				break;
			}else {
				try {
				XPathQueryService servicio;
				servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");	
				String query = "update delete //DEP_ROW[DEPT_NO="+dep+"]";//Creo la consulta, para eliminar el departamento.
				ResourceSet result = servicio.query(query); 
				System.out.println("Muy bien, se ha eliminado el departamento.");
			}catch(XMLDBException e) {
				System.out.println("Error XMLDBException, en get service."+e.getMessage());
			}
			
			}}
				
		}catch(InputMismatchException mm) {
			System.out.println("Introduzca valores correctos, por favor.");
			teclado.next();
			correcto=false;
		}
		
	}while(correcto==false);
	
}
	
	public void modificar() {
		boolean correcto;	
		do {
			correcto=true;		
		
		System.out.println("MODIFICAR DEPARTAMENTO:");
		System.out.println("-----------------------");
		System.out.println("Para salir al men� principal, escriba 0 al introducir el n� de departamento");
		System.out.println("Introduzca el n�mero de departamento: ");
		try {
		int dep = teclado.nextInt();
		if(dep==0) {
			break;
		}else {
			comprobar(dep);
			if(!comprobar(dep)) {
				System.out.println("Lo siento, pero ese departamento, no existe.");
			}else {
				System.out.println("Escriba el nombre del departamento: ");
				String nom = teclado.next();		
				System.out.println("Escriba la localidad: ");
				String loc = teclado.next();
				
				try {
				XPathQueryService servicio;
				servicio = (XPathQueryService)col.getService("XPathQueryService", "1.0");
				String query = "update value //DEP_ROW[DEPT_NO="+dep+"]/DNOMBRE	with '"+nom+"'";
				String query2 = "update value //DEP_ROW[DEPT_NO="+dep+"]/LOC with '"+loc+"'";
				ResourceSet result = servicio.query(query);
				ResourceSet result2 = servicio.query(query2);
				System.out.println("Se ha modificado el departamento.");
				
				
				}catch(XMLDBException e) {
					System.out.println("Error XMLDBException, en get service."+e.getMessage());
				}
			}
		}
		
		
		}catch(InputMismatchException mm) {
			System.out.println("Introduzca valores correctos, por favor.");
			teclado.next();
			correcto=false;
		}
		
	}while(correcto==false);
	}
}

