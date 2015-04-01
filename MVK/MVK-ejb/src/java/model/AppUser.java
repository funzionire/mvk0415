/*
Entity-Klasse "AppUser" stellt die Entity User - Anwender
und das damit verbundene User Objekt in der Datenbank dar. 

Aus den Entity-Klassen werden die Tabellen der Datenbank erzeugt.
*/
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQueries(
        {
            @NamedQuery(name = "AppUser.findById", query = "SELECT user FROM AppUser user WHERE user.userID = :userID"),
            @NamedQuery(name = "AppUser.findByEmail", query = "SELECT user FROM AppUser user WHERE user.email = :email"),
            @NamedQuery(name = "AppUser.findByEmailPassword", query = "SELECT user FROM AppUser user WHERE user.email = :email AND user.password = :password")
        })
public class AppUser implements Serializable {

    //Attribute
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Household> households;

    //Konstruktoren
    public AppUser() {
    }

    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
  
    //Getter und Setter
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
    
    public List<Household> getHouseholdList(){
        return households;
    }
}
