/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BeanFactory.getSessionBeanHousehold;
import static controller.BeanFactory.getSessionBeanStocksArticle;
import java.util.Date;
import javax.ejb.Stateless;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;

/**
 *
 * @author Felix
 */
@Stateless
public class ManageBeanStocks implements ManageBeanStocksLocal {
    
    //Kommentare?? jeweils ähnlich wie in StocksLocal?
    SessionBeanStocksArticleLocal sessionBeanStocksArticle = getSessionBeanStocksArticle();
    SessionBeanHouseholdLocal sessionBeanHousehold = getSessionBeanHousehold();
    
    @Override
    public void addStocksArticle(String nameArt, Place place, String commentArt) {
        //muss noch überprüft werden--> Kommentar(ob das so funzelt)
        StocksArticle stocksArticle = sessionBeanStocksArticle.createStocksArticle(nameArt, place, commentArt);
        sessionBeanHousehold.addStocksArticleToPlace(place, stocksArticle);
    }

    @Override
    public void removeStocksArticle(StocksArticle stocksArticle) {
        Place place = stocksArticle.getPlace();
        sessionBeanHousehold.removeStocksArticleFromPlace(place, stocksArticle);
    }

    @Override
    public void addStocksUnit(StocksArticle stocksArticle, int quantity, Date mdd, String commentSUnit) {
        StocksUnit stocksUnit = sessionBeanStocksArticle.createStocksUnit(quantity, null, commentSUnit);
        sessionBeanStocksArticle.addStocksUnitToStocksArticle(stocksArticle, stocksUnit);        
    }

    @Override
    public void removeStocksUnit(StocksUnit stocksUnit) {
        sessionBeanStocksArticle.removeStocksUnit(stocksUnit);
    }

    @Override
    public void moveStocksUnit(StocksUnit stocksUnit, Place newPlace, int newQuantity) {
        //toDO hier muss noch durchittariert werden ob es schon eine gleichen StocksArticle gibt!
        //if(stocksUnit.getStocksArticle().getName() != newPlace.getStocksArticleList().get)
        sessionBeanStocksArticle.createStocksArticle(stocksUnit.getStocksArticle().getName(), newPlace, stocksUnit.getStocksArticle().getComment());
        sessionBeanStocksArticle.createStocksUnit(newQuantity, stocksUnit.getMdd(), stocksUnit.getCommentSUnit());
        if(stocksUnit.getQuantity()== newQuantity){
            sessionBeanStocksArticle.removeStocksUnit(stocksUnit);
        }
        if(stocksUnit.getQuantity()> newQuantity){
            int updateQuantity = stocksUnit.getQuantity()-newQuantity;
            sessionBeanStocksArticle.changeStocksUnit(stocksUnit, stocksUnit.getMdd(), stocksUnit.getCommentSUnit(), updateQuantity);
        }
        else{
            //toDo --> error abfangen!
        }
    }

    @Override
    public void movePlace(StocksArticle stocksArticle, Place newPlace) {
        //toDo: Namen vergleichen siehe Methode moveStocksUnit
    }
    
    
}
