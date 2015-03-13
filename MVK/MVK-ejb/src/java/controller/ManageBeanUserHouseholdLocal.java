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
    
    //Über-Methoden (--> rufen andere Methoden auf)
    
    public Household addHousehold(String name, AppUser user);
    
    public void removeHousehold(Household household, AppUser user);
    
    public void addPlace(String name, Household household);
      
    //Rapper-Methoden
    
        //Household
    public Household changeHousehold(Household household, String name);
    
        //Place   
    public Place changePlace(Place place, String newName);
    

        //User-Administration    
    public AppUser createUser(String name, String email, String password);
    
    public AppUser login(String email, String password);
    
    public boolean deleteUser (AppUser user);
    
    public AppUser changeName (AppUser user, String name);
    
    public AppUser changePassword (AppUser user, String password);
    
    public AppUser changeEmail (AppUser user, String email);
    
    //public AppUser changeUser (AppUser user, String name, String email, String password);
}
