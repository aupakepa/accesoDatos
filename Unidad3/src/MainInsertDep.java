import primero.*;

import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class MainInsertDep {
	public static void main(String[] args) {

		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		//crear la sesion
		Session session = sesion.openSession();
		//crear una transaccion de la sesion
		Transaction tx = session.beginTransaction();

		System.out.println("Inserto una fila en la tabla DEPARTAMENTOS.");

		Departamentos dep = new Departamentos();
		dep.setDeptNo((byte) 57);
		dep.setDnombre("MARKETING");
		dep.setLoc("GUADALAJARA");

		session.save(dep);
		try {
			tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTO DUPLICADO");
			System.out.printf("MENSAJE: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());		
			System.out.printf("ERROR SQL: %s%n" , 
                  e.getSQLException().getMessage());
		}

		session.close();
		System.exit(0);
	}
}
