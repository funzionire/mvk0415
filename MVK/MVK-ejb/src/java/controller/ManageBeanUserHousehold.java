/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BeanFactory.getSessionBeanHousehold;
import static controller.BeanFactory.getSessionBeanUser;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.AppUser;
import model.Household;
import model.Place;

/**
 *
 * @author Felix
 */
@Stateless
public class ManageBeanUserHousehold implements ManageBeanUserHouseholdLocal {
    
    SessionBeanHouseholdLocal   sessionBeanHousehold = getSessionBeanHousehold();
    SessionBeanUserLocal   sessionBeanUser = getSessionBeanUser();

    
    
    @Override
    public Household addHousehold(String name, AppUser user) {
        Household household = sessionBeanHousehold.createHousehold(name);
        sessionBeanHousehold.addUserToHousehold(household, user);
        sessionBeanUser.addHouseholdToUser(user, household);
        return household;
    }

    @Override
    public void removeHousehold(Household household, AppUser user) {
        sessionBeanHousehold.removeUserFromHousehold(household, user);
        sessionBeanUser.removeHouseholdFromUser(user, household);
        if(household.getAppUserList()== null){
            sessionBeanHousehold.deleteHousehold(household);
        } 
    }

    @Override
    public Place addPlace(String name, Household household) {
        Place place = sessionBeanHousehold.createPlace(name, household);
        sessionBeanHousehold.addPlaceToHousehold(household, place);
        return place;
    }

    @Override
    public void deleteHousehold(Household household) {
        sessionBeanHousehold.deleteHousehold(household);
    }
    
    @Override
    public Household changeHousehold(Household household, String name) {
        return sessionBeanHousehold.changeHousehold(household, name);
    }

    @Override
    public Household findHousehold(long longID) {
        return sessionBeanHousehold.findHousehold(longID);
    }

    @Override
    public Place findPlace(long longID) {
        return sessionBeanHousehold.findPlace(longID);
    }

    @Override
    public void shareHousehold(Household household, String email) {
       if(household != null & email != null){
            AppUser user = sessionBeanUser.findUser(email);
            sessionBeanHousehold.addUserToHousehold(household, user);
            sessionBeanUser.addHouseholdToUser(user, household);
            }
    }
    
    
    @Override
    public Place changePlace(Place place, String newName) {
        return sessionBeanHousehold.changePlace(place, newName);
    }

    @Override
    public AppUser createUser(String name, String email, String password) {
        return sessionBeanUser.createUser(name, email, password);
    }

    @Override
    public AppUser login(String email, String password) {
        return sessionBeanUser.login(email, password);
    }

    @Override
    public boolean deleteUser(AppUser user) {
        return sessionBeanUser.deleteUser(user);
    }

    @Override
    public AppUser changeName(AppUser user, String name) {
        return sessionBeanUser.changeName(user, name);
    }

    @Override
    public AppUser changePassword(AppUser user, String password) {
        return sessionBeanUser.changePassword(user, password);
    }

    @Override
    public AppUser changeEmail(AppUser user, String email) {
        return sessionBeanUser.changeEmail(user, email);
    } 
    
    /*@Override
    public AppUser changeUser(AppUser user, String name, String email, String password) {
        return sessionBeanUser.changeUser(user, name, email, password);
    }*/
    
    @Override
    public AppUser findUser(long longID) {
        return sessionBeanUser.findUser(longID);
    }

    @Override
    public AppUser findUser(String email) {
        return sessionBeanUser.findUser(email);
    }  
    
}
