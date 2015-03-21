/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Local;
import model.AppUser;
import model.Household;
import model.Place;
import model.StocksArticle;

/**
 *
 * @author Felix
 */
@Local
public interface ManageBeanUserHouseholdLocal {
//------------------------------------------------------------------------------    
    //Grundlegende Methoden zur Verwaltung eines Haushaltes
//------------------------------------------------------------------------------   
    
    public Household addHousehold(String name, AppUser user);
    
    public void removeHousehold(Household household, AppUser user);
    
    public Place addPlace(String name, Household household);
            
    public void shareHousehold(Household household, String email);
    
//------------------------------------------------------------------------------      
    //"Wrapper-Methoden" (mit eventuellen Datentyp-Umwandlungen)
//------------------------------------------------------------------------------    
        //-->Household
    public Household changeHousehold(Household household, String name);
    
    public Household findHousehold(String stringID);
    
//------------------------------------------------------------------------------    
        //-->Place   
    public Place changePlace(Place place, String newName);
    
    public Place findPlace(String stringID);
    
//------------------------------------------------------------------------------
        //-->User-Administration    
    public AppUser createUser(String name, String email, String password);
    
    public AppUser login(String email, String password);
    
    public void deleteUser (AppUser user);
    
    public AppUser changeName (AppUser user, String name);
    
    public AppUser changePassword (AppUser user, String password);
    
    public AppUser changeEmail (AppUser user, String email);
    
    //public AppUser changeUser (AppUser user, String name, String email, String password);
    
    public AppUser findUser(long longID);
    
    public AppUser findUser(String email);
}
