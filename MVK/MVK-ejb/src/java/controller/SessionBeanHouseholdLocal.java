/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Local;
import model.Household;
import model.User;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanHouseholdLocal {
    //??? braucht es hier einen User in dieser Methode oder wird nicht einfach die UserID innerhalb der Methode rausgezogen?
    public Household createHousehold(String name, User user);
    //??? Nutzer wieder herauslöschen müssen wir auch nochmals betrachten, kann nicht einfach unter changeHousehold gemacht werden
    public Household changeHousehold(long householdID, String name);
    
    //??? kann mehrere User gleichzeitig hinzufügen? Ist eig. jeweils extra..immer mit einem plus wird diese Methode ausgeführt..
    //Validierung des Users wird sicher auch nicht ganz einfach.
    public Household addUserToHousehold(long householdID, User user);
    
    //delete oder remove??
    public Household removeUserFromHousehold(long householdID, User user);
    
    public Household deleteHousehold(long householdID);
    
}
