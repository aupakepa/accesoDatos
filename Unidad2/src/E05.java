/*EJERCICIO 5
Realiza un programa Java que utilice el driver o conector JDBC con la base de datos biblioteca,
creada anteriormente en SQLite y en MySQL, y que visualice por pantalla los resultados de las
siguientes consultas:
 Listado de todos los libros.
 Listado de todos los socios.
 Listado de todos los préstamos.
 Listado de libros prestados actualmente.
 Número de libros prestamos a un socio determinado.
 Libros que han superado la fecha de fin de préstamo.
 Socios que tienen libros que han superado la fecha de fin de préstamo.*/
import java.sql.*;

import utilidades.Leer;
public class E05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo1", "root","root");
		Statement sentencia = conexion.createStatement();
		int menu=-1;
		do {
			Leer.mostrarEnPantalla("1. Listado de todos los libros");
			Leer.mostrarEnPantalla("2. Listado de todos los socios");
			Leer.mostrarEnPantalla("3. Listado de todos los préstamos.");
			Leer.mostrarEnPantalla("4. Listado de libros prestados actualmente.");
			Leer.mostrarEnPantalla("5. Número de libros prestamos a un socio determinado.");
			Leer.mostrarEnPantalla("6. Listado de libros prestados actualmente.");
			Leer.mostrarEnPantalla("7. Libros que han superado la fecha de fin de préstamo.");
			Leer.mostrarEnPantalla("8. Socios que tienen libros que han superado la fecha de fin de préstamo.");
			Leer.mostrarEnPantalla("9. Salir.\r\n");
			
			menu = Leer.pedirEntero("Introducir una opcion de menu");
			switch (menu) {
			case 1:
				String sql ="select * from libro";
				ResultSet resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%d, %s, %s, %s, %d, %s, %d, %d\n", resul.getInt(1),
					resul.getString(2), resul.getString(3),resul.getString(4),resul.getInt(5),resul.getString(6),resul.getInt(7),resul.getInt(8));
					}
				Leer.mostrarEnPantalla("------------------------------");
				break;
			case 2:
				break;
			case 3:
				break;			
			case 4:
					break;			
			case 5:
						break;
			default:
				break;
			}
			
		} while (menu != 9);
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
