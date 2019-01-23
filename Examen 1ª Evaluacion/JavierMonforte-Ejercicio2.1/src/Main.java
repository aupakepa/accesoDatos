import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Leer;

/**
 * 
 */

/**
 * @author Javier Monforte Taboada
 * 11 dic. 2018
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int menu;
		Conexion conexion = null;
		String sql;
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
				conexion = new Conexion(1);
				int idmedio = Leer.pedirEntero("introduzca un id del medio");
				String nombre = Leer.pedirCadena("Introduzca nombre");
				String direccion = Leer.pedirCadena("Direccion");
				String email = Leer.pedirCadena("email");
				String telefono = Leer.pedirCadena("Telefono");
				
				sql = "INSERT INTO medioComunicacion VALUES (?,?,?,?,?)"; 
				  //Las clases date o calendar no las he usado pero es que no tengo tiempo
				 try { 
				 PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
				 sentencia.setInt(1, idmedio); 
				 sentencia.setString(2,nombre);
				 sentencia.setString(3,direccion); 
				 sentencia.setString(4, email); 
				 sentencia.setString(5, telefono); 
				 sentencia.executeUpdate();
				 sentencia.close();
				  } 
				 catch (SQLException e) { 
				 if (e.getErrorCode() == 1062) {
				 System.out.println("Clave primaria duplicada"); 
				 }
				 System.err.println(e.getMessage()); 
				 System.err.println(e.getSQLState());
				 System.err.println(e.getErrorCode()); 
				 }
					conexion.cerraConexion();

				
				break;

			case 2:

				conexion = new Conexion(1);
				int idfoto = Leer.pedirEntero("introduzca un id de la foto");
				String nombrefoto = Leer.pedirCadena("Introduzca nombre de la foto");
				String descripcion = Leer.pedirCadena("Descripcion");
				String tematica = Leer.pedirCadena("Tematica");
				int anio = Leer.pedirEntero("Introzca el año");
				int precio = Leer.pedirEntero("introduzca un precio entero");
				 sql = "INSERT INTO fotos VALUES (?,?,?,?,?,?)"; 
				  //Las clases date o calendar no las he usado pero es que no tengo tiempo
				 try { 
				 PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
				 sentencia.setInt(1, idfoto); 
				 sentencia.setString(2,nombrefoto);
				 sentencia.setString(3, descripcion); 
				 sentencia.setString(4, descripcion);
				 sentencia.setInt(5,anio); 
				 sentencia.setInt(6, precio); 
				 sentencia.executeUpdate();
				 sentencia.close();
				  } 
				 catch (SQLException e) { 
				 if (e.getErrorCode() == 1062) {
				 System.out.println("Clave primaria duplicada"); 
				 }
				 System.err.println(e.getMessage()); 
				 System.err.println(e.getSQLState());
				 System.err.println(e.getErrorCode()); 
				 }
					conexion.cerraConexion();
				break;

			case 3:
				conexion = new Conexion(1);
				int idpublicacion = Leer.pedirEntero("introduzca un id de la foto");
				int idfoto2 = Leer.pedirEntero("Introzca el medio");
				int idmedio2 = Leer.pedirEntero("introduzca una foto");
				String fechadepublicacion = Leer.pedirCadena("Introduzca nombre de la foto");
				String piepublicacion = Leer.pedirCadena("Descripcion");
			
				 sql = "INSERT INTO publicaciones VALUES (?,?,?,?,?)"; 
				  //Las clases date o calendar no las he usado pero es que no tengo tiempo
				 try { 
				 PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
				 sentencia.setInt(1, idpublicacion); 
				 sentencia.setInt(2,idfoto2); 
				 sentencia.setInt(3, idmedio2); 
				 sentencia.setString(4,fechadepublicacion);
				 sentencia.setString(5, piepublicacion); 
				 sentencia.executeUpdate();
				 sentencia.close();
				  } 
				 catch (SQLException e) { 
				 if (e.getErrorCode() == 1062) {
				 System.out.println("Clave primaria duplicada"); 
				 }
				 System.err.println(e.getMessage()); 
				 System.err.println(e.getSQLState());
				 System.err.println(e.getErrorCode()); 
				 }
					conexion.cerraConexion();

				
				break;
				
			case 4:
				conexion = new Conexion(1);
				StringBuilder sql2 = new StringBuilder();
				sql2.append("SELECT * FROM mediocomunicacion where nombre like '");
				sql2.append(Leer.pedirCadena("Introzca el inicio del nombre de un medio"));
				sql2.append("%'");
				try {
					
					ResultSet resul =  conexion.getSentencia().executeQuery(sql2.toString());

					while( resul.next()) {
						System.out.printf("%25s | %25s| %25s | %25s | %25s\n", resul.getInt(1), resul.getString(2),resul.getString(3), resul.getString(4),resul.getString(5));
					}
					conexion.cerraConexion();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 5:

				imprimirFotos();
				conexion = new Conexion(1);
				StringBuilder sql3 = new StringBuilder();
				sql3.append("UPDATE fotos SET precio =");
				int codigoFoto =  Leer.pedirEntero("Introzca el id de la foto a modificar el precio");
				int precio2 = Leer.pedirEntero("introduzca el nuevo precio");
				sql3.append(precio2);
				sql3.append(" where Idfoto = ");
				sql3.append(codigoFoto);
				try {
					
					
					conexion.getSentencia().executeUpdate(sql3.toString());
					Leer.mostrarEnPantalla("Precio cambiado");
					
					conexion.cerraConexion();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:
				imprimirFotos();
				break;
			case 7:

				
				break;
			case 8:

				
				break;
			case 9:

				
				break;
			default:

				break;
			}

		} while (menu != 9);

	}

	private static void imprimirFotos() {
		Conexion conexion;
		String sql;
		conexion = new Conexion(1);
		sql= "select * from fotos";
		try {
			ResultSet resul = conexion.getSentencia().executeQuery(sql);
			while(resul.next()) {
				
				System.out.printf("%11s | %25s| %25s | %25s | %11s| 11s\n", resul.getInt(1), resul.getString(2),resul.getString(3), resul.getString(4),resul.getInt(5),resul.getInt(6));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


