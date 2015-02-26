/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Local;
import model.User;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanUserLocal {
    
    public User createUser(String name,String email, String password);
    //|->soll einen User mit den Parametern name, email und passward erstellen
    
    public User login(String email, String password);
    //|->soll einen User authentifizieren und für den Kontext zur Verfügung stellen
    
    public boolean deleteUser (User user);
    //|->soll ein user-Objekt löschen
    
    public User changeUser (User user, String name, String email, String password);
//-->

}
