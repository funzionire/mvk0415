package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;

@Local
public interface ManageBeanStocksLocal {

//------------------------------------------------------------------------------
// StocksArticle
//------------------------------------------------------------------------------
    public StocksArticle addStocksArticle(String nameArt, Place place, String commentArt) throws MVKException;
    
    public boolean removeStocksArticle(StocksArticle stocksArticle) throws MVKException;
    
    public StocksArticle findStocksArticle(String stringID) throws MVKException;
    
    public int proofeMdd (StocksArticle stocksArticle) throws MVKException;
    
    public int sumUpQuantity (StocksArticle stocksArticle) throws MVKException;
//------------------------------------------------------------------------------    
    //-->StocksUnit
//------------------------------------------------------------------------------
    public StocksUnit addStocksUnit(StocksArticle stocksArticle, String quantity, String mdd, String commentSUnit) throws MVKException;
    
    public boolean removeStocksUnit(StocksUnit stocksUnit) throws MVKException;
    
    public StocksUnit findStocksUnit(String stringID) throws MVKException;
    
        //-->Veränderungen an der Quantity eines StocksUnit
    public StocksUnit raiseQuantityOfStocksUnit(StocksUnit stocksUnit) throws MVKException;
    
    public StocksUnit reduceQuantityOfStocksUnit(StocksUnit stocksUnit) throws MVKException;
    
    
    

    
}
