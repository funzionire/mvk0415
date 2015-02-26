/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.User;

/**
 *
 * @author Felix
 */
@Stateless(name = "SessionBeanUser")
public class SessionBeanUser implements SessionBeanUserLocal {
    private static final Logger LOG = Logger.getLogger(SessionBeanUser.class.getName());
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public User createUser(String name, String email, String password) {
        LOG.info("CustomInfo: SessionBean aufgerufe: User anlegen");
        em.setFlushMode(FlushModeType.AUTO);
        User user = new User(name, email, password);
        em.persist(user);
        user = em.merge(user);
        em.flush();
        return user;
        
    }

    @Override
    public User login(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty())
        {
            return null;
        }
        TypedQuery<User> query = em.createNamedQuery("User.findByLoginPasswort", User.class)
            .setParameter("email", email)
            .setParameter("password", password);
        if (query.getResultList().isEmpty())
        {
            return null;
        }
        return query.getSingleResult();
    }
    
    @Override
    public User deleteUser(long userID) {
        em.setFlushMode (FlushModeType.AUTO);
        User user = em.find(User.class, userID);
        em.remove(user);
        em.flush();
        return user;
    }

    @Override
    public User changeUser(long userID, String name, String email, String password) {
        em.setFlushMode(FlushModeType.AUTO);
        User user = em.find(User.class, userID);
        if (name != null) {
        user.setName(name);
        }
        if (email != null) {
        user.setEmail(email);
        }
        if (password != null) {
        user.setPassword(password);
        }
        em.persist(user);
        user = em.merge(user);
        em.flush();
        return user;
    }
        
    /* brauchen wir nicht !?
    public User getUser(long userID){
        User user = em.find(User.class, userID);
        return user;
    }*/
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
