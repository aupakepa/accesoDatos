import java.io.Serializable;

public class VideoJuego implements Comparable<VideoJuego>,Serializable {

	private String nombre;
	private String fabricante;
	private int edadMinima;
	private Float precio;
	private String plataformas;
	private int unidadesVendidas;
	private String formaJuego;
	public VideoJuego(String nombre, String fabricante, int edadMinima, Float precio, String plataformas,
			int unidadesVendidas, String formaJuego) {
		super();
		this.nombre = nombre;
		this.fabricante = fabricante;
		this.edadMinima = edadMinima;
		this.precio = precio;
		this.plataformas = plataformas;
		this.unidadesVendidas = unidadesVendidas;
		this.formaJuego = formaJuego;
	}
	public VideoJuego(String nombre, String fabricante, String plataformas) {
		super();
		this.nombre = nombre;
		this.fabricante = fabricante;
		this.plataformas = plataformas;
	}
	public String getPlataformas() {
		return plataformas;
	}
	public void setPlataformas(String plataformas) {
		this.plataformas = plataformas;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public int getEdadMinima() {
		return edadMinima;
	}
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public int getUnidadesVendidas() {
		return unidadesVendidas;
	}
	public void setUnidadesVendidas(int unidadesVendidas) {
		this.unidadesVendidas = unidadesVendidas;
	}
	public String getFormaJuego() {
		return formaJuego;
	}
	public void setFormaJuego(String formaJuego) {
		this.formaJuego = formaJuego;
	}
	@Override
	public String toString() {
		return "VideoJuego [nombre=" + nombre + ", fabricante=" + fabricante + ", edadMinima=" + edadMinima
				+ ", precio=" + precio + ", unidadesVendidas=" + unidadesVendidas + ", formaJuego=" + formaJuego + "]";
	}
	
	@Override
	public int compareTo(VideoJuego arg0) {
		// TODO Auto-generated method stub
		return this.nombre.compareTo(arg0.getNombre());
	}

}
