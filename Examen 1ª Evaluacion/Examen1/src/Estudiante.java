
public class Estudiante extends Persona implements Comparable<Persona>{
private String estudios;
private int curso;

@Override
public String toString() {
	return "Estudiante " + super.getApellido()+" "+ super.getNombre()+"[curso=" + curso + ", estudios=" + estudios + "]";
}
public int getCurso() {
	return curso;
}
public void setCurso(int curso) {
	this.curso = curso;
}
public String getEstudios() {
	return estudios;
}
public void setEstudios(String estudios) {
	this.estudios = estudios;
}
public Estudiante(String nombre, String apellido, String estudios, int curso) {
	super(nombre, apellido);
	this.curso = curso;
	this.estudios = estudios;
}
}
