package controller;

import java.util.List;
import javax.ejb.Local;
import model.Household;
import model.AppUser;
import model.Place;
import model.StocksArticle;


@Local
public interface SessionBeanHouseholdLocal{
    
//------------------------------------------------------------------------------
    //Household
//------------------------------------------------------------------------------
    public Household createHousehold(String name) throws MVKException;
        
    public void deleteHousehold(Household household) throws MVKException;
    
    public Household changeHousehold(Household household, String newName) throws MVKException;
    
    public Household findHousehold(long longID) throws MVKException;

//------------------------------------------------------------------------------
    //Place
//------------------------------------------------------------------------------
    public Place createPlace(String name, Household household) throws MVKException;
    
    public void deletePlace(Place place) throws MVKException;
    
    public Place findPlace(long longID) throws MVKException;

//------------------------------------------------------------------------------
    //Verknüpfung User_Household
//------------------------------------------------------------------------------
    public boolean addUserToHousehold(Household household, AppUser user) throws MVKException;
    
    public Household removeUserFromHousehold(Household household, AppUser user) throws MVKException;

//------------------------------------------------------------------------------
    //Verknüpfung Household_Place
//------------------------------------------------------------------------------
    public boolean addPlaceToHousehold(Household household, Place place) throws MVKException;
    
//------------------------------------------------------------------------------
    //Verknüpfung Place_StocksArticle
//------------------------------------------------------------------------------
    public boolean addStocksArticleToPlace(Place place, StocksArticle stocksArticle) throws MVKException;
    
    public boolean removeStocksArticleFromPlace(Place place, StocksArticle stocksArticle) throws MVKException;
    
    public List<Household> getHouseholdsForUser(AppUser user) throws MVKException;
}
