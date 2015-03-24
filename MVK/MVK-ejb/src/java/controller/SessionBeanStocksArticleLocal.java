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
public interface SessionBeanStocksArticleLocal {

//------------------------------------------------------------------------------
    //StocksArticle
//------------------------------------------------------------------------------
    public StocksArticle createStocksArticle(String nameArt, Place place, String commentArt);
    
    public void deleteStocksArticle(StocksArticle stocksArticle);
    
    public StocksArticle changeStocksArticle(StocksArticle stocksArticle, String nameArt, String commentArt);
    
    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace);
    
    public StocksArticle findStocksArticle(long longID);
    
//-->TODO (in dieser bean?) addUnit, removeUnit, changeUnit, moveUnit,

//------------------------------------------------------------------------------
    //StocksUnit
//------------------------------------------------------------------------------
    public StocksUnit createStocksUnit(int quantity, Date mdd, String commentSUnit);
    
    public StocksUnit findStocksUnit(long longID);
    
    /*--> kann entfernt werden wenn geklärt!
    public StocksUnit addUnit(int quantity);
    public StocksUnit addUnit(int quantity, Date mdd);
    public StocksUnit addUnit(int quantity, String commentSUnit);
    public StocksUnit addUnit(Date mdd);
    public StocksUnit addUnit(Date mdd, String commentSUnit);
    public StocksUnit addUnit(String commentSUnit);
    */
    //fragt sich ob man das so will.../notwendig (Abfrage == null?) -->Ausprobieren
    
    
    //??? int quantity nur über plus/minus-Buttons!
    public StocksUnit changeStocksUnit(StocksUnit stocksUnit, Date mdd, String commentSUnit, int updateQuantity);
    
    /*--> kann entfernt werden wenn geklärt!
    public StocksUnit changeUnit(Date mdd);
    public StocksUnit changeUnit(Date mdd, String commentSUnit);
    public StocksUnit changeUnit(String commentSUnit);
    public StocksUnit changeUnit(int quantity);
    public StocksUnit changeUnit(int quantity, Date mdd);
    public StocksUnit changeUnit(int quantity, String commentSUnit);
    */
    
    public void deleteStocksUnit(StocksUnit stocksUnit);   

//------------------------------------------------------------------------------
    //Beziehungen
//------------------------------------------------------------------------------
        //??? boolean?
    public boolean addStocksUnitToStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit);
    
    public boolean removeStocksUnitFromStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit);
    
    public boolean addStocksArticleToPlace(Place place, StocksArticle stocksArticle);
    
    public boolean removeStocksArticleFromPlace(Place place, StocksArticle stocksArticle);
    
    public boolean changePlaceFromStocksArticle(Place place, StocksArticle stocksArticle);
    
    public StocksUnit changeQuantityOfStocksUnit(StocksUnit stocksUnit, int change);       
    
}
