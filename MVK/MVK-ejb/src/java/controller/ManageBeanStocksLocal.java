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
    public boolean addStocksArticle(String nameArt, Place place, String commentArt);
    
    public boolean removeStocksArticle(StocksArticle stocksArticle);
    
    //StocksUnit
    public boolean addStocksUnit(StocksArticle stocksArticle, int quantity, Date mdd, String commentSUnit);
    
    public boolean removeStocksUnit(StocksUnit stocksUnit);
    
    //move-Methoden
    public boolean moveStocksUnit(StocksUnit stocksUnit, Place newPlace, int newQuantity);
    
    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace);
    
    //Veränderungen an der Quantity eines StocksUnit
    public boolean raiseQuantityOfStocksUnit(StocksUnit stocksUnit);
    
    public boolean reduceQuantityOfStoksUnit(StocksUnit stocksUnit);
    
    
    

    
}
