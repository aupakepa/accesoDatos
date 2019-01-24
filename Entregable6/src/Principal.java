import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import utilidades.Leer;

/**EJERCICIO 6 (Entregable)
 Realiza un programa Java que inserte, elimine y modifique departamentos del documento departamentos.xml.
 Utiliza las Sentencias de actualización de eXist dentro de nuestro código Java. 
 Los datos se leerán de la entrada estándar de teclado. 
 Recomendación: Hacer que la función main() llame y ejecute los siguientes métodos para cada operación a realizar:
 insertadep(), este método leerá de teclado un departamento, su nombre y su localidad, y deberá añadirlo al documento. 
Si el código de departamento existe visualizar que no se puede insertar porque ya existe.
 borradep(), este método leerá de teclado un departamento, y deberá borralo si existe, 
si no existe visualizar que no se puede borrar porque ya existe.
 modificadep(), este método leerá de teclado un departamento, 
su nombre nuevo y la localidad nueva y deberá actualizar todos los datos si existe, 
si no existe visualizar que no se puede modificar porque ya existe.
**Es recomendable tener un método (por ejemplo, listardep()) para listar los departamentos 
*y poderlos visualizar por pantalla.
 * 
 */
/**
 * @author Javier Monforte Taboada 18 ene. 2019
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int menu = -1;
		Coleccion col;
		int num;
		String query;
		// LA VERDAD NO TENGO CLARO SI DEBIA HACERLO CON CLASE O TODO EN EL PRINCIPAL
		// PERO HE DECIDIDO SEPARAR LO QUE DEPENDE DEL DOCUMENTO Y LO QUE NO
		// SIN EMBARGO LOS PARAMETROS DE LA COLECCION ESTAN EN LA CLASE (PARA NO TENER
		// QUE INTRODUCIR TANTOS PARAMETROS CADA VEZ
		// PERO NO SE QUE SERA MAS PRACTICO PARA REUTILIZAR
		// PODRIA HABER DOS COLECCIONES EN EL MISMO PROYECTO Y ASI NO TENDRIA SENTIDO
		do {
			Leer.mostrarEnPantalla("-----------------------------");

			Leer.mostrarEnPantalla("1.Insertar un departamento");
			Leer.mostrarEnPantalla("2.Modificar un departamento");
			Leer.mostrarEnPantalla("3.Borrar un departamento");
			Leer.mostrarEnPantalla("4.Realizar una Consulta Xpath o Xquery y Mostrarla por pantalla");
			Leer.mostrarEnPantalla("5.Buscar un departamento");
			Leer.mostrarEnPantalla("6.Imprimir empleados de un departamento");
			Leer.mostrarEnPantalla("7.Imprimir departamentos");
			Leer.mostrarEnPantalla("0.Salir");

			Leer.mostrarEnPantalla("-----------------------------");
			menu = Leer.pedirEntero("Introduzca una opcion de menu", "[1-7]|0");
			col = new Coleccion();

			switch (menu) {
			case 1:// insertar nuevo departamento
				insertadep(col);
				break;
			case 2:// modificar departamentos
				modificadep(col);
				break;
			case 3:// borrar departamentos
				borradep(col);
				break;
			case 4:// introducir una consulta Xpath o Xquery
				query = Leer.pedirCadena("Introduzca consulta Xquery");
				col.ImprimirConsulta(query);
				break;
			case 5:
				num = Leer.pedirEntero("Introduzca numero de departamento");
				query = "for $em in /departamentos/DEP_ROW[DEPT_NO=" + num + "] return $em";
				col.ImprimirConsulta(query);// EN LOS INTRODUCIDOS NO ES EL FORMATO ESPERADO PERO NO HE CONSEGUIDO EL
											// FORMATO DE LOS DEMAS
				break;
			case 6:
				num = Leer.pedirEntero("Introduzca numero de departamento");
				query = "for $em in /EMPLEADOS/EMP_ROW[DEPT_NO=" + num + "] return $em";
				col.ImprimirConsulta(query);
				break;
			case 7:
				query = "/departamentos";
				col.ImprimirConsulta(query);
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
			}
			col.cerrarColeccion();
		} while (menu != 0);
	}
	private static void borradep(Coleccion col) {// borrar departamentos
		int num = Leer.pedirEntero("Introduzca el numero del nuevo departamento");

		if (!departamentoExiste(col, num)) {
			Leer.mostrarEnPantalla("El departamento no existe");
		} else {
			String query = "update delete doc(\"departamentos.xml\")/departamentos/DEP_ROW[DEPT_NO=" + num + "]";
			col.realizarConsulta(query);
			Leer.mostrarEnPantalla("Departamento " + num + " borrado");
		}
	}
	private static void modificadep(Coleccion col) {// modificar departamentos
		int num = Leer.pedirEntero("Introduzca numero de departamento");
		String query;
		String nombre;
		String localidad;
		int key;

		if (!departamentoExiste(col, num)) {// VALIDAR QUE EXISTE
			Leer.mostrarEnPantalla("El departamento no existe");
		} else {

			do {
				Leer.mostrarEnPantalla("1. Modificar el nombre");
				Leer.mostrarEnPantalla("2. Modificar la localidad");
				Leer.mostrarEnPantalla("9. Salir");
				key = Leer.pedirEntero("Introduzca una opcion de menu", "1|2|9");
				switch (key) {
				case 1:
					nombre = Leer.pedirCadena("Introduzca el nuevo nombre");
					query = "update value doc(\"departamentos.xml\")/departamentos/DEP_ROW[DEPT_NO=" + num
							+ "]/DNOMBRE " + "with '" + nombre + "'";
					col.realizarConsulta(query);
					Leer.mostrarEnPantalla("El departamento se llama ahora " + nombre);
					break;
				case 2:
					localidad = Leer.pedirCadena("introduzca la nueva localidad");
					query = "update value doc(\"departamentos.xml\")/departamentos/DEP_ROW[DEPT_NO=" + num + "]/LOC "
							+ "with '" + localidad + "'";
					col.realizarConsulta(query);
					Leer.mostrarEnPantalla("El departamento se localiza en " + localidad);

					break;
				case 9:

					break;

				default:

					break;
				}
			} while (key != 9);
		}
	}

	private static void insertadep(Coleccion col) {// insertar nuevo departamento
		int num = Leer.pedirEntero("Introduzca el numero del nuevo departamento");

		if (departamentoExiste(col, num)) {
			Leer.mostrarEnPantalla("El departamento ya existe");
		} else {
			String nombre = Leer.pedirCadena("Introduzca el nombre del departamento");
			String localidad = Leer.pedirCadena("Introduzca la localidad del departamento");
			String query = "update insert" + "<DEP_ROW>" + "<DEPT_NO>" + num + "</DEPT_NO>" + "<DNOMBRE>" + nombre
					+ "</DNOMBRE>" + "<LOC>" + localidad + "</LOC>" + "</DEP_ROW> into /departamentos";//en principio lo hice con StringBuilder pero la maquetacion no era buena y lo cambie
			col.realizarConsulta(query);
			Leer.mostrarEnPantalla("Departamento " + num + " creado");
		}
	}
	private static boolean departamentoExiste(Coleccion col, int num) {
		boolean existe = false;
		String query = "for $em in /departamentos/DEP_ROW[DEPT_NO=" + num + "] return $em";
		ResourceSet result = col.realizarConsulta(query);
		try {
			if (result.getSize() == 0) {
				existe = false;
			} else {
				existe = true;
			}
		} catch (XMLDBException e) {// La verdad no se exactamente cuando da la exception y la posibilidad de que
									// siendo true devuelva false
			// TODO Auto-generated catch block
		}
		return existe;
	}
}
