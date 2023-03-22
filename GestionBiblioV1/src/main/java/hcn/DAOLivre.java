package hcn;

import beans.Livre;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.plaf.IconUIResource;
import java.util.List;
import java.util.Vector;
/**
 *
 * @author S USER
 */
public class DAOLivre  {


    public boolean create(Livre D) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            session.save(D);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            System.out.println("err");
            tx.rollback();
            return false;

        }

    }
    public Vector<Livre> retreive() {

        List<Livre> livre = new  Vector<>();
        Transaction tx=null;
        try{
            Session session=HibernateUtil.getsessionFactory().openSession();
            tx=session.beginTransaction();
            Query query = (Query) session.createQuery("from Livre");
            for(final Object o : query.list()) livre.add((Livre)o);

            tx.commit();
            session.close();
        }catch(HibernateException e){
            tx.rollback();
            System.out.println("error loading: "+e);
        }

        return (Vector<Livre>)livre;

    }

    public boolean update(Livre L) {
        Transaction tx = null;
        String isbn=L.getIsbn(),titre=L.getTitre();
        isbn=isbn.trim();titre=titre.trim();

        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            //L=(Livre) session.load(Livre.class, isbn);
            if(L==null) System.out.println(("null ici"));
            L.setIsbn(isbn);
            System.out.println("trace"+L.getTitre());
            System.out.println("trace"+L.getIsbn());
            session.update(L);
           // L.setTitre(titre);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            tx.rollback();
            return false;

        }
    }
    public boolean delete(Livre L) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(L);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            tx.rollback();
            return false;

        }
    }

    public Livre findByIsbn(String isbn){
        Livre livre =null;
        Transaction tx = null;
        try
        {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            livre = (Livre) session.get(Livre.class, isbn);
            tx.commit();
            return livre;
        }
        catch(HibernateException e)
        {
            tx.rollback();
            return null;
        }
    }

}
