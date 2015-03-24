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
    
    //???void oder boolean??
//------------------------------------------------------------------------------
    //-->StocksArticle
//------------------------------------------------------------------------------
    public StocksArticle addStocksArticle(String nameArt, Place place, String commentArt);
    
    public boolean removeStocksArticle(StocksArticle stocksArticle);
    
    public StocksArticle findStocksArticle(String stringID);
    
    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace);
//------------------------------------------------------------------------------    
    //-->StocksUnit
//------------------------------------------------------------------------------
    public StocksUnit addStocksUnit(StocksArticle stocksArticle, String quantity, String mdd, String commentSUnit);
    
    public boolean removeStocksUnit(StocksUnit stocksUnit);
    
    public boolean moveStocksUnit(StocksUnit stocksUnit, Place newPlace, int newQuantity);
    
    public StocksUnit findStocksUnit(String stringID);
  
        //-->Veränderungen an der Quantity eines StocksUnit
    public StocksUnit raiseQuantityOfStocksUnit(StocksUnit stocksUnit);
    
    public StocksUnit reduceQuantityOfStocksUnit(StocksUnit stocksUnit);
    
    
    

    
}
