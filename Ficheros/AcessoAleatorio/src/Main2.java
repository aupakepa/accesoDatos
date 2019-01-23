import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 */

/**
 * @author Javier Monforte Taboada
 * 9 dic. 2018
 */
public class Main2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero = new File("AleatorioEmple.dat");
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");
			String apellido [] = {"FERNANDEZ","GIL","LOPEZ","RAMOS","SEVILLA","CASILLA","REY"};
			int dep[] = {10,20,10,10,30,30,20};
			Double salario [] = {1000.45,2400.60,3000.0,1500.56,2200.0,1435.87,2000.0};
			StringBuffer buffer = null;
			int n = apellido.length;
			for (int i = 0; i < n; i++) {
				file.writeInt(i+1);
				buffer = new StringBuffer (apellido[i]);
				buffer.setLength(10);
				file.writeChars(buffer.toString());
				file.writeInt(dep[i]);
				file.writeDouble(salario[i]);
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "r");
			int id,dep;
			Double salario;
			char[] apellido = new char[10];
			char aux;
			long posicion = 0;
			for(;;) {
				file.seek(posicion);
				id = file.readInt();
				for (int i = 0; i < apellido.length; i++) {
					apellido[i] = file.readChar();
				}
				String apellidos = new String(apellido);
				dep = file.readInt();
				salario = file.readDouble();
				if(id>0)
					System.out.printf("ID: %s, Apellido: %s, Departamento %d, Salario; %.2f %n",id,apellidos.trim(),dep,salario);
			
			if (file.getFilePointer() == file.length()) {
				break;
			}
			posicion = posicion+36;
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
