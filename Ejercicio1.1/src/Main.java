import java.io.File;
import java.util.Scanner;

/*EJERCICIO 1
Escribe un programa Java que inicialice cuatro objetos de tipo File: dos que hagan referencia a ficheros, 
que uno exista y otro no, y los otros dos a directorios, que uno exista y el otro no. 
El programa debe averiguar si existen dichos ficheros o directorios, independientemente de que sean una cosa o la otra.
También debe averiguar para cada objeto que exista si es un fichero o directorio. 
A la hora de visualizar el nombre del fichero o directorio, prueba a usar los diferentes métodos 
get que ofrece la clase File para ver la diferencia que hay entre cada uno de ellos.
*/
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);
		File f1 = new File("C:\\Users\\usuario\\eclipse-workspace\\Ejercicio1.1\\src\\prueba1");
		File f2 = new File("C:\\Users\\usuario\\eclipse-workspace\\Ejercicio1.1\\src\\prueba2");
		File d1 = new File("C:\\Users\\usuario\\eclipse-workspace\\Ejercicio1.1\\src\\dir1");
		File d2 = new File("C:\\Users\\usuario\\eclipse-workspace\\Ejercicio1.1\\src\\dir2");
		System.out.println(f1.exists());
		System.out.println(f2.exists());
		System.out.println(d1.exists());
		System.out.println(d2.exists());

		if (f1.isDirectory()) {
			System.out.println(f1.getTotalSpace()+" es un directorio");
			
		} else {System.out.println(f1.getName()+" es un archivo");}
		if (d1.isDirectory()) {
			System.out.println(d1.getParent()+" es un directorio");
			
		} else {System.out.println(d1.getName()+" es un archivo");}
	}

}
