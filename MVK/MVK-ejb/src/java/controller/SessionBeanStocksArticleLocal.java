package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;


@Local
public interface SessionBeanStocksArticleLocal {

//------------------------------------------------------------------------------
    //StocksArticle
//------------------------------------------------------------------------------
    public StocksArticle createStocksArticle(String nameArt, Place place, String commentArt);
    
    public void deleteStocksArticle(StocksArticle stocksArticle);
    
    public StocksArticle findStocksArticle(long longID);
    

//------------------------------------------------------------------------------
    //StocksUnit
//------------------------------------------------------------------------------
    public StocksUnit createStocksUnit(int quantity, Date mdd, String commentSUnit);
        
    public void deleteStocksUnit(StocksUnit stocksUnit); 
    
    public StocksUnit findStocksUnit(long longID);
  

//------------------------------------------------------------------------------
    //Beziehungen
//------------------------------------------------------------------------------
    
    public boolean addStocksUnitToStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit);
    
    public boolean removeStocksUnitFromStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit);
    
    public StocksUnit changeQuantityOfStocksUnit(StocksUnit stocksUnit, int change);       
    
    public int compareMddWithCurrentDate(StocksUnit stocksUnit);
    
}
