
public class Persona implements Comparable <Persona> {

	private String nombre;
	private String apellido;
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Persona(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}

	@Override
	public int compareTo(Persona arg0) {
		// TODO Auto-generated method stub
		return - this.apellido.compareTo(arg0.getApellido());
	}

}
