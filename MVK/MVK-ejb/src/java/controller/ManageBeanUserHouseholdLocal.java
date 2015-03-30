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
    
    public Household addHousehold(String name, AppUser user) throws MVKException;
    
    public AppUser removeHousehold(Household household, AppUser user) throws MVKException;
    
    public Place addPlace(String name, Household household) throws MVKException;
            
    public void shareHousehold(Household household, String email) throws MVKException;
    
//------------------------------------------------------------------------------      
    //"Wrapper-Methoden" (mit eventuellen Datentyp-Umwandlungen)
//------------------------------------------------------------------------------    
        //-->Household
    public Household changeHousehold(Household household, String name) throws MVKException;
    
    public Household findHousehold(String stringID) throws MVKException;
    
//------------------------------------------------------------------------------    
        //-->Place   
    public Place changePlace(Place place, String newName) throws MVKException;
    
    public Place findPlace(String stringID) throws MVKException;
    
//------------------------------------------------------------------------------
// User-Administration    
    public AppUser createUser(String name, String email, String password) throws MVKException;
    
    public AppUser login(String email, String password) throws MVKException;
    
    public boolean deleteUser (AppUser user) throws MVKException;
    
    public AppUser changeName (AppUser user, String name) throws MVKException;
    
    public AppUser changePassword (AppUser user, String password) throws MVKException;
    
    public AppUser changeEmail (AppUser user, String email) throws MVKException;
    
    //public AppUser changeUser (AppUser user, String name, String email, String password);
    
    public AppUser findUser(long longID) throws MVKException;
    
    public AppUser findUser(String email) throws MVKException;
}
