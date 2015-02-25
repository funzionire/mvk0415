/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author baader
 */
@Entity
public class Place {
    @Id
    @GeneratedValue   
    private long placeID;
    @OneToOne
    private String name;
    private Household household;
    @OneToMany
    private List<StocksArticle> stocksArticles;

    public Place(String name, Household household) {
        this.name = name;
        this.household=household;
    }

    public long getPlaceID() {
        return placeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*TODO
    public boolean createStockArticle(StocksArticle stocksArticle){
        return stocksArticles.add(new StocksArticle(stocksArticle));
    }
    */
}
