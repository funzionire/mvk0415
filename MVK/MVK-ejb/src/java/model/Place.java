/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;

/**
 *
 * @author baader
 */
public class Place {
    private static int id = 0;
    private String name;
    private Collection<StocksUnit> stocksUnits;

    public Place(int id, String name) {
        id = id++;
        this.name = name;
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

    public Collection<StocksUnit> getStocksUnits() {
        return stocksUnits;
    }

    public void setStocksUnits(Collection<StocksUnit> stocksUnits) {
        this.stocksUnits = stocksUnits;
    }
    
    
}
