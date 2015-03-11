/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Local;
import model.Household;
import model.AppUser;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanHouseholdLocal {

    //??? braucht es hier einen User in dieser Methode oder wird nicht einfach die UserID innerhalb der Methode rausgezogen?
    //--> so lassen

    public Household createHousehold(String name, AppUser user);
    //|->soll einen neuen Haushalt anlegen, dem der Benutzer einen Namen geben muss
    //|->der neue Haushalt soll in die households Liste des user-Objekts aufgenommen werden
    //|->der user soll in die users Liste des household-Objekts aufgenommen werden

    public Household changeHousehold(Household household, String name);
    //|->soll den Namen des households ändern

    //??? kann mehrere User gleichzeitig hinzufügen? Ist eig. jeweils extra..immer mit einem plus wird diese Methode ausgeführt..
    //Validierung des Users wird sicher auch nicht ganz einfach.
    public boolean addUserToHousehold(Household household, AppUser user);
    //|->soll einen user in die users-Liste eines household-Objekts hinzufügen
    //|->user soll anhand seiner registrierten email-adresse gefunden werden 
//--> TODO controller servlet, das user-Objekt anhand der email-adresse findet und der Methode übergibt

    //delete oder remove??
    //--> remove
    public boolean removeUserFromHousehold(Household household, AppUser user);
    //|->soll ein user-Objekt aus der users-Liste des household-Objekts entfernen
//-->könnte man ausprogrammieren oder auch nicht    

    public boolean deleteHousehold(Household household);
    //|->soll den Haushalt und alles was "in Ihm" ist löschen
    //|->siehe auch Household.removePlace

    public List<Household> getHouseholdsForUser(AppUser user);
}
