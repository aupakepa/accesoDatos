import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);
		General llamo = new General();
		int entrada;boolean bien;
		
		do {
			bien=false;
		
		System.out.println("MENÚ DE OPERACIONES:");
		System.out.println("--------------------");
		System.out.println("1 - Insertar departamento.");
		System.out.println("2 - Eliminar departamento.");
		System.out.println("3 - Modificar departamentos.");
		System.out.println("4 - Listar departamentos.");
		System.out.println("5 - Salir.");
		System.out.println("------------------------------");
		System.out.println("¿Qué quiere hacer ahora?");
		try {
			entrada = teclado.nextInt();
			
			switch(entrada) {
			
			case 1:
				llamo.conectar();
				llamo.insertar();
				llamo.desconectar();
				break;
				
			case 2:
				llamo.conectar();
				llamo.borrar();
				llamo.desconectar();
				break;
				
			case 3:
				llamo.conectar();
				llamo.modificar();
				llamo.desconectar();
				break;
				
			case 4:
				llamo.conectar();
				System.out.println("Listado");
				llamo.listar();
				llamo.desconectar();
				break;
				
			case 5:
				bien=true;
				break;
				
			default:
				System.out.println("Introduzca un número entre 1 y 5, por favor");
				bien=false;
				break;
				
				
			}
		}catch(InputMismatchException mm) {
			System.out.println("Introduzca un número, por favor.");
			bien=false;
			teclado.next();
		}	
		

	}while(bien==false);	
	}
}
