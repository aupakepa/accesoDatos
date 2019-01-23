import java.io.Serializable;

/*nombre, sueldo, año de nacimiento y antigüedad*/
public class Libros implements Serializable {

	/**
	 * 
	 */
	private int idLibro;
	private String titulo;
	private String autor;
	private int ejemplares;
	private String Editorial;
	public Libros(int idLibro, String titulo, String autor, int ejemplares, String editorial) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.ejemplares = ejemplares;
		Editorial = editorial;
	}
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getEjemplares() {
		return ejemplares;
	}
	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}
	public String getEditorial() {
		return Editorial;
	}
	public void setEditorial(String editorial) {
		Editorial = editorial;
	}
	@Override
	public String toString() {
		return "Libros [idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", ejemplares=" + ejemplares
				+ ", Editorial=" + Editorial + "]";
	}
	
}
