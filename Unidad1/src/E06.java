import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*EJERCICIO 6
Escribe un programa Java que haga una copia de seguridad del fichero de texto creado en el ejercicio anterior.*/
public class E06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero = new File(".", "E05");
		String texto = "";
		texto = leerFichero(fichero, texto);
		escribirFichero(texto);

	}

	private static void escribirFichero(String texto) {
		FileWriter escribir;
		try {
			escribir = new FileWriter(new File("E06"), false);
			for (int j = 0; j < texto.length(); j++) {
				escribir.write(texto.charAt(j));
			}

			escribir.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String leerFichero(File fichero, String texto) {
		FileReader leer;
		int i;
		try {
			leer = new FileReader(fichero);
			while ((i = leer.read()) != -1) {
				texto = texto.concat((char) i + "");
			}
			leer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return texto;
	}

}

