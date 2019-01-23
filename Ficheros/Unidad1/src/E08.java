import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*EJERCICIO 8
Escribe un programa Java que lea de una sola vez todos los caracteres que tiene un fichero de
texto. Visualiza dicho contenido en la pantalla.
*/
public class E08 {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int b=0;
		File fichero = new File("./texto");
		if (!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream bytes = new FileOutputStream(fichero,true);
			for (int i = 0; i < 100; i++) {
				bytes.write(i);
			}
			bytes.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			FileInputStream entrada = new FileInputStream(fichero);
			while ((b = entrada.read()) != -1 ) {
				System.out.println(b);
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
