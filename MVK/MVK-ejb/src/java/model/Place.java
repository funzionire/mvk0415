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
public class Place {
    private String name;
    private String householdReferenceID;
    private String placeID;

    public Place(String name, String householdID, int places) {
        this.name = name;
        this.householdReferenceID = householdID;
        placeID = householdID+places+"p";
    }

    public String getId() {
        return placeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StocksArticle addStockUnit(){
        return new StocksArticle();
    }
    
    public String gethouseholdReferenceID() {
        return householdReferenceID;
    }

}
