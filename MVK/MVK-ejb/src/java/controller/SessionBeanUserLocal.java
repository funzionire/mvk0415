package controller;

import javax.ejb.Local;
import model.AppUser;
import model.Household;

@Local
public interface SessionBeanUserLocal {

//------------------------------------------------------------------------------
// User -->Grundlegende Methoden
//------------------------------------------------------------------------------
    public AppUser createUser(String name, String email, String password) throws MVKException;
    
    public AppUser login(String email, String password) throws MVKException;
    
    public boolean deleteUser (AppUser user) throws MVKException;
    
    public AppUser findUser(long longID) throws MVKException;
    
    public AppUser findUser(String email) throws MVKException;
    
//------------------------------------------------------------------------------
// Beziehungen
//------------------------------------------------------------------------------
    public boolean addHouseholdToUser(AppUser user, Household household) throws MVKException;
    
    public AppUser removeHouseholdFromUser(AppUser user, Household household) throws MVKException;
    
//------------------------------------------------------------------------------
// Change-Methoden
//------------------------------------------------------------------------------
    public AppUser changeName (AppUser user, String name) throws MVKException;
    
    public AppUser changePassword (AppUser user, String password) throws MVKException;
    
    public AppUser changeEmail (AppUser user, String email) throws MVKException;
}
