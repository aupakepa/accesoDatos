package primero;
// Generated 29-nov-2018 18:50:03 by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto implements java.io.Serializable {

	private int idproducto;
	private String descripcion;
	private Integer stockactual;
	private Integer stockminimo;
	private Integer pvp;
	private Set<Venta> ventas = new HashSet<Venta>(0);

	public Producto() {
	}

	public Producto(int idproducto, String descripcion) {
		this.idproducto = idproducto;
		this.descripcion = descripcion;
	}

	public Producto(int idproducto, String descripcion, Integer stockactual, Integer stockminimo, Integer pvp,
			Set<Venta> ventas) {
		this.idproducto = idproducto;
		this.descripcion = descripcion;
		this.stockactual = stockactual;
		this.stockminimo = stockminimo;
		this.pvp = pvp;
		this.ventas = ventas;
	}

	public int getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getStockactual() {
		return this.stockactual;
	}

	public void setStockactual(Integer stockactual) {
		this.stockactual = stockactual;
	}

	public Integer getStockminimo() {
		return this.stockminimo;
	}

	public void setStockminimo(Integer stockminimo) {
		this.stockminimo = stockminimo;
	}

	public Integer getPvp() {
		return this.pvp;
	}

	public void setPvp(Integer pvp) {
		this.pvp = pvp;
	}

	public Set<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

}
