import java.io.Serializable;

/*Número de departamento: entero.
Nombre: String.
Localidad: String*/
public class Departamentos implements Serializable, Comparable<Departamentos> {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private String nombre;
	private String localidad;

	public Departamentos(int numero, String nombre, String localizable) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.localidad = localizable;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizable() {
		return localidad;
	}

	public void setLocalizable(String localizable) {
		this.localidad = localizable;
	}

	@Override
	public String toString() {
		return "Departamentos [numero=" + numero + ", nombre=" + nombre + ", localizable=" + localidad + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.numero == ((Departamentos)obj).getNumero()) {
			return true;
			} else return false;
		
	}

	@Override
	public int compareTo(Departamentos o) {
		// TODO Auto-generated method stub
		return this.numero-o.getNumero();
	}

}
