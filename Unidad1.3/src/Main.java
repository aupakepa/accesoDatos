import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*EJERCICIO 3
Crea un fichero de texto con algún editor de textos como Notepad 
y después realiza un programa Java que visualice su contenido. 
Cambia el programa Java para que el nombre del fichero se acepte 
al ejecutar desde la línea de comandos.*/
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File directorio = new File("C:\\Users\\Usuario\\accesoDatos\\Unidad1.3\\src\\");
		Scanner teclado = new Scanner(System.in);
		System.out.println("introduzca el nombre del fichero ");
		String archivo = teclado.nextLine();
		File texto = new File(directorio,archivo);
		
		try {
			int i;
			FileReader lectura = new FileReader(texto);
			while ((i = lectura.read()) != -1) {
				System.out.print((char) i);
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("el fichero no se ha encontrado");;
		} catch (IOException e) {
			System.out.println("error en lectura");
			// TODO: handle exception
		}

	}
}
