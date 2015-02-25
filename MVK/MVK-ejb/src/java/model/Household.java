/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author baader
 */
public class Household {
    private String householdID;
    private String name;
    private int places = 0;
    

    public Household(String name, String userID, int haushalte) {
        this.name = name;
        householdID = userID+haushalte+"h";
    }

    public String getHouseholdID() {
        return householdID;
    }

    public void setHouseholdID(String householdID) {
        this.householdID = householdID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Place addPlace(String name, String HouseholdID){
        return new Place(name, householdID, places++);
    }    
    
}
