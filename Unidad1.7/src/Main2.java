import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import utilidades.Leer;

/*EJERCICIO 7
Escribe un programa Java que pida una serie de frases por teclado 
hasta que se inserte como frase la palabra “fin”. 
Dichas frases deberán guardarse en un fichero de texto. 
A continuación, el programa visualizará el contenido del fichero, 
frase por frase. Cada vez que se ejecute el programa, 
se tienen que descartar las frases que ya estaban escritas en el fichero. 
Realiza este ejercicio usando la clase BufferedReader.*/
public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String frase = "";
		File fichero = new File("./texto2");
		if (!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}
		FileWriter escribir;
		try {
			escribir = new FileWriter(fichero);
			Leer.mostrarEnPantalla("introduzca frases para terminar escriba fin");
			BufferedWriter buffer = new BufferedWriter(escribir);
			do {
				frase = Leer.pedirCadena("");
				if (!frase.equals("fin")) {
					buffer.write(frase+"\n");
				}
			} while (!frase.equals("fin"));
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		BufferedReader buffreader;
		try {
			buffreader = new BufferedReader(new FileReader(fichero));
			String linea;
			while ((linea = buffreader.readLine()) != null) {
				Leer.mostrarEnPantalla(linea);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
