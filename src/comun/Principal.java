package comun;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import usuario.modelo.Usuario;

public class Principal 
{	
    public static void main( String[] args )
    {
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	
    	Usuario usuario = new Usuario();    
    	Session session;
    	
    	/** select **/    	
    	session = sessionFactory.openSession();
    	session.beginTransaction();
    	Usuario usuario2 = (Usuario) session.get(Usuario.class, 0);
    	System.out.println(usuario2);
    	session.close();
    	
    	/** insert **/
    	session = sessionFactory.openSession();
    	session.beginTransaction();
    	int count = ((Long)session.createQuery("select count(*) from usuario.modelo.Usuario").uniqueResult()).intValue();
    	session.getTransaction().commit();
    	session.close();
    	System.out.println(count);
    	usuario.setUsuarioId(count);
    	usuario.setUsuarioNombre("Alfred");
    	usuario.setUsuarioApellido("Hitch");
    	System.out.println(usuario);
    	session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(usuario);
    	session.getTransaction().commit();
    	session.close();
    	System.out.println("guardado");
    	
    	/** update **/
    	session = sessionFactory.openSession();
    	session.beginTransaction();
    	usuario2.setUsuarioNombre("Paco");
    	session.update(usuario2);
    	session.getTransaction().commit();
    	session.close();
    	
    	/** delete **/
    	session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.delete(usuario2);
    	session.getTransaction().commit();
    	session.close();
    	
    	System.out.println("Hecho");
    }

	

	
}
