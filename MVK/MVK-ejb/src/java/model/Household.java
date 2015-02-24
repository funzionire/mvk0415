/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author baader
 */
public class Household {
    private String hhId;
    private String name;
    

    public Household(String name, String uId, int haushalte) {
        this.name = name;
        hhId = uId+haushalte;
    }

    public String getHhId() {
        return hhId;
    }

    public void setHhId(String hhId) {
        this.hhId = hhId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*
    public Place addPlace(String name){
        return new Place(name, hhId);
    }    
    */
}
