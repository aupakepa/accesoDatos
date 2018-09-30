import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*EJERCICIO 5
Modifica el programa Java anterior, de tal forma que cada vez que se ejecute el programa, 
vaya añadiendo las nuevas frases introducidas al final del fichero de texto.*/
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String frase;
		Scanner teclado = new Scanner(System.in);
		File fichero = new File(".", "texto");
		if (!fichero.exists()) {
			crearFichero(fichero);
		} 
		FileWriter escribir;
		try {
			escribir = new FileWriter(fichero,true);
			do {
				frase = teclado.nextLine();
				if (!frase.equals("fin")) {
				for (int i = 0; i < frase.length(); i++) {
						escribir.write(frase.charAt(i));	
					}
				escribir.write("\n");
				}
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

