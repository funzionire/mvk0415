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
    private static int id = 0;
    private String name;
    private int householdReferenceId;

    public Place(String name, int householdReferenceId) {
        id = id++;
        this.name = name;
        this.householdReferenceId = householdReferenceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public int gethouseholdReferenceId() {
        return householdReferenceId;
    }

    public void sethouseholdReferenceId(int householdReferenceId) {
        this.householdReferenceId = householdReferenceId;
    }
    
    
}
