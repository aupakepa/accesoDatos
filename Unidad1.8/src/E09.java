import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*EJERCICIO 9
Escribe un programa Java que inicialice tres tablas con los datos de una serie de alumnos. 
En una tabla se van a guardar sus nombres, en otra la edad de los alumnos, 
y en la última la nota con decimales que han obtenido en el módulo de Programación. 
Después, el programa deberá almacenar dichos datos en un fichero.*/
/*EJERCICIO 10
A partir del fichero de datos creado en el ejercicio anterior, 
escribe un programa Java que visualice el contenido de este fichero correctamente por pantalla.*/
public class E09 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] nombres = {"javier","juan","lucia"};
		double [] notas = {2.5,5.7,8.9};
		int edad [] = {41,19,20};
		File datos = new File("./datos");
		if (!datos.exists()) {
			try {
				datos.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream paso = new FileOutputStream(datos);
			DataOutputStream salida = new DataOutputStream(paso);
			for (int i = 0; i < edad.length; i++) {
				salida.writeInt(edad[i]);
				salida.writeDouble(notas[i]);
				salida.writeUTF(nombres[i]);
			}
			salida.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileInputStream paso2 = new FileInputStream(datos);
			DataInputStream entrada = new DataInputStream(paso2);
			for (int i = 0; i < edad.length; i++) {
			System.out.println(entrada.readInt());
			System.out.println(entrada.readDouble());
			System.out.println(entrada.readUTF());
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
