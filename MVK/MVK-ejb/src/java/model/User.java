/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 
 * @author Steffen
 */

@Entity
@NamedQueries(
{
    @NamedQuery(name = "User.findById", query = "SELECT user FROM User user WHERE user.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT user FROM User user WHERE user.email = :email"),
    @NamedQuery(name = "User.findByEmailPassword", query = "SELECT user FROM User user WHERE user.email = :email AND user.password = :password")
})
public class User implements Serializable{
        
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private final String userID;
    private static int id=0;
    private int households;
    private String name;
    private String email;
    private String password;
    
    
    
    public User (String name, String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
        userID = formatU(id++);
        households = 0;
    }
    
    public Household createHousehold(String hhName, String userID){
        return new Household(hhName, userID, households++);
    }
    
    public Household addHousehold(String id){
        //TODO
        //Ein bestehender Haushalt soll dem Benutzer zugeordnet werden
        return null;
    }
    
    public Household deleteHousehold (String id){
        //TODO
        //Ein zugeordneter Haushalt soll gelöscht werden wenn der Benutzer Besitzer des Haushaltes ist
        //die zuordnung soll gelöscht werden, wenn der Benutzer nicht Besitzer des haushaltes ist
        return null;
    }

    public static int getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public String formatU(int id) { 
        return String.valueOf(id)+"u"; 
    }
    
}
