package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.AppUser;
import model.Household;

@Stateless(name = "SessionBeanUser")
public class SessionBeanUser implements SessionBeanUserLocal {

    @PersistenceContext
    private EntityManager em;

//------------------------------------------------------------------------------
// User -->Grundlegende Methoden
//------------------------------------------------------------------------------
    @Override
    public AppUser createUser(String name, String email, String password) throws MVKException{
        try{
            em.setFlushMode(FlushModeType.AUTO);
            AppUser user = new AppUser(name, email, password);
            em.persist(user);
            user = em.merge(user);
            em.flush();
            return user;
        }catch(Exception e){
            throw new MVKException("Fehler beim Anlegen des Users.");
        }
    }

    @Override
    public AppUser login(String email, String password) throws MVKException{
        try{
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                return null;
            }
            TypedQuery<AppUser> query = em.createNamedQuery("AppUser.findByEmailPassword", AppUser.class)
                    .setParameter("email", email)
                    .setParameter("password", password);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            return query.getSingleResult();
        }catch(Exception e){
            throw new MVKException("Fehler beim Einloggen.");
        }
    }

    @Override
    public void deleteUser(AppUser user) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            Household foundPlace = em.find(Household.class, user.getUserID());
            if(foundPlace != null){
                em.remove(foundPlace);
                em.getTransaction().commit();
                em.flush();
            }
        }catch(Exception e){
            throw new MVKException("Fehler beim Löschen des Users.");
        }
    }
    
        @Override
    public AppUser findUser(long longID) throws MVKException{
        try{
            if (longID == 0) {
                return null;
            }
            TypedQuery<AppUser> query = em.createNamedQuery("AppUser.findById", AppUser.class)
                    .setParameter("userID", longID);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            return query.getSingleResult();
        }catch(Exception e){
            throw new MVKException("User nicht gefunden.");
        }
    }
    
    @Override
    public AppUser findUser(String email) throws MVKException{
        try{
            if (email == null) {
                return null;
            }
            TypedQuery<AppUser> query = em.createNamedQuery("AppUser.findByEmail", AppUser.class)
                    .setParameter("email", email);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            return query.getSingleResult();
        }catch(Exception e){
            throw new MVKException("User nicht gefunden.");
        }
    }

    
//------------------------------------------------------------------------------
// Beziehungen
//------------------------------------------------------------------------------
    @Override
    public boolean addHouseholdToUser(AppUser user, Household household) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (household != null) {
                user.getHouseholdList().add(household);
            }
            em.merge(user);
            em.flush();
            return true;
        }catch(Exception e){
            throw new MVKException("Fehler beim Hinzufügen eines Haushalts zum User.");
        }
    }

    @Override
    public AppUser removeHouseholdFromUser(AppUser user, Household household) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (household != null) {
                user = em.find(AppUser.class, user.getUserID());
                user.getHouseholdList().remove(household);
                em.flush();
                em.getEntityManagerFactory().getCache().evictAll();
            }
            return user;
        }catch(Exception e){
            throw new MVKException("Fehler beim Entfernen des Haushalts von einem User.");
        }
    }
    
//------------------------------------------------------------------------------
// Change-Methoden
//------------------------------------------------------------------------------
    @Override
    public AppUser changeName(AppUser user, String name) throws MVKException{
        try{
            em.setFlushMode(FlushModeType.AUTO);
            user.setName(name);
            user = em.merge(user);
            em.flush();
            return user;
        }catch(Exception e){
            throw new MVKException("Fehler beim Ändern des Usernames.");
        }
    }

    @Override
    public AppUser changePassword(AppUser user, String password) throws MVKException{
        try{
            em.setFlushMode(FlushModeType.AUTO);
            user.setPassword(password);
            user = em.merge(user);
            em.flush();
            return user;
        }catch(Exception e){
            throw new MVKException("Fehler beim Ändern des Passworts.");
        }
    }

    @Override
    public AppUser changeEmail(AppUser user, String email) throws MVKException{
        try{
            em.setFlushMode(FlushModeType.AUTO);
            user.setEmail(email);
            user = em.merge(user);
            em.flush();
            return user;
        }catch(Exception e){
            throw new MVKException("Fehler beim Ändern der Email.");
        }
    }
    
}
