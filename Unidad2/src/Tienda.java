/*Se precisa realizar una aplicación informática para la gestión de ventas de una pequeña empresa con
información acerca de clientes, productos y ventas.
Para ello, necesitamos crea una base de datos denominada tienda en MySQL (con usuario y clave con el
mismo nombre) y en SQLite (guarda las tablas en un archivo llamado tienda.db). La base de datos MySQL
y SQLite contendrá las siguientes tablas: PRODUCTOS, CLIENTES y VENTAS. Las tablas son las siguientes:

Nota: No debemos suponer que las bases de datos estén creadas previamente, por lo tanto, se debe
adjuntar el script para la creación de las bases de datos en el proyecto.
Una vez creadas las tablas, desarrollaremos un programa Java con un menú que permita la gestión de la
información de la empresa:
 Necesitamos rellenar con datos los productos en la tabla PRODUCTO y clientes en la tabla CLIENTE
(los datos que insertemos se definen por defecto en el propio programa). El programa Java se
implementará de modo que el menú reciba por teclado un número cuyo valor válido es 1 y 2. Si el
valor es 1 se deben llenar las tablas de la base de datos de MySQL, si es 2 debes llenarlas en SQLite.
Debemos informar del número de filas que se han insertado en cada tabla en caso.
 Queremos visualizar los datos de las tablas. Elegiremos por teclado la base de datos de la que
queremos visualizar los datos (1 o 2, su significado es como en el ejemplo anterior).
 Partiendo de las tablas anteriores, para insertar ventas en la tabla VENTAS. El programa se
implementará de modo que en esta opción se reciben varios parámetros desde teclado:
- El primero indica la base de datos donde se insertará la venta (1 o 2, su significado es como en el
ejemplo anterior). El segundo parámetro indica el identificador de venta. El tercer parámetro
indica el identificador del cliente. El cuarto parámetro indica el identificador del producto. Y el
quinto parámetro indica la cantidad. La fecha de venta se puede recoger como parámetro desde
teclado u obtenerla del sistema.
Realizar las siguientes comprobaciones antes de insertar la venta en la tabla:
- El identificador de venta no debe existir en la tabla VENTAS.
- El identificador de cliente debe existir en la tabla CLIENTES.
- El identificador de producto debe existir en la tabla PRODUCTOS.
- La cantidad debe ser > que 0.
Cuando la operación de inserción de la fila en la tabla haya sido correcta, se visualizará un mensaje
indicando al usuario. Si no se ha podido realizar la inserción debemos visualizar el motivo (no existe el
cliente, no existe el producto, cantidad menor o igual a 0…). El programa podrá insertar varias ventas
en las distintas bases de datos.
 Finalmente, partiendo de las tablas anteriores se requiere buscar un producto por su identificador y
poder realizar una modificación del precio asociado. El programa recibe varios parámetros desde
teclado: la base de datos de datos donde se busca y modificarán los datos (1 o 2, su significado es
como en el ejemplo anterior), el identificador del producto y el nuevo precio. Se informará del
resultado de la operación.
Se valorará positivamente: Limpieza y comentarios del código, manejo de excepciones e información
personalizada del resultado de las operaciones.*/

//sql.date este es para soportar sql
//java.date
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import utilidades.Leer;

