package es.cesur.progprojectpok.daos;

import es.cesur.progprojectpok.database.HibernateSessionFactoryUtil;
import es.cesur.progprojectpok.model.User;
import es.cesur.progprojectpok.utils.UtilsFicheros;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class UserDAO {

    public UserDAO() {
    }

    /**
     * Busca un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return El usuario encontrado, o null si no se encuentra.
     */
    public User findById(int id) {
        User user = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
        return user;
    }

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param user El usuario a guardar.
     * @return True si el usuario se ha guardado con éxito, false en caso contrario.
     */
    public Boolean save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Serializable serializable = session.save(user);
        tx1.commit();
        session.close();

        return serializable != null;
    }

/*    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }*/

/*    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }*/

/*    public List<User> findAll() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }*/

    /**
     * Busca un usuario por su nombre de usuario y contraseña.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return El usuario encontrado que coincide con el nombre de usuario y contraseña proporcionados, o null si no se encuentra ninguno.
     */
    public User findByUsernameAndPassword(String username, String password) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM User WHERE username = :username AND password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<User> result = query.list();
            if (!result.isEmpty()) {
                return result.get(0); // Retorna el primer usuario que coincida (debería ser único)
            }
            return null; // Ningún usuario encontrado
        } finally {
            session.close();
        }
    }

}
