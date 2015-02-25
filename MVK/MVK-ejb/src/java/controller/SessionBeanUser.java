/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
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

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public User createPerson(String name, String email, String password) {
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
    public User deleteUser(Long uid) {
        em.setFlushMode (FlushModeType.AUTO);
        User user = getUser(uid);
        em.remove(user);
        em.flush();
        return user;
    }

    @Override
    public User changeUser(Long uid, String name, String email, String password) {
        em.setFlushMode(FlushModeType.AUTO);
        User user = getUser(uid);
        em.persist(user);
        user = em.merge(user);
        em.flush();
        return user;
    }

    // TODO:
    public User getUser(Long uid){
        return null;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
