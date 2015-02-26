/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import model.Household;
import model.User;

/**
 *
 * @author Felix
 */
@Stateless
public class SessionBeanHousehold implements SessionBeanHouseholdLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Household createHousehold(String name, User user) {
        em.setFlushMode(FlushModeType.AUTO);
        Household household = new Household(name, user);
        em.persist(household);
        household = em.merge(household);
        em.flush();
        return household;
    }

    @Override
    public Household changeHousehold(Household household, String name) {
        em.setFlushMode(FlushModeType.AUTO);
        if (name != null) {
            household.setName(name);
        }
        em.persist(household);
        household = em.merge(household);
        em.flush();
        return household;
    }

    @Override
    public boolean addUserToHousehold(Household household, User user) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            //???weitere Prüfung notwendig
            if (user != null) {
                household.addUser(user);
            }
            em.persist(household);
            household = em.merge(household);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeUserFromHousehold(Household household, User user) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (user != null) {
                household.removeUser(user);
            }
            em.persist(household);
            household = em.merge(household);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteHousehold(Household household) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            //gemockt--------------------
            String mockup = "mockup";
            household.removePlace(mockup);
            //----------------------------
            em.remove(household);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /*unnötig
     private Household getHousehold(long householdID) {
     return em.find(Household.class, householdID);
     //throw new UnsupportedOperationException("Not supported yet."); 
     //To change body of generated methods, choose Tools | Templates.
     }*/
}
