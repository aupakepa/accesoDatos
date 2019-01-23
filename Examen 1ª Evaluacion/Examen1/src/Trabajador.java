
public class Trabajador extends Persona implements Comparable<Persona> {
private String categoria;
private Float sueldo;
@Override
public String toString() {
	return "Trabajador  " + super.getApellido()+" "+ super.getNombre()+ "[categoria=" + categoria + ", sueldo=" + sueldo + "]";
}
public String getCategoria() {
	return categoria;
}
public void setCategoria(String categoria) {
	this.categoria = categoria;
}
public Float getSueldo() {
	return sueldo;
}
public void setSueldo(Float sueldo) {
	this.sueldo = sueldo;
}
public Trabajador(String nombre, String apellido, String categoria, Float sueldo) {
	super(nombre, apellido);
	this.categoria = categoria;
	this.sueldo = sueldo;
}
}
