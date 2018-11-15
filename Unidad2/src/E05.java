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
		ResultSet resul;
		String sql;
		int menu=-1;
		do {
			Leer.mostrarEnPantalla("1. Listado de todos los libros");
			Leer.mostrarEnPantalla("2. Listado de todos los socios");
			Leer.mostrarEnPantalla("3. Listado de todos los préstamos.");
			Leer.mostrarEnPantalla("4. Listado de libros prestados actualmente.");
			Leer.mostrarEnPantalla("5. Número de libros prestamos a un socio determinado.");
			Leer.mostrarEnPantalla("6. Libros que han superado la fecha de fin de préstamo.");
			Leer.mostrarEnPantalla("7. Socios que tienen libros que han superado la fecha de fin de préstamo.");
			Leer.mostrarEnPantalla("9. Salir.\r\n");
			
			menu = Leer.pedirEntero("Introducir una opcion de menu");
			switch (menu) {
			case 1:
				sql ="select * from libro";
				resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%d, %s, %s, %s, %d, %s, %d, %d\n", resul.getInt(1),
					resul.getString(2), resul.getString(3),resul.getString(4),resul.getInt(5),resul.getString(6),resul.getInt(7),resul.getInt(8));
					}
				Leer.mostrarEnPantalla("------------------------------");
				break;
			case 2:
				sql ="select * from socio";
				resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%d, %s, %s, %s, %s, %s\n", resul.getInt(1),
					resul.getString(2), resul.getString(3),resul.getDate(4),resul.getString(5),resul.getString(6));
					}
				Leer.mostrarEnPantalla("------------------------------");
				resul.close();
				break;
			case 3:
				sql ="select * from prestamo";
				resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%d, %d, %s, %s\n", resul.getInt(1),
					resul.getInt(2), resul.getDate(3),resul.getDate(4));
					}
				Leer.mostrarEnPantalla("------------------------------");
				resul.close();
				break;			
			case 4:
				sql ="select distinct codigolibro, titulo from libro inner join prestamo using(codigolibro) where fechafin > '2010-11-02'";
				resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%s, %s\n", resul.getInt(1),
					resul.getString(2));
					}
				Leer.mostrarEnPantalla("------------------------------");
				resul.close();
					break;			
			case 5:
				sql ="select count(*) from  prestamo where codigosocio =102";
				resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%d,\n", resul.getInt(1));
					}
				Leer.mostrarEnPantalla("------------------------------");
				resul.close();
						break;
			case 6:
				sql ="select * from  prestamo where fechafin > ADDDATE(fechainicio, INTERVAL 10 DAY)";
				resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%d, %d, %s, %s\n", resul.getInt(1),
					resul.getInt(2), resul.getDate(3),resul.getDate(4));
					}
				Leer.mostrarEnPantalla("------------------------------");
				resul.close();
						break;						
						
			case 7:
				sql ="select distinct codigosocio, nombre, apellidos from  prestamo inner join socio using (codigosocio) where fechafin > ADDDATE(fechainicio, INTERVAL 10 DAY)";
				resul = sentencia.executeQuery(sql);
				
				while (resul.next()) {
					System.out.printf("%d, %s, %s\n", resul.getInt(1), resul.getString(2),resul.getString(3));
					}
				Leer.mostrarEnPantalla("------------------------------");
				resul.close();
						break;							
						
						
			default:
				break;
			}
			
		} while (menu != 9);
		
		sentencia.close();
		conexion.close();

	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
	}
	}

}
