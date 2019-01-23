package primero;


import org.hibernate.*;


/**
 * @author Javier Monforte Taboada
 * 7 dic. 2018
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory session = HibernateUtil.getSessionFactory();
		Session sesion = session.openSession();
		try {

		Transaction tx = sesion.beginTransaction();
		Libro libro = new Libro();
		libro.setCodigoLibro(20);
		libro.setTitulo("La insoportable levedad del ser");
		libro.setIsbn("84-121-2310-2");
		libro.setAutor("Millan Kundera");	
		sesion.update(libro);
		tx.commit();
		}
		
		 catch (javax.persistence.PersistenceException e) {
			System.out.println("Libro duplicado");
			System.out.printf("MENSAJE: %s%n",e.getMessage());
			System.out.println(e.getClass());
			System.out.println(e.getSuppressed());

			
		}catch (Exception e) {
			
			System.out.println(e.getClass());
		}
		sesion.close();
		System.exit(1);
	}

}
