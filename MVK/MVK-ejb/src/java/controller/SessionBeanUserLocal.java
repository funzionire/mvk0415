/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Local;
import model.AppUser;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanUserLocal {
    
    public AppUser createUser(String name, String email, String password);
    //|->soll einen User mit den Parametern name, email und passward erstellen
    
    public AppUser login(String email, String password);
    //|->soll einen User authentifizieren und für den Kontext zur Verfügung stellen
    
    public boolean deleteUser (AppUser user);
    //|->soll ein user-Objekt löschen
    
    public AppUser changeUser (AppUser user, String name, String email, String password);
//-->soll dem Benutzer die Möglichkeit geben, eins oder mehrere seiner Eigenschaften zu ändern
//-->mehrere Methoden (?): changePassword, changeName, changeEmail
    
    public AppUser changeName (AppUser user, String name);
    
    public AppUser changePassword (AppUser user, String password);
    
    public AppUser changeEmail (AppUser user, String email);

}
