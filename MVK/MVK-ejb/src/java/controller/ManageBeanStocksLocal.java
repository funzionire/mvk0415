/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;

/**
 *
 * @author Felix
 */
@Local
public interface ManageBeanStocksLocal {
    
    //void oder boolean??
    //StocksArticle
    public void addStocksArticle(String nameArt, Place place, String commentArt);
    
    public void removeStocksArticle(StocksArticle stocksArticle);
    
    //StocksUnit
    public void addStocksUnit(StocksArticle stocksArticle, int quantity, Date mdd, String commentSUnit);
    
    public void removeStocksUnit(StocksUnit stocksUnit);
    
    //move-Methoden
    public void moveStocksUnit(StocksUnit stocksUnit, Place newPlace, int newQuantity);
    
    public void movePlace(StocksArticle stocksArticle, Place newPlace);
    
    
    

    
}
