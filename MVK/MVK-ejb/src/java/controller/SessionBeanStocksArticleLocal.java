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
    
    public StocksArticle changeStocksArticle(StocksArticle stocksArticle, String nameArt, String commentArt);
    
    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace);
    
    public StocksArticle findStocksArticle(long longID);
    

//------------------------------------------------------------------------------
    //StocksUnit
//------------------------------------------------------------------------------
    public StocksUnit createStocksUnit(int quantity, Date mdd, String commentSUnit);
    
    public StocksUnit findStocksUnit(long longID);

    public StocksUnit changeStocksUnit(StocksUnit stocksUnit, Date mdd, String commentSUnit, int updateQuantity);
    
    public void deleteStocksUnit(StocksUnit stocksUnit);   

//------------------------------------------------------------------------------
    //Beziehungen
//------------------------------------------------------------------------------
    
    public boolean addStocksUnitToStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit);
    
    public boolean removeStocksUnitFromStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit);
    
    //doppelt: gibts auch in SessionBeanHousehold
    public boolean addStocksArticleToPlace(Place place, StocksArticle stocksArticle);
    
//    public boolean removeStocksArticleFromPlace(Place place, StocksArticle stocksArticle);
    
    //public boolean changePlaceFromStocksArticle(Place place, StocksArticle stocksArticle);
    
    public StocksUnit changeQuantityOfStocksUnit(StocksUnit stocksUnit, int change);       
    
    public int compareMddWithCurrentDate(StocksUnit stocksUnit);
    
}
