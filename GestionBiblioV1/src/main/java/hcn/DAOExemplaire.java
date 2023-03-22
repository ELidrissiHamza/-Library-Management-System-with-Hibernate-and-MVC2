package hcn;

import java.util.List;
import java.util.Vector;

import beans.Exemplaire;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 *
 * @author S USER
 */
public class DAOExemplaire {

    public boolean create(Exemplaire D) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(D);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            System.out.println("erreur exp: "+ e);
            tx.rollback();
            return false;
        }

    }
    public Vector<Exemplaire> retreive() {

        List<Exemplaire> livre = new  Vector<Exemplaire>();
        Transaction tx=null;
        try{
            Session session=HibernateUtil.getsessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            Query query = session.createQuery("from Exemplaire");
            for(final Object o : query.list()) livre.add((Exemplaire)o);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            System.out.println("error loading: "+e);
        }
        return (Vector<Exemplaire>)livre;

    }

    public boolean delete(Exemplaire L) {
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

    public Long countIsbn(String isbn){
        Transaction tx = null;
        Long count;
        try
        {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(*) FROM Exemplaire e JOIN e.livre l WHERE l.isbn = :isbnValue");
            query.setParameter("isbnValue", isbn);
            count = (Long) query.uniqueResult();

            tx.commit();
            return count;
        }
        catch(HibernateException e)
        {
            System.out.println("here : "+e);
            tx.rollback();
            return null;
        }
    }
    public Long countExpEmprunt(String isbn){
        Transaction tx = null;
        Long count;
        try
        {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(*) FROM Exemplaire e JOIN e.livre l JOIN e.emprunts m WHERE m.retourne=0 AND l.isbn = :isbnValue");
            query.setParameter("isbnValue", isbn);
            count = (Long) query.uniqueResult();

            tx.commit();
            return count;
        }
        catch(HibernateException e)
        {
            System.out.println("here : "+e);
            tx.rollback();
            return null;
        }
    }

    public Vector<Exemplaire> retreiveExpEmprunt(String isbn){
        Transaction tx = null;
        List<Exemplaire> exp = new  Vector<Exemplaire>();
        try
        {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT e FROM Exemplaire e JOIN e.emprunts m WHERE m.retourne=0 AND e.livre.isbn= :isbnValue");
            query.setParameter("isbnValue", isbn);
            for(final Object o : query.list()) exp.add((Exemplaire)o);
            tx.commit();
        }
        catch(HibernateException e)
        {
            System.out.println("here : "+e);
            tx.rollback();
        }
        return (Vector<Exemplaire>)exp;

    }

    public Vector<Exemplaire> retreiveByIsbn(String isbn) {

        List<Exemplaire> exp = new  Vector<Exemplaire>();
        Transaction tx=null;
        try{
            Session session=HibernateUtil.getsessionFactory().getCurrentSession();
            tx=session.beginTransaction();

            Query query = session.createQuery("SELECT e FROM Exemplaire e JOIN e.livre l WHERE l.isbn = :isbnValue");
            query.setParameter("isbnValue", isbn);
            for(final Object o : query.list()) exp.add((Exemplaire)o);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            System.out.println("error loading Exemplaires with isbn :"+isbn+" \n"+e);
        }
        return (Vector<Exemplaire>)exp;
    }
    public Exemplaire getAvailableExp(String isbn){

        List<Exemplaire> expEmrunte=retreiveExpEmprunt(isbn);
        List<Exemplaire> allExp=retreiveByIsbn(isbn);

        for(Exemplaire e: allExp){
            if(!expEmrunte.contains(e)) return e;
        }

        return null;
    }

    public String getIsbn(int exp){
        Transaction tx = null;
        Exemplaire e=null;
        String isbn="";
        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            e=(Exemplaire) session.load(Exemplaire.class, exp);
            //  session.saveOrUpdate(L);
            isbn=e.getLivre().getIsbn();
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            return null;
        }
        return isbn;
    }
}


