import java.io.Serializable;

/*nombre, sueldo, año de nacimiento y antigüedad*/
public class Medicos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	
	private int anio;
	private double sueldo;
	private int antiguedad;
	public Medicos(String nombre, int anio, double sueldo, int antiguedad) {
		super();
		this.nombre = nombre;
		this.anio = anio;
		this.sueldo = sueldo;
		this.antiguedad = antiguedad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	@Override
	public String toString() {
		return "Medicos [nombre=" + nombre + ", anio=" + anio + ", sueldo=" + sueldo + ", antiguedad=" + antiguedad
				+ "]";
	}
}
