import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * EJERCICIO 4 Escribe un programa Java que pida una serie de frases por teclado
 * hasta que se inserte como frase la palabra “fin”. Dichas frases deberán
 * guardarse en un fichero de texto. A continuación, el programa visualizará el
 * contenido del fichero, frase por frase. Cada vez que se ejecute el programa,
 * se tienen que descartar las frases que ya estaban escritas en el fichero.
 * Realiza este ejercicio sin usar la clase BufferedReader.
 **/
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String frase;
		Scanner teclado = new Scanner(System.in);
		File fichero = new File(".", "texto");
		if (fichero.exists()) {
			fichero.delete();
			crearFichero(fichero);
		} else {
			crearFichero(fichero);
		}
		FileWriter escribir;
		try {
			escribir = new FileWriter(fichero);
			do {
				frase = teclado.nextLine();
				for (int i = 0; i < frase.length(); i++) {
					if (!frase.equals("fin")) {
						escribir.write(frase.charAt(i));	
					}
				}
				escribir.write("\n");
			} while (!frase.equals("fin"));
			escribir.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileReader leer;
		int i;
		try {
			leer = new FileReader(fichero);
			while ((i = leer.read()) != -1) {
				System.out.print((char)i);
			}
			leer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void crearFichero(File fichero) {
		try {
			fichero.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
