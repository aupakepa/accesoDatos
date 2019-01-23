import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;




public class ConnectMysql {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public  ConnectMysql() throws ClassNotFoundException {
	
	try
    { Class.forName("com.mysql.jdbc.Driver");
     // DriverManager.registerDriver(new OracleDriver());
      // Cada fabricante tiene su clase que hay que registrar antes de conectar
      //Los datos de la primera cadena dependen del fabricate
      // Después son el usuario y la clave hr,hr
      Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost/departamentos","usuario","usuario");
      System.out.println(" Parece ser que nos hemos conectado");
      // se escribe la consulta a realizar
      
      PreparedStatement prst=conn.prepareStatement
         //("select * from DEPARTMENTS");
      ("select * from departments where department_id=?");
    // peligro insercion de código  ("select * from departments where department_id="+id);
  // setInt Sirve para rellenar el interrogante, 1 es el primer interrogante
      prst.setInt(1,80);
      // Se ejecuta la consulta preparada anteriormente
      ResultSet rsst=prst.executeQuery();
      // Se muestran los resultados, que han sido devueltos en una matriz
      while (rsst.next())
      {
        for(int i=1;i<=4;i++)
        {
          System.out.print(rsst.getString(i)+'\t');
        }
        System.out.println();
        System.out.println("Estamos en la fila  :"+rsst.getRow());
      }
      
      ResultSetMetaData rsmd=rsst.getMetaData();
      int ncolumn=rsmd.getColumnCount();
      System.out.println("Número de columnas "+ncolumn);
      for (int i=1;i<=ncolumn;i++)
      { System.out.println(" Nombre de la columna "+i+" "+rsmd.getColumnName(i));
      System.out.println(" Tipo de dato de la columna "+i+" "+rsmd.getColumnTypeName(i)+" "+rsmd.getColumnDisplaySize(i));
      
      }
      conn.close();
      System.out.println();
      System.out.println(" Parece ser que nos hemos desconectado");
    } catch (SQLException sqle)
    {
      sqle.printStackTrace();
      System.out.println(sqle.getErrorCode()+" - "+sqle.getMessage());
    }
    System.out.println("fin de ejecución");
    
  }
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		ConnectMysql cms=new ConnectMysql();
	}

}

