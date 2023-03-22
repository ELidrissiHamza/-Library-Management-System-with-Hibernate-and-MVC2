package hcn;
import beans.Adherant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class DAOAdherant {


    public boolean create(Adherant A) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            //   session.clear();
            tx = session.beginTransaction();
            session.save(A);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            tx.rollback();
            System.out.println(e);
            return false;

        }
    }


    public boolean update(Adherant ads) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(ads);
            tx.commit();
            //  session.clear();
            return true;
        } catch (HibernateException e) {
            tx.rollback();
            return false;

        }
    }


    public boolean delete(Adherant L) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(L);
            tx.commit();
            // session.clear();

            return true;
        } catch (HibernateException e) {
            tx.rollback();
            return false;

        }    }


    public Vector<Adherant> retreive() {
        List<Adherant> ads = new Vector<>();
        Transaction tx=null;
        try{
            Session session=HibernateUtil.getsessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            Query query = session.createQuery("from Adherant");
            for(final Object o : query.list()) ads.add((Adherant)o);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            System.out.println("error loading");
        }
        return (Vector<Adherant>)ads;
    }

    public Adherant findByCin(String cin){
        Adherant ads =null;
        Transaction tx = null;
        try
        {
            Session session = HibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            ads = (Adherant) session.get(Adherant.class, cin);
            tx.commit();
            return ads;
        }
        catch(HibernateException e)
        {
            tx.rollback();
            return null;
        }
    }
}

