import java.io.*;
import java.sql.*;

import utilidades.Leer;


/**EJERCICIO 5
Realiza un programa Java que utilice el driver o conector JDBC con la base de datos biblioteca, 
creada anteriormente en SQLite y en MySQL, y que visualice por pantalla los resultados de las siguientes consultas:
 Listado de todos los libros.
 Listado de todos los socios.
 Listado de todos los préstamos.
 Listado de libros prestados actualmente.
 Número de libros prestamos a un socio determinado.
 Libros que han superado la fecha de fin de préstamo.
 Socios que tienen libros que han superado la fecha de fin de préstamo.
 * 
 */
/**
 * @author Javier Monforte Taboada
 * 7 dic. 2018
 */
public class Ejercicio5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into prestamo values (?,?,?,?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/librosprueba", "root", "root");
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			int codigoLibro = 6;
			int codigoSocio = 101;
			sentencia.setInt(1, codigoLibro);
			sentencia.setInt(2, codigoSocio);
			sentencia.setString(3,"2018-10-12");
			sentencia.setString(4,"2018-10-13");
			sentencia.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static void leerScript() {
		StringBuilder sql = new StringBuilder();
		String linea;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/librosprueba?allowMultiQueries=true", "root", "root");
			Statement sentencia = conexion.createStatement();
			File fichero = new File("./libros.sql");
			FileReader in = new FileReader(fichero);
			BufferedReader buffer = new BufferedReader(in);
			while((linea = buffer.readLine()) != null) {
				sql.append(linea);
			}
			sentencia.execute(sql.toString());

			buffer.close();
			in.close();
			sentencia.close();
			conexion.close();
		} catch (EOFException e) {
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void sentenciaSelect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/libros", "root", "root");
			Statement sentencia = conexion.createStatement();
			String sql ="select * from libro where codigolibro > 9";
			ResultSet resul = sentencia.executeQuery (sql);
			while(resul.next()) {
				System.out.printf("%5s | %s\n",resul.getInt(1),resul.getString(2));
			} 
			conexion.close();
			sentencia.close();
			resul.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
