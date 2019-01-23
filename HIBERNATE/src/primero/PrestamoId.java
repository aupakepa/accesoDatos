package primero;
// Generated 07-dic-2018 17:06:26 by Hibernate Tools 5.2.3.Final

/**
 * PrestamoId generated by hbm2java
 */
public class PrestamoId implements java.io.Serializable {

	private int codigoLibro;
	private int codigoSocio;

	public PrestamoId() {
	}

	public PrestamoId(int codigoLibro, int codigoSocio) {
		this.codigoLibro = codigoLibro;
		this.codigoSocio = codigoSocio;
	}

	public int getCodigoLibro() {
		return this.codigoLibro;
	}

	public void setCodigoLibro(int codigoLibro) {
		this.codigoLibro = codigoLibro;
	}

	public int getCodigoSocio() {
		return this.codigoSocio;
	}

	public void setCodigoSocio(int codigoSocio) {
		this.codigoSocio = codigoSocio;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PrestamoId))
			return false;
		PrestamoId castOther = (PrestamoId) other;

		return (this.getCodigoLibro() == castOther.getCodigoLibro())
				&& (this.getCodigoSocio() == castOther.getCodigoSocio());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoLibro();
		result = 37 * result + this.getCodigoSocio();
		return result;
	}

}
