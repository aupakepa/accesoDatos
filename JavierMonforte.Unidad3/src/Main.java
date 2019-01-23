/*Se pide desarrollar una aplicación Java que utilice Hibernate y MySQL 
 * para la gestión de los datos de dicha empresa. 
 * Necesitamos poder ejecutar las siguientes operaciones de gestión de objetos mapeados a partir de la base de datos tienda:
- Insertar un cliente.
- Insertar un producto.
- Insertar una venta. 
Se debe comprobar que tanto el cliente como el producto relacionados con la venta existen en la base de datos.
- Leer una venta, y además, su cliente y su producto correspondientes.
En cada caso, añade las comprobaciones necesarias en caso de errores, 
visualiza en pantalla los mensajes apropiados para las distintas situaciones y 
también los atributos de los objetos manipulados en Java.
Por otro lado, necesitamos ejecutar las siguientes sentencias de consulta sobre la base de datos tienda:
- Obtener un listado de los productos que tienen un precio inferior a 100.
- Obtener un listado de los clientes que viven en Zaragoza.
- Obtener un listado de los clientes cuyo nombre empieza por la letra R.
En cada apartado, utiliza parámetros nombrados o por posición en la consulta.
Finalmente, se pide poder realizar dos manipulaciones de datos sobre dicha base de datos:
- Modificar el precio de todos los productos de forma que incluyan un IVA del 21%.
- Modificar el precio asociado de un producto dado.
Se valorará positivamente: Limpieza y comentarios del código, manejo de excepciones 
e información personalizada del resultado de las operaciones.

@autor Javier Monforte Taboada
@date 2018-11-30
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.query.Query;
import org.slf4j.Logger;

import primero.Cliente;
import primero.HibernateUtil;
import primero.Producto;
import primero.Venta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilidades.Leer;

public class Main {

	public static void main (String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//No he sido capaz de desactivar los mensajes de hibernate
		// He puesto excepciones aunque creo que esta todo validado y no harian falta en la mayoria;

		int menu = -1;
		do {
			Leer.mostrarEnPantalla("-----------------------------");

			Leer.mostrarEnPantalla("1.Insertar cliente");
			Leer.mostrarEnPantalla("2.Insertar producto");
			Leer.mostrarEnPantalla("3.Insertar venta");
			Leer.mostrarEnPantalla("4.Leer una venta ademas su cliente y su produzcto correspondiente");
			Leer.mostrarEnPantalla("5.Obtener un listado de los productos que tienen un precio inferior a 100.");
			Leer.mostrarEnPantalla("6.Obtener un listado de los clientes que viven en Zaragoza.");
			Leer.mostrarEnPantalla("7.Obtener un listado de los clientes cuyo nombre empieza por la letra R.");
			Leer.mostrarEnPantalla(
					"8.Modificar el precio de todos los productos de forma que incluyan un IVA del 21%.");
			Leer.mostrarEnPantalla("9.Modificar el precio asociado de un producto dado.");

			Leer.mostrarEnPantalla("0.Salir");

			Leer.mostrarEnPantalla("-----------------------------");
			menu = Leer.pedirEntero("Introduzca una opcion de menu");

			switch (menu) {
			case 1:
				introducirCliente(sesion);
				break;
			case 2:
				introducirProducto(sesion);
				break;
			case 3:
				introducirVenta(sesion);
				break;
			case 4:
				consultarVentas(sesion);
				break;
			case 5:
				consultarProductosPorPrecio(sesion);
				break;
			case 6:
				consultarClientesPorLocalidad(sesion);
				break;
			case 7:
				consultarClientesPorInicial(sesion);
				break;
			case 8:
				subirIva(sesion);
				break;
			case 9:
				cambiarPrecioProducto(sesion);
				break;
			case 0:
				sesion.close();
				System.exit(0);
				break;
			default:
				break;
			}
		} while (menu != 0);
	}

	/* Metodo para cambiar el Precio de un producto */
	private static void cambiarPrecioProducto(SessionFactory sesion) {

		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "update from Producto p set p.pvp = :precio where p.idproducto = :identificador";
		Query q = session.createQuery(sql);// No entiendo esta advertencia que aparece siempre <R>
		int identificador = elegirProductoExistente(session);// Metodo para elegir un producto que exista
		q.setParameter("identificador", identificador);
		int precio = numeroMayor0("Introduzca el nuevo precio");
		q.setParameter("precio", precio);
		q.executeUpdate();

		tx.commit();

	}

	private static int numeroMayor0(String texto) {
		
		int numero;
		do {
			numero = Leer.pedirEntero(texto);
			if (numero<=0) {
				Leer.mostrarEnPantalla("El valor debe ser mayor que 0");
			}
		} while (numero<=0);
		return numero;
	}

	/*Metodo para aplicar Iva a los productos. El precio final sera siempre un entero
	 */
	private static void subirIva(SessionFactory sesion) {
		try {
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			String sql = "update from Producto p set p.pvp = p.pvp*1.21";// Estamos guardando un entero Asi que nada de
																			// decimales
			Query q = session.createQuery(sql);
			q.executeUpdate();
			tx.commit();
			Leer.mostrarEnPantalla("Se ha incrementado el precio de todos los productos un 21%");
		} catch (Exception e) {
			System.err.println("Se ha producido un error");
			// TODO: handle exception
		}

	}
	/*Metodo para buscar clientes por Inicial (Lo he cambiado a inicio No  Distingue Mayusculas/Minusculas*/
	private static void consultarClientesPorInicial(SessionFactory sesion) {

		listarCliente(sesion);
		try {
			Session session = sesion.openSession();
			String hql = "from Cliente where nombre like :inicial";
			Query q = session.createQuery(hql);
			String inicial = Leer.pedirCadena("Introduzca el inicio del cliente a Buscar","\\w{0,9}")+"%";
			q.setParameter("inicial", inicial);
			ArrayList<Cliente> clientes = (ArrayList<Cliente>) q.list();
			imprimirClientes(clientes);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void consultarClientesPorLocalidad(SessionFactory sesion) {

		try {
			listarCliente(sesion);
			Session session = sesion.openSession();
			String hql = "from Cliente where poblacion like :localidad ";
			Query q = session.createQuery(hql);

			String localidad = Leer.pedirCadena("Introduzca la localidad a buscar","\\w{0,19}")+"%";
			q.setParameter("localidad", localidad);// No lo pido por teclado porque tengo pocas localidades

			ArrayList<Cliente> clientes = (ArrayList<Cliente>) q.list();
			imprimirClientes(clientes);
			
			session.close();
		} catch (Exception e) {
			System.err.println("Se ha producido un error");
		}

	}

	private static void consultarProductosPorPrecio(SessionFactory sesion) {
		listarProductos(sesion);
		try {
			Session session = sesion.openSession();
			String hql = "from Producto where pvp <= :precio";
			Query q = session.createQuery(hql);
			int precio = Leer.pedirEntero("Introduzca un precio maximo a Buscar");
			q.setParameter("precio", precio);
			ArrayList<Producto> productos = (ArrayList<Producto>) q.list();
			imprimirProductos(productos);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void consultarVentas(SessionFactory sesion) {
		listarVentas(sesion);

	}

	private static void introducirVenta(SessionFactory sesion) {
		try {

			listarVentas(sesion);
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			Leer.mostrarEnPantalla("Inserte una venta");
			int idventa = elegirVentaNoExistente(session);
			Cliente cliente = session.load(Cliente.class, elegirClienteExistente(session));
			Producto producto = session.load(Producto.class, elegirProductoExistente(session));
			java.util.Date hoy = new java.util.Date();
			java.sql.Date fechaventa = new java.sql.Date(hoy.getTime());
			Integer cantidad;
			do {
				cantidad = Leer.pedirEntero("Introduzca la cantidad", "\\d{0,11}");
				if (cantidad<=0) {
					Leer.mostrarEnPantalla("La cantidad debe ser mayor que 0");
				}
			} while (cantidad<=0);
			Venta venta = new Venta(idventa, cliente, producto, fechaventa, cantidad);
			session.save(venta);
			// session.delete(venta);
			tx.commit();
			session.close();

		} catch (org.hibernate.exception.ConstraintViolationException e) {//Esta validado no deberia entrar
			System.err.println(e.getConstraintName());
			System.err.println(e.getErrorCode());
			System.err.println("Venta duplicada");

		} catch (org.hibernate.TransientPropertyValueException e) { //Esta validado no deberia entrar
			System.err.println(e.getMessage());
			System.err.println(e.getPropertyName());
			System.err.println("El Producto o el cliente no Existe");

		} catch (Exception e) {// A saber que otros errores me va a dar
			System.err.println("Ha dado un error que a saber");
		}
	}

	private static void listarVentas(SessionFactory sesion) {//Estoy leyendo el producto y cliente
		try {// He creido suficiente imprimir solo los nombres
			Session session = sesion.openSession();
			Query q = session.createQuery("from Venta");
			ArrayList<Venta> ventas = (ArrayList<Venta>) q.list();
			System.out.printf("%10s | %10s| %10s | %25s | %10s| %30s\n", "Id cliente", "Fecha","idProducto", "Producto","IdCliente", "Cliente");
			Iterator<Venta> iter = ventas.iterator();
			for (Venta venta : ventas) {// Pongo el nombre del producto y cliente para que sea mas claro
				System.out.printf("%10s | %10s| %10s | %25s | %10s| %30s\n", venta.getIdventa(), venta.getFechaventa(),venta.getProducto().getIdproducto(),
						venta.getProducto().getDescripcion(), venta.getCliente().getIdcliente(),venta.getCliente().getNombre());

			}
			session.close();
		} catch (Exception e) {
			System.err.println("Se ha producido un error");
		}
	}

	private static void introducirProducto(SessionFactory sesion) {
		try {
			listarProductos(sesion);

			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			Leer.mostrarEnPantalla("Inserte un producto");
			int idproducto = elegirProductoNoExistente(session);

			String descripcion = Leer.pedirCadena("Introduzca el nombre del Producto", "\\w{0,50}");
			Integer stockactual = Leer.pedirEntero("Introduzca el Stock", "\\d{0,11}");
			Integer stockminimo = Leer.pedirEntero("Introduzca el Stock Minimo", "\\d{0,11}");
			Integer pvp = Leer.pedirEntero("Introduzca el Precio de Venta", "\\d{0,11}");
			Set<Venta> ventas = new HashSet<Venta>(0);
			Producto producto = new Producto(idproducto, descripcion, stockactual, stockminimo, pvp, ventas);
			session.save(producto);
			tx.commit();
			session.close();
		} catch (org.hibernate.exception.ConstraintViolationException e) {//Esta validado no deberia entrar
			System.err.println(e.getConstraintName());
			System.err.println(e.getErrorCode());
			System.err.println("Cliente duplicado");

		} catch (org.hibernate.TransientPropertyValueException e) {//Esta validado no deberia entrar
			System.err.println(e.getMessage());
			System.err.println(e.getPropertyName());
			System.err.println("Este Error no lo debe dar");

		} catch (Exception e) {// A saber que otros errore me va a dar
			System.err.println("Se ha producido un error");// Seguramente de conexion
			System.err.println(e.getMessage());// He sido incapaz de saber que excepcion concreta controla un dato que
												// excede el tamaño ddel tipo de la base de datos
		}
	}

	private static void listarProductos(SessionFactory sesion) {
		try {
			Session session = sesion.openSession();
			String hql = "from Producto";
			Query q = session.createQuery(hql);
			ArrayList<Producto> productos = (ArrayList<Producto>) q.list();

			imprimirProductos(productos);
			session.close();
		} catch (Exception e) {
			System.err.println("Se ha producido un error");
		}
	}

	private static void imprimirProductos(ArrayList<Producto> productos) {
		
		System.out.printf("%10s | %30s | %10s |%10s | %10s\n", "IdProducto",
				"Descripcion", "pvp", "StockActual","StockMinimo");
		System.out.printf("%10s | %30s | %10s |%10s | %10s\n", "--------",
				"----------", "--------", "---------","---------");
		for (Producto producto : productos) {
			System.out.printf("%10s | %30s | %10s |%10s | %10s\n", producto.getIdproducto(),
					producto.getDescripcion(), producto.getPvp(), producto.getStockactual(),
					producto.getStockminimo());
		}
		Leer.mostrarEnPantalla("---------------------------------------");
	}

	private static int elegirProductoNoExistente(Session session) {
		int idproducto;
		Producto existe;
		do {
			idproducto = numeroMayor0("Introduzca el Id Producto");
			existe = session.get(Producto.class, idproducto);
			if (existe != null) {
				Leer.mostrarEnPantalla("El id de producto ya existe");
			}
		} while (existe != null);
		return idproducto;
	}

	private static int elegirProductoExistente(Session session) {
		int idproducto;
		Producto existe;
		do {
			idproducto = Leer.pedirEntero("Introduzca el Id Producto");
			existe = session.get(Producto.class, idproducto);
			if (existe == null) {
				Leer.mostrarEnPantalla("El id de producto no existe");
			}
		} while (existe == null);
		return idproducto;
	}

	private static void introducirCliente(SessionFactory sesion) {
		try {

			listarCliente(sesion);
			Leer.mostrarEnPantalla("---------------------------------------");
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			Leer.mostrarEnPantalla("Inserte un cliente");
			Leer.mostrarEnPantalla("-----------------------------");
			int idcliente = elegirClienteNoExistente(session);

			String nombre = Leer.pedirCadena("Introduzca el nombre del cliente", "\\w{0,50}");
			String direccion = Leer.pedirCadena("Introduzca ls Direccion", "\\w{0,50}");
			String poblacion = Leer.pedirCadena("Introduzca La Poblacion", "\\w{0,50}");
			String telef = Leer.pedirCadena("Introduzca telefono", "\\w{0,9}");
			String nif = Leer.pedirCadena("Introduzca el Nif", "\\w{0,9}");
			Set<Venta> ventas = new HashSet<Venta>();
			Cliente cliente = new Cliente(idcliente, nombre, direccion, poblacion, telef, nif, ventas);
			session.save(cliente);
			tx.commit();
			session.close();
		} catch (org.hibernate.exception.ConstraintViolationException e) {//Esta validado no deberia entrar
			System.err.println(e.getConstraintName());
			System.err.println(e.getErrorCode());
			System.err.println("Cliente duplicado");

		} catch (org.hibernate.TransientPropertyValueException e) {//Esta validado no deberia entrar
			System.err.println(e.getMessage());
			System.err.println(e.getPropertyName());
			System.err.println("Este Error no lo debe dar");

		} catch (Exception e) {// A saber que otros errore me va a dar
			System.err.println("Se ha producido un error");// Seguramente de conexion
			System.err.println(e.getMessage());// He sido incapaz de saber que excepcion concreta controla un dato que
												// excede el tamaño ddel tipo de la base de datos
		}
	}

	/*Metodo para listar Clientes Lo uso para facilitar la tares al crear clientes*/
	private static void listarCliente(SessionFactory sesion) {
		try {
			Session session = sesion.openSession();
			String hql = "from Cliente";
			Query q = session.createQuery(hql);
			ArrayList<Cliente> clientes = (ArrayList<Cliente>) q.list();
			imprimirClientes(clientes);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void imprimirClientes(ArrayList<Cliente> clientes) {
		System.out.printf("%11s | %30s | %30s |%15s | %9s | %9s\n", "IdCliente", "Nombre","Direccion","Poblacion","Telefono","NIF");		
		System.out.printf("%11s | %30s | %30s |%15s | %9s | %9s\n", "--------", "---------------","------------","------------","---------","---------");					
		for (Cliente cliente : clientes) {
			System.out.printf("%11s | %30s | %30s |%15s | %9s | %9s\n", cliente.getIdcliente(), cliente.getNombre(),cliente.getDireccion(),cliente.getPoblacion(),cliente.getTelef(),cliente.getNif());
		}
		Leer.mostrarEnPantalla("---------------------------------------");
	}
	/*Metodo para elegir un idcliente no usado
	 * Se usara en el metodo crearCliente*/
	private static int elegirClienteNoExistente(Session session) {
		int idcliente;
		Cliente existe;
		do {
			idcliente = numeroMayor0("Introduzca el Id cliente");
			existe = session.get(Cliente.class, idcliente);
			if (existe != null) {
				Leer.mostrarEnPantalla("El id de cliente ya existe");
			}
		} while (existe != null);
		return idcliente;
	}

	private static int elegirClienteExistente(Session session) {
		int idcliente;
		Cliente existe;
		do {
			idcliente = Leer.pedirEntero("Introduzca el Id cliente");
			existe = session.get(Cliente.class, idcliente);
			if (existe == null) {
				Leer.mostrarEnPantalla("El id de cliente no existe");
			}
		} while (existe == null);
		return idcliente;
	}
	/*Metodo para elegir un idventa no usado
	 * Se usara en el metodo crearVenta*/
	private static int elegirVentaNoExistente(Session session) {
		int idventa;
		Venta existe;
		do {
			idventa = numeroMayor0("Introduzca el Id Venta");
			existe = session.get(Venta.class, idventa);
			if (existe != null) {
				Leer.mostrarEnPantalla("El id de venta ya existe");
			}
		} while (existe != null);
		return idventa;
	}
}
