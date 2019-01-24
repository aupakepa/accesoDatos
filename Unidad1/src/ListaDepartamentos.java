import java.io.Serializable;
import java.util.ArrayList;

public class ListaDepartamentos extends ArrayList<Departamentos> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Departamentos> dep;

	public ListaDepartamentos(ArrayList<Departamentos> dep) {
		super();
		this.dep =  new ArrayList<Departamentos>();
	}

	@Override
	public String toString() {
		return "ListaPersonas [dep=" + dep + "]";
	}

	public ArrayList<Departamentos> getDep() {
		return dep;
	}

	public void setDep(ArrayList<Departamentos> dep) {
		this.dep = dep;
	}
	
}
