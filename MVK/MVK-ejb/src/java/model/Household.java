/*
Entity-Klasse "Household" stellt die Entity Household - Haushalt dar 
und das damit verbundene Household Objekt in der Datenbank.
*/
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Household.findById", query = "SELECT household FROM Household household WHERE household.householdID = :householdID")
        })
public class Household implements Serializable {

    //Attribute
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long householdID;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Place> places;
    @ManyToMany
    private List<AppUser> users;

    //Konstruktoren
    public Household() {
    }

    public Household(String name) {
        users = new ArrayList();
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
    
    public List<Place> getPlaceList(){
        return places;
    }
    
    public List<AppUser> getAppUserList(){
        return users;
    }
}
