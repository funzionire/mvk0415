/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author baader
 */
@Entity
public class Place {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long placeID;
    @ManyToOne
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

    public boolean createStocksArticle(String nameArt, Place place){
        return stocksArticles.add(new StocksArticle(nameArt, place));
    }
    
        public boolean createStocksArticle(String nameArt, Place place, String commentArt){
        return stocksArticles.add(new StocksArticle(nameArt, place, commentArt));
    }
}
