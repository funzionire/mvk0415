/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Local;
import model.Household;
import model.AppUser;
import model.Place;
import model.StocksArticle;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanHouseholdLocal {

    //??? braucht es hier einen User in dieser Methode oder wird nicht einfach die UserID innerhalb der Methode rausgezogen?
    //--> so lassen
    
//------------------------------------------------------------------------------
    //Household
//------------------------------------------------------------------------------
    public Household createHousehold(String name);
        
    public void deleteHousehold(Household household);
    
    public Household changeHousehold(Household household, String newName);
    
    public Household findHousehold(long longID);

//------------------------------------------------------------------------------
    //Place
//------------------------------------------------------------------------------
    public Place createPlace(String name, Household household);
    
    public void deletePlace(Place place);
    
    public Place changePlace(Place place, String newName);
    
    public Place findPlace(long longID);

//------------------------------------------------------------------------------
    //Verknüpfung User_Household
//------------------------------------------------------------------------------
    public boolean addUserToHousehold(Household household, AppUser user);
    
    public Household removeUserFromHousehold(Household household, AppUser user);

//------------------------------------------------------------------------------
    //Verknüpfung Household_Place
//------------------------------------------------------------------------------
    public boolean addPlaceToHousehold(Household household, Place place);
    
    public boolean removePlaceFromHousehold(Household household, Place place);

//------------------------------------------------------------------------------
    //Verknüpfung Place_StocksArticle
//------------------------------------------------------------------------------
    public boolean addStocksArticleToPlace(Place place, StocksArticle stocksArticle);
    
    public boolean removeStocksArticleFromPlace(Place place, StocksArticle stocksArticle);
    
    
    //noch fraglich
    public List<Household> getHouseholdsForUser(AppUser user);
}
