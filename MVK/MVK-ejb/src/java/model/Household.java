/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author baader
 */
@Entity
public class Household implements Serializable {

    //Attribute

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long householdID;
    @OneToMany
    private List<Place> places;
    @ManyToMany
    private List<AppUser> users;

    //Konstruktoren
    public Household() {
        //TODO
    }

    //??? wird hier AppUser gebraucht?
    public Household(String name, AppUser user) {
        users = new ArrayList();
        users.add(user);
        this.name = name;
    }

    //Getter und Setter
    public long getHouseholdID() {
        return householdID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
