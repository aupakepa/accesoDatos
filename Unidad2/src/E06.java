import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Leer;

/*EJERCICIO 6
Realiza un programa Java que utilice el driver o conector JDBC con la base de datos sakila de
MySQL y que visualice por pantalla la siguiente información de dicha base de datos:
A) Nombre de la base de datos.
B) URL de alojamiento de la base de datos.
C) Nombre del driver JDBC en uso.
D) Nombre del usuario actual.
E) Nombres de las tablas que se encuentran en la base de datos.
F) Nombres, tipos y tamaños de las columnas de la tabla ACTOR.
G) Columnas que forman la clave primaria de la tabla ACTOR.
H) Lista de las claves ajenas que utilizan la clave primaria de la tabla ACTOR.
*/
public class E06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sakila", "root", "root");
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet resul;
			String sql;
			int menu = -1;
			do {
				Leer.mostrarEnPantalla("1. Nombre de la base de datos.");
				Leer.mostrarEnPantalla("2. URL de alojamiento de la base de datos");
				Leer.mostrarEnPantalla("3. Nombre del driver JDBC en uso.");
				Leer.mostrarEnPantalla("4. Nombre del usuario actual.");
				Leer.mostrarEnPantalla("5. Nombres de las tablas que se encuentran en la base de datos.");
				Leer.mostrarEnPantalla("6. Nombres, tipos y tamaños de las columnas de la tabla ACTOR.");
				Leer.mostrarEnPantalla("7. Columnas que forman la clave primaria de la tabla ACTOR.");
				Leer.mostrarEnPantalla("8. Lista de las claves ajenas que utilizan la clave primaria de la tabla ACTOR.");
				Leer.mostrarEnPantalla("9. Salir.\r\n");

				menu = Leer.pedirEntero("Introducir una opcion de menu");
				switch (menu) {
				case 1:
					Leer.mostrarEnPantalla(dbmd.getDatabaseProductName());

					Leer.mostrarEnPantalla("------------------------------");
					break;
				case 2:
					Leer.mostrarEnPantalla(dbmd.getURL());

					Leer.mostrarEnPantalla("------------------------------");
					
					break;
				case 3:
					Leer.mostrarEnPantalla(dbmd.getDriverName());

					Leer.mostrarEnPantalla("------------------------------");
					break;
				case 4:
					Leer.mostrarEnPantalla(dbmd.getUserName());

					Leer.mostrarEnPantalla("------------------------------");
					break;
				case 5:
					String[] tipos = {"table"};
					resul =dbmd.getTables("sakila", "sakila", null, tipos);
					while (resul.next()) {
					//	String catalogo = resul.getString("TABLE_CAT");
					//	String esquema = resul.getString("TABLE_SCHEM");
						String nombre = resul.getString(3);
					//	String tipo = resul.getString("TABLE_TYPE");
					//	Leer.mostrarEnPantalla(catalogo);
					//	Leer.mostrarEnPantalla(esquema);
						Leer.mostrarEnPantalla(nombre);
					//	Leer.mostrarEnPantalla(tipo);
						
					}
					break;
				case 6:
					
					ResultSet columnas = dbmd.getColumns("sakila", "sakila","actor", null);

					while (columnas.next()) {
						Leer.mostrarEnPantalla(columnas.getString(4));
					}
					
					break;

				case 7:
					ResultSet primarias = dbmd.getPrimaryKeys("sakila", "sakila","actor");
					while (primarias.next()) {
						Leer.mostrarEnPantalla(primarias.getString(4));
					}
					break;
				case 8:
					ResultSet ajenas = dbmd.getExportedKeys("sakila", "sakila","actor");
					while (ajenas.next()) {
						Leer.mostrarEnPantalla(ajenas.getString(7));
					}
					break;

				default:
					break;
				}

			} while (menu != 9);

	conexion.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

}
