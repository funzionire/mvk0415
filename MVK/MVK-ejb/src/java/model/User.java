/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * unser User
 * @author Steffen
 */

@Entity
@NamedQueries(
{
    @NamedQuery(name = "User.findById", query = "SELECT user FROM User user WHERE user.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT user FROM User user WHERE user.email = :email"),
    @NamedQuery(name = "User.findByEmailPassword", query = "SELECT user FROM User user WHERE user.email = :email AND user.password = :password")
})

public class User {
    private static int id=1000000;
    private int uid;
    private String name;
    private String email;
    private String password;
    
    
    public User (String name,String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
        uid= id++;
    }
    
    public void createHousehold(){
        //TODO
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

    public static void setId(int id) {
        User.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
   
}
