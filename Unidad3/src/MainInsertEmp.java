import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import primero.Departamentos;
import primero.Empleados;
import primero.HibernateUtil;

public class MainInsertEmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// obtener la sesion actual
		Departamentos dep = new Departamentos();
		dep.setDeptNo((byte) 57);
		dep.setDnombre("MARKETING");
		dep.setLoc("GUADALAJARA");
		SessionFactory sesion = HibernateUtil.getSessionFactory();

		// crear la sesion
		Session session = sesion.openSession();
		// crear una transaccion de la sesion
		Transaction tx = session.beginTransaction();

		System.out.println("Inserto una fila en la tabla DEPARTAMENTOS.");

		Empleados emple = new Empleados();
		emple.setApellido("Perez");
		emple.setComision(15.5f);
		emple.setDepartamentos(dep);
		emple.setDir((short) 1);
		emple.setEmpNo((short)3);
		java.util.Date hoy = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(hoy.getTime());
		emple.setFechaAlt(fecha);
		emple.setOficio("infor");
		emple.setSalario(1500f);
		

		session.save(emple);
		try {
			tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTO DUPLICADO");
			System.out.printf("nn    MENSAJE: %s%n", e.getMessage());
			System.out.printf("COD ERROR: %d%n", e.getErrorCode());
			System.out.printf("ERROR SQL: %s%n", e.getSQLException().getMessage());
		}

		session.close();
		System.exit(0);
	}
}
