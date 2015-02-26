/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author baader
 */
@Entity

public class Household {

    @Id
    @GeneratedValue
    private long householdID;
    @OneToOne
    private String name;
    @OneToMany
    private List<Place> places;
    @ManyToMany
    private List<User> users;

    public Household(String name, User user) {
        users.add(user);
        this.name = name;
    }

    public long getHouseholdID() {
        return householdID;
    }

    public void setHouseholdID(long householdID) {
        this.householdID = householdID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean addUser(User user){
        return users.add(user);
    }
    
    public boolean removeUser(User user){
        return users.remove(user);
    }
    
     public boolean createPlace(String name, Household household){
     return places.add(new Place(name, household));
     }    
     
     public boolean removePlace(String name){
         //gemockt
         //müsste eigentlich removeArticles aufrufen und removeArticles müsste removeUnits machen
         return true;
     }
}
