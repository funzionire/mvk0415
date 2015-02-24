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
    
    private String uId;
    private static int id=0;
    private int households;
    private String name;
    private String email;
    private String password;
    
    
    
    public User (String name, String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
        uId = formatZ(id++);
        households = 0;
    }
    
    public void createHousehold(String hhName, String uId){
        new Household(hhName, uId, households++);
    }
    
    public void addHousehold(int id){
        //TODO
    }
    
    public void deleteHousehold (int id){
        //TODO
    }

    public static int getId() {
        return id;
    }

    public String getUId() {
        return uId;
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
   
        public String formatZ(int value) { 
        String result = String.valueOf(value); 
        return result+"z"; 
    }
    
}
