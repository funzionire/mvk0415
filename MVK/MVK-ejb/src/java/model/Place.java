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
    private Collection<StocksArticle> stocksArticles;

    public Place(String name) {
        id = id++;
        this.name = name;
        stocksArticles = new ArrayList<StocksArticle>();
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

    public void addStockUnit(){
        StocksArticle stocksArticle = new StocksArticle();
        stocksArticles.add(stocksArticle);
    }
    
    public Collection<StocksArticle> getStocksArticles() {
        return stocksArticles;
    }

    public void setStocksArticles(Collection<StocksArticle> stocksArticles) {
        this.stocksArticles = stocksArticles;
    }
    
    
}
