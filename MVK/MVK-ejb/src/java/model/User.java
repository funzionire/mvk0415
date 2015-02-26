/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)

    private long userID;
    @OneToOne
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<Household> households;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Household createHousehold(String hhName, User user) {
        Household x = new Household(hhName, user);
        households.add(x);
        return x;
    }

    public boolean addHousehold(Household household) {
        return households.add(household);
    }

    public boolean removeHousehold(Household household) {
        return households.remove(household);
    }

    public long getUserID() {
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
    
}
