import java.io.Serializable;
import java.util.ArrayList;

public class ListaDepartamentos extends ArrayList<Departamentos> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ArrayList<Departamentos> departamentos;

	
	public ListaDepartamentos(ArrayList<Departamentos> dep) {
		super();
		this.departamentos = dep;
	}

	@Override
	public String toString() {
		return "ListaPersonas [dep=" + departamentos + "]";
	}

	public ArrayList<Departamentos> getDep() {
		return departamentos;
	}

	public void setDep(ArrayList<Departamentos> dep) {
		this.departamentos = dep;
	}
	
}