public class Tienda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int menu;
		Conexion conexion = null;
		int id ;
		String fecha;
		int codigoProducto;
		int codigoCliente;
		int cantidad;

		do {
			Leer.mostrarEnPantalla("\n\t1.Introducir datos desde fichero");
			Leer.mostrarEnPantalla("\t2.Introducir ventas");
			Leer.mostrarEnPantalla("\t3.Modificar precio de un producto");
			Leer.mostrarEnPantalla("\t4.Imprimir ventas");
			Leer.mostrarEnPantalla("\t5.Imprimir productos");
			Leer.mostrarEnPantalla("\t6.Imprimir clientes");
			Leer.mostrarEnPantalla("\t9.Salir");
			Leer.mostrarEnPantalla("-----------------------------");

			menu = Leer.pedirEntero("Introduzca la opcion de menu");

			switch (menu) {

			case 1:

				conexion = elegirBase();
				introducirDatos(conexion.getSentencia());//introduce datos del fichero script
				conexion.cerraConexion();
				break;

			case 2:

				conexion = elegirBase();
				id = pedirIdVenta(conexion);
				fecha = pedirFecha();
				codigoProducto = pedirProducto(conexion);// metodo para un producto valido
				codigoCliente = pedirCliente(conexion);
				cantidad = pedirCantidad();
				introducirVenta(conexion, id, fecha, codigoProducto, codigoCliente, cantidad);
				conexion.cerraConexion();
				break;

			case 3:

				conexion = elegirBase();
				cambiarPrecio(conexion);
				conexion.cerraConexion();
				break;
				
			case 4:

				conexion = elegirBase();
				imprimirVentas(conexion);
				conexion.cerraConexion();
				break;
			case 5:

				conexion = elegirBase();
				imprimirProductos(conexion);
				conexion.cerraConexion();
				break;
			case 6:

				conexion = elegirBase();
				imprimirClientes(conexion);
				conexion.cerraConexion();
				break;
			default:

				break;
			}

		} while (menu != 9);

	}

	private static void cambiarPrecio(Conexion conexion) {
		int codigoProducto;
		codigoProducto = pedirProducto(conexion);
		int precio = Leer.pedirEntero("introduzca el nuevo precio");
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PRODUCTO SET PVP=");
		sql.append(precio);
		sql.append(" where IDPRODUCTO = ");
		sql.append(codigoProducto);
		
		try {
			int numeroLineas = conexion.getSentencia().executeUpdate(sql.toString());
			Leer.mostrarEnPantalla(numeroLineas + " lineas modificada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void introducirVenta(Conexion conexion, int id, String fecha, int codigoProducto, int codigoCliente,
			int cantidad) {
		
		 String sql = "INSERT INTO VENTA VALUES (?, ?, ?, ?, ?)"; 
		  
		 try { 
		 PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
		 sentencia.setInt(1, id); 
		 sentencia.setString(2,fecha);
		 sentencia.setInt(3,codigoCliente); 
		 sentencia.setInt(4, codigoProducto); 
		 sentencia.setInt(5,cantidad);
		 sentencia.executeUpdate();
		 } 
		 catch (SQLException e) { 
		 if (e.getErrorCode() == 1062) {
		 System.out.println("Clave primaria duplicada"); 
		 }
		 System.err.println(e.getMessage()); 
		 System.err.println(e.getSQLState());
		 System.err.println(e.getErrorCode()); 
		}
	}
		 /*
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO VENTA VALUES (");
		sql.append(id);
		sql.append(",");
		sql.append(fecha);
		sql.append(",");
		sql.append(codigoCliente);
		sql.append(",");
		sql.append(codigoProducto);
		sql.append(",");
		sql.append(cantidad);
		sql.append(")");

		try {
			int numeroLineas = conexion.getSentencia().executeUpdate(sql.toString());
			Leer.mostrarEnPantalla(numeroLineas + " lineas insertadas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	private static int pedirCantidad() {
		int cantidad;
		do {
			cantidad = Leer.pedirEntero("Introduce  cantidad mayor que cero");
			if (cantidad <= 0) {
				Leer.mostrarEnPantalla("La cantidad debe ser mayor que cero");
			}
		} while (cantidad <= 0);
		return cantidad;
	}

	private static String pedirFecha() {
		Fecha fecha = null;
		do {
			fecha = new Fecha(Leer.pedirEntero("introduce el dia"), Leer.pedirEntero("introduce el mes"),
					Leer.pedirEntero("introduce el a�o"));
			if (fecha.getDia() == null) {
				Leer.mostrarEnPantalla("Por favor, vuelve a introducir la fecha, no es un valor posible");
			}

		} while (fecha.getDia() == null);

		String fecha1 = fecha.toString();
		return fecha1;
	}

	private static int pedirIdVenta(Conexion conexion) {
		boolean existe;
		int id;
		imprimirVentas(conexion);
		do {
			id = Leer.pedirEntero("introduzca el id de la venta");
			existe = comprobarVenta(conexion, id);
			if (existe == true) {
				Leer.mostrarEnPantalla("El id de la venta ya existe, Vuelva a intentarlo");
			}
		} while (existe);
		return id;
	}

	private static int pedirProducto(Conexion conexion) {
		boolean existe;
		int codigoProducto;
		imprimirProductos(conexion);
		do {
			codigoProducto = Leer.pedirEntero("introduce el id de producto");
			existe = comprobarProducto(conexion, codigoProducto);
			if (existe == false) {
				Leer.mostrarEnPantalla("El Producto no existe Introduzca un producto correcto");
			}
		} while (existe == false);
		return codigoProducto;
	}

	private static int pedirCliente(Conexion conexion) {
		boolean existe;
		int codigoCliente;
		imprimirClientes(conexion);
		do {
			codigoCliente = Leer.pedirEntero("introduce el id de cliente");
			existe = comprobarCliente(conexion, codigoCliente);
			if (existe == false) {
				Leer.mostrarEnPantalla("El cliente se�alado no existe");
			}
		} while (!existe);
		return codigoCliente;
	}

	private static void imprimirVentas(Conexion conexion) {
		try {
			ResultSet resul = conexion.getSentencia().executeQuery("Select * from venta");
			Leer.mostrarEnPantalla("-----------------------------");
			while (resul.next()) {
				Leer.mostrarEnPantalla(resul.getInt(1) + "|" + resul.getString(2));
			}
			Leer.mostrarEnPantalla("-----------------------------");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void imprimirProductos(Conexion conexion) {
		try {
			ResultSet resul = conexion.getSentencia().executeQuery("Select * from producto");
			Leer.mostrarEnPantalla("-----------------------------");

			while (resul.next()) {
				Leer.mostrarEnPantalla(resul.getInt(1) + "|" + resul.getString(2) + "|" + resul.getInt(5));
			}
			Leer.mostrarEnPantalla("-----------------------------");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void imprimirClientes(Conexion conexion) {
		try {
			ResultSet resul = conexion.getSentencia().executeQuery("Select * from cliente");
			Leer.mostrarEnPantalla("-----------------------------");
			while (resul.next()) {
				Leer.mostrarEnPantalla(resul.getInt(1) + "|" + resul.getString(2));
			}
			Leer.mostrarEnPantalla("-----------------------------");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static boolean comprobarProducto(Conexion conexion, int codigoProducto) {
		boolean existe = false;
		try {
			ResultSet resul = conexion.getSentencia().executeQuery("select * from producto");
			while (resul.next()) {
				if (resul.getInt(1) == codigoProducto) {
					existe = true;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return existe;
	}

	private static boolean comprobarVenta(Conexion conexion, int id) {
		boolean existe = false;
		try {
			ResultSet resul = conexion.getSentencia().executeQuery("select * from venta");
			while (resul.next()) {
				if (resul.getInt(1) == id) {
					existe = true;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return existe;
	}

	private static boolean comprobarCliente(Conexion conexion, int codigoCliente) {
		boolean existe = false;
		try {
			ResultSet resul = conexion.getSentencia().executeQuery("select * from cliente");
			while (resul.next()) {
				if (resul.getInt(1) == codigoCliente) {
					existe = true;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return existe;
	}

	private static Conexion elegirBase() {
		Conexion conexion;
		Leer.mostrarEnPantalla("\t1. Elegir MySql");
		Leer.mostrarEnPantalla("\t2. Elegir SqLite");
		int opc = Leer.pedirEntero("Elija la base de datos", "1|2");
		switch (opc) {
		case 1:
			conexion = new Conexion(1);
			break;
		case 2:
			conexion = new Conexion(2);

			break;
		default:
			conexion = null;
			break;
		}
		return conexion;
	}

	/* Introduce datos de un fichero con sentencias insert */
	private static void introducirDatos(Statement sentenciaMysql) {
		File fichero = new File("./script");
		BufferedReader buffreader;
		try {
			buffreader = new BufferedReader(new FileReader(fichero));
			String linea;
			int numeroLineas = 0;
			while ((linea = buffreader.readLine()) != null) {
				numeroLineas = numeroLineas + sentenciaMysql.executeUpdate(linea);
			}
			Leer.mostrarEnPantalla("sentencias  ejecutadas \n" + numeroLineas + " filas insertadas");
			buffreader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("Clave primaria duplicada ");//codigo mysql
				System.err.println("No se ha podido realizar la operacion");
				System.err.println("error code =" + e.getErrorCode());//No entiendo porque sale a mitad del menu supongo que sera por el retraso de contestar de la base
			}
			if (e.getErrorCode() == 19) {//codigo sqlite
				System.err.println("Clave primaria duplicada");//codigo sqlite
				System.err.println("No se ha podido realizar la operacion");
				System.err.println("error code =" + e.getErrorCode());
			}
			e.printStackTrace();
			// TODO Auto-generated catch block
		}
	}

}
