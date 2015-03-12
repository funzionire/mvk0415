/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Local;
import model.AppUser;
import model.Household;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanUserLocal {
    
    //User -->Grundlegende Methoden
    public AppUser createUser(String name, String email, String password);
    
    public AppUser login(String email, String password);
    
    public boolean deleteUser (AppUser user);
    
    
    //Beziehungen
    public boolean addHouseholdToUser(AppUser user, Household household);
    
    public boolean removeHouseholdToUser(AppUser user, Household household);
    
    
    //Change-Methoden
    public AppUser changeName (AppUser user, String name);
    
    public AppUser changePassword (AppUser user, String password);
    
    public AppUser changeEmail (AppUser user, String email);
    //public AppUser changeUser (AppUser user, String name, String email, String password);
//-->soll dem Benutzer die Möglichkeit geben, eins oder mehrere seiner Eigenschaften zu ändern
//-->mehrere Methoden (?): changePassword, changeName, changeEmail
}
