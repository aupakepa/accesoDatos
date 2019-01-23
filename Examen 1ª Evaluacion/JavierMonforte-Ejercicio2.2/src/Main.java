
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import primero.*;
import utilidades.Leer;

/**
 * @author Javier Monforte Taboada 12 dic. 2018
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		int menu;
		do {
			Leer.mostrarEnPantalla("\n\t1.Dar de alta medios de comunicacion");
			Leer.mostrarEnPantalla("\t2.Dar de Alta Fotos");
			Leer.mostrarEnPantalla("\t3.Anotar publicaciones");
			Leer.mostrarEnPantalla("\t4.Buscar por el nombre del medio de publicacion");
			Leer.mostrarEnPantalla("\t5.Modificar precio de una foto");
			Leer.mostrarEnPantalla("\t6.Imprimir Fotos");
			Leer.mostrarEnPantalla("\t7.Imprimir Medios");
			Leer.mostrarEnPantalla("\t8.Imprimir Publicaciones");
			Leer.mostrarEnPantalla("\t9.Salir");
			Leer.mostrarEnPantalla("-----------------------------");

			menu = Leer.pedirEntero("Introduzca la opcion de menu");

			switch (menu) {

			case 1:
				introducirMedio(sesion);

				break;

			case 2:
				introducirFotos(sesion);

				break;

			case 3:
				introducirPublicacion(sesion);

				break;

			case 4:


				break;
			case 5:


				break;
			case 6:
				imprimirFotos(sesion);
				break;
			case 7:
				imprimirMedioscomunicacion(sesion);
				break;
			case 8:
				listarPublicaciones(sesion);
				break;
			case 9:

				break;
			default:

				break;
			}

		} while (menu != 9);

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
	private static void consultarMediosPorInicial(SessionFactory sesion) {

		imprimirMedioscomunicacion(sesion);
		try {
			Session session = sesion.openSession();
			String hql = "from Mediocomunicacion where nombre like :inicial";
			Query q = session.createQuery(hql);
			String inicial = Leer.pedirCadena("Introduzca el inicio del medio a Buscar","\\w{0,9}")+"%";
			q.setParameter("inicial", inicial);
			ArrayList<Mediocomunicacion> medios = (ArrayList<Mediocomunicacion>) q.list();
			imprimirMedios(medios);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void consultarMediosPorLocalidad(SessionFactory sesion) {

		try {
			imprimirMedioscomunicacion(sesion);
			Session session = sesion.openSession();
			String hql = "from Mediocomunicacion where poblacion like :localidad ";
			Query q = session.createQuery(hql);

			String localidad = Leer.pedirCadena("Introduzca la localidad a buscar","\\w{0,19}")+"%";
			q.setParameter("localidad", localidad);// No lo pido por teclado porque tengo pocas localidades

			ArrayList<Mediocomunicacion> medios = (ArrayList<Mediocomunicacion>) q.list();
			imprimirMedios(medios);
			
			session.close();
		} catch (Exception e) {
			System.err.println("Se ha producido un error");
		}

	}

	private static void consultarPublicacionesPorPrecio(SessionFactory sesion) {
		listarPublicaciones(sesion);
		try {
			Session session = sesion.openSession();
			String hql = "from Publicaciones where pvp <= :precio";
			Query q = session.createQuery(hql);
			int precio = Leer.pedirEntero("Introduzca un precio maximo a Buscar");
			q.setParameter("precio", precio);
			ArrayList<Publicaciones> publicaciones = (ArrayList<Publicaciones>) q.list();
			imprimirPublicaciones(publicaciones);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void consultarFotos(SessionFactory sesion) {
		imprimirFotos(sesion);

	}

	private static void introducirFotos(SessionFactory sesion) {
		try {

			imprimirFotos(sesion);
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			Leer.mostrarEnPantalla("Inserte una Foto");
			int idFoto = elegirFotosNoExistente(session);
			String nombre = Leer.pedirCadena("Introduzca el nombre", "(\\w|\\W){0,50}");
			String descripcion = Leer.pedirCadena("Introduzca la descripcion", "(\\w|\\W){0,50}");
			String tematica = Leer.pedirCadena("Introduzca la tematica", "(\\w|\\W){0,50}");
			Integer anio = Leer.pedirEntero("Introduzca el año", "\\d{0,11}");
			Set<Publicaciones> publicaciones = new HashSet<Publicaciones>();
			int precio;
			do {
				precio = Leer.pedirEntero("Introduzca el Precio", "\\d{0,11}");
				if (precio<=0) {
					Leer.mostrarEnPantalla("La cantidad debe ser mayor que 0");
				}
			} while (precio<=0);
			Fotos foto = new Fotos(idFoto, nombre, descripcion, tematica, anio, precio, publicaciones);
			session.save(foto);
			tx.commit();
			session.close();

		} catch (org.hibernate.exception.ConstraintViolationException e) {//Esta validado no deberia entrar
			System.err.println(e.getConstraintName());
			System.err.println(e.getErrorCode());
			System.err.println("Foto duplicada");

		} catch (org.hibernate.TransientPropertyValueException e) { //Esta validado no deberia entrar
			System.err.println(e.getMessage());
			System.err.println(e.getPropertyName());

		} catch (Exception e) {// A saber que otros errores me va a dar
			System.err.println("Ha dado un error que a saber");
		}
	}

	private static void imprimirFotos(SessionFactory sesion) {//Estoy leyendo el producto y cliente
		try {// He creido suficiente imprimir solo los nombres
			Session session = sesion.openSession();
			Query q = session.createQuery("from Fotos");
			ArrayList<Fotos> fotos = (ArrayList<Fotos>) q.list();
			System.out.printf("%11s | %30s| %30s | %30s | %11s| %11s\n", "Id Foto", "Nombre","Descripcion", "Tematica","año", "Precio");
			Iterator<Fotos> iter = fotos.iterator();
			for (Fotos foto : fotos) {// Pongo el nombre del producto y cliente para que sea mas claro
				System.out.printf("%11s | %30s| %30s | %30s | %11s| %11s\n", foto.getIdfoto(), foto.getNombre(),foto.getDescripcion(),
						foto.getTematica(), foto.getAnio(),foto.getPrecio());

			}
			session.close();
		} catch (Exception e) {
			System.err.println("Se ha producido un error");
		}
	}

	private static void introducirPublicacion(SessionFactory sesion) {
		try {
			listarPublicaciones(sesion);

			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			Leer.mostrarEnPantalla("Inserte un publicacion");
			int idpublicacion = elegirPublicacionesNoExistente(session);

			String piePublicacion = Leer.pedirCadena("Introduzca el pie de la Publicacion", "(\\w|\\W){0,50}");
			imprimirFotos(sesion);
			Fotos foto = session.load(Fotos.class, elegirFotosExistente(session));
			imprimirMedioscomunicacion(sesion);
			Mediocomunicacion medio = session.load(Mediocomunicacion.class, elegirMediosExistente(session));
			java.util.Date hoy = new java.util.Date();
			java.sql.Date fechaPublicacion = new java.sql.Date(hoy.getTime());
			Publicaciones publicacion = new Publicaciones(idpublicacion, foto, medio, fechaPublicacion, piePublicacion);
			session.save(publicacion);
			tx.commit();
			session.close();
		} catch (org.hibernate.exception.ConstraintViolationException e) {//Esta validado no deberia entrar
			System.err.println(e.getConstraintName());
			System.err.println(e.getErrorCode());
			System.err.println("Publicacion duplicada");

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

	private static void listarPublicaciones(SessionFactory sesion) {
		try {
			Session session = sesion.openSession();
			String hql = "from Publicaciones";
			Query q = session.createQuery(hql);
			ArrayList<Publicaciones> publicaciones = (ArrayList<Publicaciones>) q.list();

			imprimirPublicaciones(publicaciones);
			session.close();
		} catch (Exception e) {
			System.err.println("Se ha producido un error");
		}
	}

	private static void imprimirPublicaciones(ArrayList<Publicaciones> publicaciones) {
		
		System.out.printf("%11s | %50s | %50s |%50s | %11s\n", "IdPubl.",
				"NombreFoto", "NombreMedio", "Pie Publicacion","Fecha");
		System.out.printf("%11s | %50s | %50s |%50s | %11s\n", "--------",
				"----------", "--------", "---------","---------");
		for (Publicaciones publicacion : publicaciones) {
			System.out.printf("%11s | %50s | %50s |%50s | %11s\n", publicacion.getIdpublicacion(), publicacion.getFotos().getNombre(), publicacion.getMediocomunicacion().getNombre(),publicacion.getPiePublicacion(),publicacion.getFechaPublicacion());
		}
		Leer.mostrarEnPantalla("---------------------------------------");
	}

	private static int elegirPublicacionesNoExistente(Session session) {
		int idPublicacion;
		Publicaciones existe;
		do {
			idPublicacion = numeroMayor0("Introduzca el Id Publicacion");
			existe = session.get(Publicaciones.class, idPublicacion);
			if (existe != null) {
				Leer.mostrarEnPantalla("El id de publicacion ya existe");
			}
		} while (existe != null);
		return idPublicacion;
	}

	private static int elegirProductoExistente(Session session) {
		int idpublicacion;
		Publicaciones existe;
		do {
			idpublicacion = Leer.pedirEntero("Introduzca el Id Publicacion");
			existe = session.get(Publicaciones.class, idpublicacion);
			if (existe == null) {
				Leer.mostrarEnPantalla("El id de publicacion no existe");
			}
		} while (existe == null);
		return idpublicacion;
	}

	private static void introducirMedio(SessionFactory sesion) {
		try {

			imprimirMedioscomunicacion(sesion);
			Leer.mostrarEnPantalla("---------------------------------------");
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			Leer.mostrarEnPantalla("Inserte un medio");
			Leer.mostrarEnPantalla("-----------------------------");
			int idmedio = elegirMedioNoExistente(session);
			String nombre = Leer.pedirCadena("Introduzca el nombre", "(\\w|\\W){0,50}");
			String direccion = Leer.pedirCadena("Introduzca ls Direccion", "(\\w|\\W){0,50}");
			String email = Leer.pedirCadena("Introduzca el email", "(\\w|\\W){0,50}");
			String telefono = Leer.pedirCadena("Introduzca el telefono", "(\\w|\\W){0,50}");
			Set<Publicaciones> publicaciones = new HashSet<Publicaciones>();
			Mediocomunicacion medio = new Mediocomunicacion(idmedio, nombre, direccion, email, telefono,publicaciones);
			session.save(medio);
			tx.commit();
			session.close();
		} catch (org.hibernate.exception.ConstraintViolationException e) {//Esta validado no deberia entrar
			System.err.println(e.getConstraintName());
			System.err.println(e.getErrorCode());
			System.err.println("Medio duplicado");

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
	private static void imprimirMedioscomunicacion(SessionFactory sesion) {
		try {
			Session session = sesion.openSession();
			String hql = "from Mediocomunicacion";
			Query q = session.createQuery(hql);
			ArrayList<Mediocomunicacion> medios = (ArrayList<Mediocomunicacion>) q.list();
			imprimirMedios(medios);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void imprimirMedios(ArrayList<Mediocomunicacion> medios) {
		System.out.printf("%11s | %30s | %30s |%30s | %30s \n", "IdMedio", "Nombre","Direccion","email","Telefono");		
		System.out.printf("%11s | %30s | %30s |%30s | %30s \n", "--------", "---------------","------------","------------","---------","---------");					
		for (Mediocomunicacion medio : medios) {
			System.out.printf("%11s | %30s | %30s |%30s | %30s \n", medio.getIdmedio(), medio.getNombre(),medio.getDireccion(),medio.getEmail(),medio.getTelefono());
		}
		Leer.mostrarEnPantalla("---------------------------------------");
	}
	/*Metodo para elegir un idcliente no usado
	 * Se usara en el metodo crearCliente*/
	private static int elegirMedioNoExistente(Session session) {
		int idmedio;
		Mediocomunicacion existe;
		do {
			idmedio = numeroMayor0("Introduzca el Id Medio");
			existe = session.get(Mediocomunicacion.class, idmedio);
			if (existe != null) {
				Leer.mostrarEnPantalla("El id de cliente ya existe");
			}
		} while (existe != null);
		return idmedio;
	}

	private static int elegirMediosExistente(Session session) {
		int idmedio;
		Mediocomunicacion existe;
		do {
			idmedio = Leer.pedirEntero("Introduzca el Id del medio");
			existe = session.get(Mediocomunicacion.class, idmedio);
			if (existe == null) {
				Leer.mostrarEnPantalla("El id del medio no existe");
			}
		} while (existe == null);
		return idmedio;
	}
	/*Metodo para elegir un idventa no usado
	 * Se usara en el metodo crearVenta*/
	private static int elegirFotosNoExistente(Session session) {
		int idventa;
		Fotos existe;
		do {
			idventa = numeroMayor0("Introduzca el Id Foto");
			existe = session.get(Fotos.class, idventa);
			if (existe != null) {
				Leer.mostrarEnPantalla("El id de venta ya existe");
			}
		} while (existe != null);
		return idventa;
	}
	private static int elegirFotosExistente(Session session) {
		int idfoto;
		Fotos existe;
		do {
			idfoto = Leer.pedirEntero("Introduzca el Id de la foto");
			existe = session.get(Fotos.class, idfoto);
			if (existe == null) {
				Leer.mostrarEnPantalla("El id de la foto no existe");
			}
		} while (existe == null);
		return idfoto;
	}
}
	

