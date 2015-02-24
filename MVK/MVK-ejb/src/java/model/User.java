/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;

/**
 * 
 * @author Steffen
 */
public class User {
    private static int id=0;
    private int haushalte;
    private String uId;
    private String name;
    private String email;
    private String password;
    
    
    
    public User (String name, String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
        uId = formatZ(id++);
        haushalte = 0;
    }
    
    public void createHousehold(String hhName, String uId){
        new Household(hhName, uId, haushalte++);
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
