/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author baader
 */
@Entity
public class Place implements Serializable{
    //Attribute
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long placeID;
    @ManyToOne
    private Household household;
    @OneToMany
    private List<StocksArticle> stocksArticles;

    //Konstruktoren
    
    public Place() {
        //TODO
    }

    //???wird hier Household bebraucht?
    public Place(String name, Household household) {
        this.name = name;
        this.household=household;
    }

    //Getter und Setter
    public long getPlaceID() {
        return placeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
    
    public List<StocksArticle> getStocksArticleList(){
        return stocksArticles;
    }
}
