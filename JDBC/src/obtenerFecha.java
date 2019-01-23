import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import utilidades.Leer;

/* Ejemplos simples fechas */
public class obtenerFecha {

	public static void main(String[] args) {
		Date fechaActual = obtenerFechaActual(); // Obtener fecha
		System.out.println("La fecha Date actual es: " + fechaActual);
		Leer.mostrarEnPantalla(fechaActual.toString());
		Date fecha = new Date(2008, 12, 17);
		String fechaA = getFechaActual();
		System.out.println("La fecha actual cadena es: " + fechaA);
	}

	// ---------------------------------- Obtener la fecha actual DATE-----------------------------
	/*JDBC no soporta el registro de instancias directas de java.util.Date,
	 * necesitamos java.sql (java.sql.Date) que extienden de java.util.Date
	 */
	private static Date obtenerFechaActual() {
		java.util.Date hoy = new java.util.Date();
		return new java.sql.Date(hoy.getTime());
	}// fin obtenerfecha 

	// ---------------------------------- Obtener la fecha actual String con formato-----------------------------
	/* java.util.Date con clase SimpleDateFormat
	 */
			public static String getFechaActual() {
				java.util.Date ahora = new java.util.Date();
				System.out.println(ahora);
			    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
				//SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			    //SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			    return formateador.format(ahora);

		}//fingetFechaActual
}
