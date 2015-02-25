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
    public Household changeHousehold(long householdID, String name) {
        em.setFlushMode(FlushModeType.AUTO);
        Household household = getHousehold(householdID);
        if (name != null) {
            household.setName(name);
        }
        em.persist(household);
        household = em.merge(household);
        em.flush();
        return household;
    }
    
    @Override
    public Household addUserToHousehold(long householdID, User user) {
        em.setFlushMode(FlushModeType.AUTO);
        Household household = getHousehold(householdID);
        //???weitere Prüfung notwendig
        if (user != null) {
            household.addUser(user);
        }
        em.persist(household);
        household = em.merge(household);
        em.flush();
        return household;
    }

    @Override
    public Household removeUserFromHousehold(long householdID, User user) {
        em.setFlushMode(FlushModeType.AUTO);
        Household household = getHousehold(householdID);
        //???weitere Prüfung notwendig
        if (user != null) {
            household.removeUser(user);
        }
        em.persist(household);
        household = em.merge(household);
        em.flush();
        return household;
    }

    @Override
    public Household deleteHousehold(long householdID) {
        em.setFlushMode(FlushModeType.AUTO);
        Household household = getHousehold(householdID);
        em.remove(household);
        em.flush();
        return household;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    //TODO
    private Household getHousehold(long householdID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
