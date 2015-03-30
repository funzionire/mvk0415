package controller;

import static controller.BeanFactory.getSessionBeanHousehold;
import static controller.BeanFactory.getSessionBeanUser;
import javax.ejb.Stateless;
import model.AppUser;
import model.Household;
import model.Place;

@Stateless
public class ManageBeanUserHousehold implements ManageBeanUserHouseholdLocal {
    
    SessionBeanHouseholdLocal   sessionBeanHousehold = getSessionBeanHousehold();
    SessionBeanUserLocal   sessionBeanUser = getSessionBeanUser();

//------------------------------------------------------------------------------    
// Grundlegende Methoden zur Verwaltung eines Haushaltes
//------------------------------------------------------------------------------   
    @Override
    public Household addHousehold(String name, AppUser user) throws MVKException{
        Household household = sessionBeanHousehold.createHousehold(name);
        sessionBeanHousehold.addUserToHousehold(household, user);
        sessionBeanUser.addHouseholdToUser(user, household);
        return household;
    }

    @Override
    public AppUser removeHousehold(Household household, AppUser user) throws MVKException{
        household = sessionBeanHousehold.removeUserFromHousehold(household, user);
        user = sessionBeanUser.removeHouseholdFromUser(user, household);
        if(household.getAppUserList().isEmpty()){
            sessionBeanHousehold.deleteHousehold(household);
        } 
        return user;
    }

    @Override
    public Place addPlace(String name, Household household) throws MVKException{
        Place place = sessionBeanHousehold.createPlace(name, household);
        sessionBeanHousehold.addPlaceToHousehold(household, place);
        return place;
    }
    
    @Override
    public void shareHousehold(Household household, String email) throws MVKException{
       if(household != null & email != null){
            AppUser user = sessionBeanUser.findUser(email);
            sessionBeanHousehold.addUserToHousehold(household, user);
            sessionBeanUser.addHouseholdToUser(user, household);
            }
    }
//------------------------------------------------------------------------------    
// Household
//------------------------------------------------------------------------------    
    
    @Override
    public Household changeHousehold(Household household, String name) throws MVKException{
        return sessionBeanHousehold.changeHousehold(household, name);
    }

    @Override
    public Household findHousehold(String stringID) throws MVKException{
        long longID = Long.parseLong(stringID);
        return sessionBeanHousehold.findHousehold(longID);
    }
//------------------------------------------------------------------------------
// Place   
//------------------------------------------------------------------------------    
    
    @Override
    public Place changePlace(Place place, String newName) throws MVKException{
        return sessionBeanHousehold.changePlace(place, newName);
    }
    
    @Override
    public Place findPlace(String stringID) throws MVKException{
        long longID = Long.parseLong(stringID);
        return sessionBeanHousehold.findPlace(longID);
    }
//------------------------------------------------------------------------------    
// User-Administration 
//------------------------------------------------------------------------------    

    @Override
    public AppUser createUser(String name, String email, String password) throws MVKException{
        return sessionBeanUser.createUser(name, email, password);
    }

    @Override
    public AppUser login(String email, String password) throws MVKException{
        return sessionBeanUser.login(email, password);
    }

    @Override
    public boolean deleteUser(AppUser user) throws MVKException{
        return sessionBeanUser.deleteUser(user);
    }

    @Override
    public AppUser changeName(AppUser user, String name) throws MVKException{
        return sessionBeanUser.changeName(user, name);
    }

    @Override
    public AppUser changePassword(AppUser user, String password) throws MVKException{
        return sessionBeanUser.changePassword(user, password);
    }

    @Override
    public AppUser changeEmail(AppUser user, String email) throws MVKException{
        return sessionBeanUser.changeEmail(user, email);
    } 
    
    @Override
    public AppUser findUser(long longID) throws MVKException{
        return sessionBeanUser.findUser(longID);
    }

    @Override
    public AppUser findUser(String email) throws MVKException{
        return sessionBeanUser.findUser(email);
    }  
    
}
