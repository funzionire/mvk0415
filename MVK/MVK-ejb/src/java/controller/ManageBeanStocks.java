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

    //Kommentare?? jeweils ähnlich wie in StocksLocal? für die grobe Gliederung..
    SessionBeanStocksArticleLocal sessionBeanStocksArticle = getSessionBeanStocksArticle();
    SessionBeanHouseholdLocal sessionBeanHousehold = getSessionBeanHousehold();

    @Override
    public boolean addStocksArticle(String nameArt, Place place, String commentArt) {
        //muss noch überprüft werden--> Kommentar(ob das so funzelt)
        try {
            StocksArticle stocksArticle = sessionBeanStocksArticle.createStocksArticle(nameArt, place, commentArt);
            sessionBeanHousehold.addStocksArticleToPlace(place, stocksArticle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeStocksArticle(StocksArticle stocksArticle) {
        try {
            Place place = stocksArticle.getPlace();
            sessionBeanHousehold.removeStocksArticleFromPlace(place, stocksArticle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addStocksUnit(StocksArticle stocksArticle, int quantity, Date mdd, String commentSUnit) {
        try {
            //fliegt er so schon raus in den catch also wenn quantity >0?
            if(quantity > 0){
            StocksUnit stocksUnit = sessionBeanStocksArticle.createStocksUnit(quantity, mdd, commentSUnit);
            sessionBeanStocksArticle.addStocksUnitToStocksArticle(stocksArticle, stocksUnit);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeStocksUnit(StocksUnit stocksUnit) {
        try {
            sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean moveStocksUnit(StocksUnit stocksUnit, Place newPlace, int newQuantity) {
        //toDO hier muss noch durchittariert werden ob es schon eine gleichen StocksArticle gibt!
        //für die Kontrolle, ob ein neuer Article in dem neuen Platz angelegt werden muss;
        try {
            boolean haveToCreateNewStocksArticle = true;

            for (StocksArticle stocksArticle : newPlace.getStocksArticleList()) {
                if (stocksArticle.getName().equals(stocksUnit.getStocksArticle().getName())) {
                    haveToCreateNewStocksArticle = false;
                    break;
                }
            }
            if (haveToCreateNewStocksArticle) {
                StocksArticle newStocksArticle = sessionBeanStocksArticle.createStocksArticle(stocksUnit.getStocksArticle().getName(), newPlace, stocksUnit.getStocksArticle().getComment());
                sessionBeanStocksArticle.addStocksArticleToPlace(newPlace, newStocksArticle);
            }
            sessionBeanStocksArticle.createStocksUnit(newQuantity, stocksUnit.getMdd(), stocksUnit.getCommentSUnit());
            sessionBeanStocksArticle.addStocksUnitToStocksArticle(stocksUnit.getStocksArticle(), stocksUnit);

            //notwendige Änderungen an der bestehenden StocksUnit
            if (stocksUnit.getQuantity() == newQuantity) {
                sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
            }
            if (stocksUnit.getQuantity() > newQuantity) {
                int updateQuantity = stocksUnit.getQuantity() - newQuantity;
                sessionBeanStocksArticle.changeStocksUnit(stocksUnit, stocksUnit.getMdd(), stocksUnit.getCommentSUnit(), updateQuantity);
            } else {
                //toDo --> error abfangen!
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace) {
        try {
            boolean haveToCreateNewStocksArticle = true;
            StocksArticle holdStocksArticle = null;

            for (StocksArticle stocksArticle1 : newPlace.getStocksArticleList()) {
                if (stocksArticle1.getName().equals(stocksArticle.getName())) {
                    haveToCreateNewStocksArticle = false;
                    holdStocksArticle = stocksArticle1;
                    break;
                }
            }
            if (haveToCreateNewStocksArticle) {
                stocksArticle.setPlace(newPlace);
            } else {
                for (StocksUnit stocksUnit : stocksArticle.getStocksUnitList()) {
                    sessionBeanStocksArticle.addStocksUnitToStocksArticle(holdStocksArticle, stocksUnit);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean raiseQuantityOfStocksUnit(StocksUnit stocksUnit) {
        try{
            //Soll es auch möglich sein eine Vorauswahl zu haben (z.B. 10 für 10 Eier mehr)
            sessionBeanStocksArticle.changeQuantityOfStocksUnit(stocksUnit, 1);
            return true;
        } catch (Exception e) {
            return false;   
        }
    }

    @Override
    public boolean reduceQuantityOfStoksUnit(StocksUnit stocksUnit) {
        try{
            if(stocksUnit.getQuantity() > 1){
                sessionBeanStocksArticle.changeQuantityOfStocksUnit(stocksUnit, -1);
            }
            if(stocksUnit.getQuantity() == 1){
                //???Frage stellen: wirklich löschen??
                sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
            }
            return true;
        } catch (Exception e) {
            return false;   
        }
    }
    
    
}
