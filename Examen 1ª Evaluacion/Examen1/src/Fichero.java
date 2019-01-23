import java.io.*;

public class Fichero implements Separable{
	private BufferedReader entrada;
	private BufferedWriter salida;

	public Fichero(String nombre, String io){
		//fin=false;
		if (io.equals("I")){//abrir el fichero para leer
			try{
				entrada=new BufferedReader(new FileReader(nombre));//asignar fichero a buffer
			}catch(IOException e){//controlar el error
				Leer.mostrarEnPantalla("Error al abrir el archivo de entrada "+e.getMessage());
				System.exit(1);
			}
		}
		if (io.equals("O")){//abrir el fichero para escribir
			try{
				salida=new BufferedWriter(new FileWriter(nombre));//asignar fichero a buffer
			}catch(IOException e){//controlar el error
				Leer.mostrarEnPantalla("Error al abrir el archivo de salida "+e.getMessage());
				System.exit(1);
			}
		}
	}
	
	public Persona leer( ){
		Persona persona=null;
		String regTexto=null;
		String nombre= "", apellido="";
		String tipo="";
		String herencia="";
		Float sueldo=0.f;
		int curso=0;
		try {
			regTexto = entrada.readLine();//leemos un registro
			
			if(regTexto.length() != 0){// si tiene contenido procesamos sabiendo que separa cada campo y cuantos campos tiene
				// los valores de cada atributo estan separados por lo que devuelve getSeparador() En este caso ;
				if(regTexto.indexOf(getSeparador())!=-1){// recuperamos el valor del primer atributo
					herencia=regTexto.substring(0, regTexto.indexOf(getSeparador()));
					regTexto = regTexto.substring(regTexto.indexOf(getSeparador()) + 1 );
				}
				if (herencia.equals("1")) {
					if(regTexto.indexOf(getSeparador())!=-1){// recuperamos el valor del segundo atributo
						nombre= regTexto.substring(0, regTexto.indexOf(getSeparador()));
						regTexto = regTexto.substring(regTexto.indexOf(getSeparador()) + 1 );
					}
					if(regTexto.indexOf(getSeparador())!=-1){// recuperamos el valor del segundo atributo
						apellido= regTexto.substring(0, regTexto.indexOf(getSeparador()));
						regTexto = regTexto.substring(regTexto.indexOf(getSeparador()) + 1 );
					}
					if(regTexto.indexOf(getSeparador())!=-1){// recuperamos el valor del segundo atributo
						tipo= regTexto.substring(0, regTexto.indexOf(getSeparador()));
						regTexto = regTexto.substring(regTexto.indexOf(getSeparador()) + 1 );
					}
					curso= Integer.parseInt(regTexto);
					persona=new Estudiante(nombre, apellido, tipo, curso);
					}
				
				if (herencia.equals("2")) {
					if(regTexto.indexOf(getSeparador())!=-1){// recuperamos el valor del segundo atributo
						nombre= regTexto.substring(0, regTexto.indexOf(getSeparador()));
						regTexto = regTexto.substring(regTexto.indexOf(getSeparador()) + 1 );
					}
					if(regTexto.indexOf(getSeparador())!=-1){// recuperamos el valor del segundo atributo
						apellido= regTexto.substring(0, regTexto.indexOf(getSeparador()));
						regTexto = regTexto.substring(regTexto.indexOf(getSeparador()) + 1 );
					}
					if(regTexto.indexOf(getSeparador())!=-1){// recuperamos el valor del segundo atributo
						tipo= regTexto.substring(0, regTexto.indexOf(getSeparador()));
						regTexto = regTexto.substring(regTexto.indexOf(getSeparador()) + 1 );
					}
					
					sueldo= Float.parseFloat(regTexto);
					persona=new Trabajador(nombre, apellido, tipo, sueldo);	
			}	
			return persona;
			}
		}
		catch(IOException e){
			Leer.mostrarEnPantalla("Error al leer en el archivo "+e.getMessage());
			System.exit(1);
		} catch (NullPointerException e) {
			return null;
		}
		return persona;
	}

	public Boolean escribir(Persona persona ){
		
		try {
			if(persona!=null){//si el objeto existe lo escribimos
				if (persona instanceof Estudiante) {
					salida.write("1"+getSeparador()+persona.getNombre()+getSeparador()+persona.getApellido()+getSeparador()+((Estudiante)persona).getEstudios()+getSeparador()+((Estudiante)persona).getCurso()+"\n");
				}
				else {
					salida.write("2"+getSeparador()+persona.getNombre()+getSeparador()+persona.getApellido()+getSeparador()+((Trabajador)persona).getCategoria()+getSeparador()+((Trabajador)persona).getSueldo()+"\n");
				}
			}
		}catch(IOException e){//controlar el error de escritura
			Leer.mostrarEnPantalla("Error al leer en el archivo "+e.getMessage());
			System.exit(1);
		} catch (NullPointerException e) {//controlar el error de asignación de fichero
	        return false;
	    }
		return true;
	}
	
	public void cerrar(String io){ 
		try{
			if(io.equals("I")){//cerrar el buffer de entrada
				entrada.close();
			}
			if(io.equals("O")){//cerrar el buffer de salida
				salida.close();
			}
		}catch(IOException e){//controlar la excepción
			Leer.mostrarEnPantalla("Error al cerrar el archivo "+e.getMessage());
			System.exit(1);
		}
	}
}
