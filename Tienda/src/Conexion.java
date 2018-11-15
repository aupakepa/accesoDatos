import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	Connection conexion;
	Statement sentencia;

	public Conexion(int opcion) {

		switch (opcion) {
		case 1:
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root", "root");
				this.sentencia = conexion.createStatement();
				;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case 2:
			try {
				Class.forName("org.sqlite.JDBC");
				this.conexion = DriverManager.getConnection("jdbc:sqlite:C:\\BBDD\\Sqlite\\tienda.db");
				this.sentencia = conexion.createStatement();
				;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		default:
			break;
		}

	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Statement getSentencia() {
		return sentencia;
	}

	public void setSentencia(Statement sentencia) {
		this.sentencia = sentencia;
	}

	public void cerraConexion() {

		try {
			this.sentencia.close();
			this.conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
